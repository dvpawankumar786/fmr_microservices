package com.example.giftcard.query;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Entity
	@Data
	@NoArgsConstructor
	public class MasterCardData {
		@Id 
		@GeneratedValue(strategy=GenerationType.AUTO)
		long id;
		UUID uuid;
	    int remainingValue;	
	    int initialValue;
		public MasterCardData(UUID uuid, int remainingValue, int initialValue) {
			super();
			this.uuid = uuid;
			this.remainingValue = remainingValue;
			this.initialValue = initialValue;
		}

	    
	    
	}
