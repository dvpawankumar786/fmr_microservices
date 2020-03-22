package com.security.jwt.model;

public class PortfolioResponseDto {
	
	private String custId;
	
	
	private String portfolioId;
	
	
	private String stockId;

	
	private String timestamp;
	
	private String transFlag="S";
	
	private String transStatus="done";
	
	public String getCustId() {
		return custId;
	}
 
	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransFlag() {
		return transFlag;
	}

	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	

}
