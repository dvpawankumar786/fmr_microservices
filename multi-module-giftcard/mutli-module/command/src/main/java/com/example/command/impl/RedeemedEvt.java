package com.example.command.impl;

import lombok.Value;

import java.util.UUID;

@Value
public class RedeemedEvt {

    UUID id;
    int amount;
    private int remainingValue;


}
