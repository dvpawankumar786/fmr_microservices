package com.fmr.stock.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@IdClass(IdClassPk.class)
@Table(name="stocks", uniqueConstraints = @UniqueConstraint(columnNames = {"symbol"}, name="USER_UNIQUE_STOCKNAME"))
public class StockDto implements Serializable{

	
	@Column(name="symbol",length=50)
    private String symbol;
	@Id
	@Column(name="stocksid",length=10)
    private String stocksId;
	@Id
	@Column(name="portfolioid",length=50)
    private String portfolioId;
	
	@Column(name="type",length=100)
    private String type;
	
	@Column(name="lastdividend",length=20)
    private String lastDividend;
	
	@Column(name="fixeddividend",length=20)
    private String fixedDividend;
	
	@Column(name="parvalue",length=20)
    private String parValue;
	
	@Column(name="tickerprice",length=20)
    private String tickerPrice;
    
	@Transient
    private String transStatus="done";
	    
	@Transient
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

   
}