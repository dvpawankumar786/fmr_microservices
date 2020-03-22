package com.fmr.stock.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="stocksmaster", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}, name="USER_UNIQUE_STOCKMASTER"))
public class MasterStock implements Serializable{
	@Id
	@Column(name="id",length=50)
	private int id;
	
	@Column(name="symbol",length=50)
    private String symbol;
	
	@Column(name="type",length=50)
    private String type;
	
	@Column(name="lastdividend",length=50)
    private String lastDividend;
	
	@Column(name="fixeddividend",length=50)
    private String fixedDividend;
	
	@Column(name="parvalue",length=50)
    private String parValue;
	
	@Column(name="tickerprice",length=50)
    private String tickerPrice;
    
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
}