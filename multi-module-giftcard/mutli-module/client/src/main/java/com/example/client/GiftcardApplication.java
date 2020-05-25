package com.example.client;

import com.example.command.impl.*;
import com.example.query.*;
import com.thoughtworks.xstream.XStream;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryBackpressure;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GiftcardApplication implements CommandLineRunner {


	 @Autowired
	 private CommandGateway commandGateway;
	 
	 @Autowired
	 private QueryGateway queryGateway;
	
    public static void main(String[] args) {
        SpringApplication.run(GiftcardApplication.class, args);
        
    }
    @Autowired
    public void config(Serializer serializer) {
        if (serializer instanceof XStreamSerializer) {
            XStream xStream = ((XStreamSerializer) serializer).getXStream();
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[]{
                    "com.example.**",
                    "org.axonframework.**"});
        }
    }
   
    @RequestMapping(value = "/issueRestEnd/{amount}", method = RequestMethod.GET)
	public String issueRest(@PathVariable int amount) {
		UUID uuid=UUID.randomUUID();
       commandGateway.sendAndWait(new IssueCmd(uuid, amount));
       return "Issueed new card for "+uuid.toString()+" for amount of "+amount+"";
     	
	}
	
	@RequestMapping(value = "/reedemRestEnd", method = RequestMethod.POST,consumes = "application/json")
	public String reedemRest(@RequestBody ReedemCardPojo reedemCardPojo) {
       commandGateway.sendAndWait(new RedeemCmd(reedemCardPojo.getUuid(), reedemCardPojo.getAmount(),0));
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


