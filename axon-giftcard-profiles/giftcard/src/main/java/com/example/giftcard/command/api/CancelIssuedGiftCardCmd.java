package com.example.giftcard.command.api;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
@Value
public class CancelIssuedGiftCardCmd {

	  @TargetAggregateIdentifier 
	  UUID id;
}
