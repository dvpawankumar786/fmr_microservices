package com.fmr.portfolio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="portfolio", uniqueConstraints = @UniqueConstraint(columnNames = {"custid"}, name="USER_UNIQUE_custid"))
@IdClass(IdClasspk2.class)
public class PortfolioDto implements Serializable{
	
	@Id
	@Column(name="custid",length=50)
	private String custId;
	
	@Id
	@Column(name="portfolioid",length=50)
	private String portfolioId;
	
	@Id
	@Column(name="stockid",length=50)
	private String stockId;

	@Column(name="timestamp",length=50)
	private String timestamp;
	
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
	
	
}
