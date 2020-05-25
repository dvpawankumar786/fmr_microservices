package com.example.query;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
	public interface GiftCardRepository extends CrudRepository<GiftcardSummary, UUID> {
	GiftcardSummary findByid(UUID uuid);
	}

