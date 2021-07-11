package com.fmr.portfolio.model;

import java.io.Serializable;

public class IdClasspk2 implements Serializable{
	
	private String custId;
	
	private String portfolioId;
	
	private String stockId;

	public IdClasspk2() {
		super();
		
	}

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
	
	public int hashCode() {
        return (int)this.custId.hashCode();
    }
	public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof IdClasspk2)) return false;
        IdClasspk2 pk = (IdClasspk2) obj;
        return pk.custId.equals(this.custId) && pk.stockId.equals(this.stockId);
    }
}
