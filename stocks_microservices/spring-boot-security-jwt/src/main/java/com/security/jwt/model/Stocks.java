package com.security.jwt.model;

public class Stocks{

	
    private String symbol;
	
    private String stocksId;

    private String portfolioId;
	
    private String type;
	    private String lastDividend;
	
    private String fixedDividend;
	
    private String parValue;
	
    private String tickerPrice;
    
    private String transStatus="done";
    
    private String transFlag="S";
    
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(String lastDividend) {
		this.lastDividend = lastDividend;
	}
	public String getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(String fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public String getParValue() {
		return parValue;
	}
	public void setParValue(String parValue) {
		this.parValue = parValue;
	}
	public String getTickerPrice() {
		return tickerPrice;
	}
	public void setTickerPrice(String tickerPrice) {
		this.tickerPrice = tickerPrice;
	}
	public String getStocksId() {
		return stocksId;
	}
	public void setStocksId(String stocksId) {
		this.stocksId = stocksId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getTransFlag() {
		return transFlag;
	}
	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}
	@Override
	public String toString() {
		return "Stocks [symbol=" + symbol + ", stocksId=" + stocksId + ", portfolioId=" + portfolioId + ", type=" + type
				+ ", lastDividend=" + lastDividend + ", fixedDividend=" + fixedDividend + ", parValue=" + parValue
				+ ", tickerPrice=" + tickerPrice + ", transStatus=" + transStatus + ", transFlag=" + transFlag + "]";
	}


   
}