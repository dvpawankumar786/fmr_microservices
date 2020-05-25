package com.example.query;

import com.example.command.impl.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class SummaryProjector {

	@Autowired
    private final GiftCardRepository giftCardRepository;

	@Autowired
    private final MasterCardRepository masterCardRepository;
	
	@Autowired
    private final QueryUpdateEmitter updateEmitter;

	

    @EventHandler
    public void on(IssuedEvt evt) {
        log.debug("projecting {}", evt);
        giftCardRepository.save(new GiftcardSummary(evt.getId(), evt.getAmount(), evt.getAmount()));
        
        MasterCardData obj= new MasterCardData(evt.getId(), evt.getAmount(), evt.getAmount());
        masterCardRepository.save(obj);
        
        updateEmitter.emit(m -> "mastercarddatawatch".equals(m.getQueryName()),obj);

    }

    @EventHandler
    public void on(RedeemedEvt evt) {
        log.debug("projecting {}", evt);
        giftCardRepository.findByid(evt.getId()).remainingValue -= evt.getAmount();
    }
    
    @EventHandler
    public void on(CancelIssuedGiftCardEvt evt) {
        log.debug("projecting {}", evt);
        giftCardRepository.deleteById(evt.getId());
    }

   
    @QueryHandler
    public GiftcardSummary handle(GiftcardSummaryQuery qry) {
        return giftCardRepository.findByid(qry.getId());
    }
    
    @QueryHandler
    public List<MasterCardData> handle(GfitCardAllDataQuery qry) {
        return (List<MasterCardData>) masterCardRepository.findAll();
    }
    @QueryHandler(queryName = "mastercarddatawatch")
    public List<MasterCardData> findMovements() {
        return (List<MasterCardData>) masterCardRepository.findAll();
    }

}
