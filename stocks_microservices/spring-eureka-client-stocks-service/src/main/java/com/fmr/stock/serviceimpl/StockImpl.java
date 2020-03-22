package com.fmr.stock.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmr.stock.model.MasterStock;
import com.fmr.stock.model.StockDto;
import com.fmr.stock.repository.StockMasterRepo;
import com.fmr.stock.repository.StockRepository;
import com.fmr.stock.service.StockInt;

@Service
public class StockImpl implements StockInt {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	StockMasterRepo stockMasterRepo;
	
	@Override
	public void recordStock(StockDto stock) {
		stockRepository.save(stock);
	}

	@Override
	public List<StockDto> retrieveAllStock(String portfolioId) {
		// TODO Auto-generated method stub
		return stockRepository.retrieveAllStock(portfolioId);
	}

	@Override
	public StockDto retrieveStock(String stockSymbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MasterStock> getMasterData() {
		// TODO Auto-generated method stub
		return stockMasterRepo.findAll();
	}

}