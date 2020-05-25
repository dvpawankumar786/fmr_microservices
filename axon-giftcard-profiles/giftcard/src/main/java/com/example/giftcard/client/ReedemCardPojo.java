package com.example.giftcard.client;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReedemCardPojo {
	
	private UUID uuid;
	private int amount;

}
