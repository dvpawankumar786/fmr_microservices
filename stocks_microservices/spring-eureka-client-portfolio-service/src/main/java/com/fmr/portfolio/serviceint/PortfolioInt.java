package com.fmr.portfolio.serviceint;

import java.util.List;

import com.fmr.portfolio.model.PortfolioDto;

public interface PortfolioInt {
	
	public List<PortfolioDto> getPortfolioDetails(String custId);
	public List<PortfolioDto> getPortfolioDbCallDetails();

}
