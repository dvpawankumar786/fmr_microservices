package com.example.giftcard.query;


	import org.springframework.data.repository.CrudRepository;
	import org.springframework.stereotype.Repository;

	@Repository
		public interface MasterCardRepository extends CrudRepository<MasterCardData, Long> {
		
		}


