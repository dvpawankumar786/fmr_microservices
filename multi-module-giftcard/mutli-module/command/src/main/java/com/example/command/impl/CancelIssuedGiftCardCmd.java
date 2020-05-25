package com.example.command.impl;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
@Value
public class CancelIssuedGiftCardCmd {

	  @TargetAggregateIdentifier 
	  UUID id;
}
