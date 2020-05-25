package com.example.giftcard.client;

import com.example.giftcard.command.api.CancelIssuedGiftCardCmd;
import com.example.giftcard.command.api.IssueCmd;
import com.example.giftcard.command.api.RedeemCmd;
import com.example.giftcard.query.GfitCardAllDataQuery;
import com.example.giftcard.query.GiftcardSummary;
import com.example.giftcard.query.GiftcardSummaryQuery;
import com.example.giftcard.query.MasterCardData;

import reactor.core.publisher.Flux;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryBackpressure;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController

@Profile("client")
@ControllerAdvice
public class ClientRunner implements CommandLineRunner {

	@Autowired
	 private CommandGateway commandGateway;
	 
	 @Autowired
	 private QueryGateway queryGateway;
    
    @RequestMapping(value = "/issueRestEnd/{amount}", method = RequestMethod.GET)
	public String issueRest(@PathVariable int amount) {
		UUID uuid=UUID.randomUUID();
       commandGateway.sendAndWait(new IssueCmd(uuid, amount));
       return "Issueed new card for "+uuid.toString()+" for amount of "+amount+"";
     	
	}
	
	@RequestMapping(value = "/reedemRestEnd", method = RequestMethod.POST,consumes = "application/json")
	public String reedemRest(@RequestBody ReedemCardPojo reedemCardPojo) {
       commandGateway.sendAndWait(new RedeemCmd(reedemCardPojo.getUuid(), reedemCardPojo.getAmount(),reedemCardPojo.getAmount()));
       return "Reedemed old card - "+reedemCardPojo.getUuid().toString()+" with new amount of "+reedemCardPojo.getAmount();
      
	}
	
	@RequestMapping(value = "/cancelRestEnd", method = RequestMethod.POST,consumes = "application/json")
	public String cancelReedemRest(@RequestBody ReedemCardPojo reedemCardPojo) {
		boolean flag=true;
		String message="old card - "+reedemCardPojo.getUuid().toString()+" not present in our DB";
		try
		{
			cardSymmary(reedemCardPojo.getUuid());
		}
		catch (HibernateException e) {
			flag=false;
		}
		if(flag)
		{
			commandGateway.sendAndWait(new CancelIssuedGiftCardCmd(reedemCardPojo.getUuid()));
			message="cancelled old card - "+reedemCardPojo.getUuid().toString();
			
		}
		 return message;
      
	}
	@RequestMapping(value = "/fetchCardSummary/{uuid}", method = RequestMethod.GET)
	public GiftcardSummary cardSymmary(@PathVariable UUID uuid) {
		

		 return queryGateway.query(new GiftcardSummaryQuery(uuid),
	                ResponseTypes.instanceOf(GiftcardSummary.class)).join();
	      
	}
	
	@RequestMapping(value = "/fetchMasterCardData/", method = RequestMethod.GET)
	public List<MasterCardData> cardAllSymmary() {
		

		return queryGateway.query(new GfitCardAllDataQuery(),
	             ResponseTypes.multipleInstancesOf(MasterCardData.class)).join();
	     
	      
	}
	
	 @GetMapping(value = "/cardData/watch", produces = "text/event-stream")
	    public Flux<MasterCardData> watch() {
	        SubscriptionQueryResult<List<MasterCardData>, MasterCardData> response = queryGateway.subscriptionQuery("mastercarddatawatch", "",
	                                                                                                          ResponseTypes.multipleInstancesOf(MasterCardData.class),
	                                                                                                          ResponseTypes.instanceOf(MasterCardData.class),
	                                                                                                          SubscriptionQueryBackpressure.defaultBackpressure()
	        );
	        return response.initialResult().flatMapMany(Flux::fromIterable).concatWith(response.updates());
	    }
	@Override
    public void run(String... args) throws Exception {
        UUID id = UUID.randomUUID();

        commandGateway.sendAndWait(new IssueCmd(id, 100));
	}
	
}
