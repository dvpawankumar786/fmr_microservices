package com.fmr.portfolio.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fmr.portfolio.model.PortfolioDto;
import com.fmr.portfolio.repository.PortfolioRepository;

import com.fmr.portfolio.serviceint.PortfolioInt;

@Service
public class PortfolioServiceImpl implements PortfolioInt{
    @Autowired
	PortfolioRepository portfolioRepository;
    
	@Override
	public List<PortfolioDto> getPortfolioDetails(String custId) {
		System.out.println("inside PortfolioServiceImpl-"+custId);
		return portfolioRepository.retrievePortfolio(custId);
	}

	@Override
	public List<PortfolioDto> getPortfolioDbCallDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
