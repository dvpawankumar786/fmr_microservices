package com.fmr.stock.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.fmr.stock.model.MasterStock;
import com.fmr.stock.model.StockDto;

public interface StockInt {

    void recordStock(StockDto stock);

    /**
     * Retrieves stock details from the underlying repository.
     *
     * @param stockSymbol stock symbol
     * @return Stock details
     */
    StockDto retrieveStock(String stockSymbol);
    
  
    List<StockDto> retrieveAllStock(String portfolioId);
    
    List<MasterStock> getMasterData();
}
