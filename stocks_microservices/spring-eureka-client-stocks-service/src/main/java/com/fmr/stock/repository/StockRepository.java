package com.fmr.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fmr.stock.model.StockDto;

@Repository
public interface StockRepository extends JpaRepository<StockDto,String> {
	
	@Query(value="Select * from stocks s where s.portfolioid = ?1",nativeQuery = true)
    List<StockDto> retrieveAllStock(String portfolioid);


}
