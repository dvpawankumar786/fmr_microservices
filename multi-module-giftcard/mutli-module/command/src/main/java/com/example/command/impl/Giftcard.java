package com.example.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class Giftcard {

	public Giftcard()
	{
		System.out.println("entering emplty constructor");
	}
    @AggregateIdentifier
    private UUID id;

    private int remainingValue;

    @CommandHandler
    public Giftcard(IssueCmd cmd) {
        log.debug("handling {}", cmd);
        if(cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
        apply(new IssuedEvt(cmd.getId(), cmd.getAmount()));
    }

    @CommandHandler
    public void handle(RedeemCmd cmd) {
    	log.debug("handling RedeemCmd- {} amount-{} remainingValue- {}", cmd.getId(),cmd.getAmount(),remainingValue);
        if(cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
        if(cmd.getAmount() > remainingValue) throw new IllegalArgumentException("amount > remaining");
        apply(new RedeemedEvt(cmd.getId(), cmd.getAmount(),(remainingValue-cmd.getAmount())));
    }
    
    @CommandHandler
    public void handle(CancelIssuedGiftCardCmd cmd) {
        log.debug("handling {}", cmd);
       
        apply(new CancelIssuedGiftCardEvt(cmd.getId()));
    }

    @EventSourcingHandler
    public void on(IssuedEvt evt) {
        log.debug("applying {}", evt);
        id = evt.getId();
        remainingValue = evt.getAmount();
        
    }

    @EventSourcingHandler
    public void on(RedeemedEvt evt) {
        log.debug("applying {}", evt);
        remainingValue -= evt.getAmount();
    }

}
