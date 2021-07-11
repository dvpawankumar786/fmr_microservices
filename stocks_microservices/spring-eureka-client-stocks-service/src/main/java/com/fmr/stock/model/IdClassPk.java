package com.fmr.stock.model;

import java.io.Serializable;

public class IdClassPk implements Serializable {
	 
		private String stocksId;
		private String portfolioId;
	 
		public IdClassPk() {
	 
		}

		public IdClassPk(String stocksId, String portfolioId) {
			super();
			this.stocksId = stocksId;
			this.portfolioId = portfolioId;
		}

		public String getStockId() {
			return stocksId;
		}

		public void setStockId(String stockId) {
			this.stocksId = stockId;
		}

		public String getPortfolioId() {
			return portfolioId;
		}

		public void setPortfolioId(String portfolioId) {
			this.portfolioId = portfolioId;
		}
		public int hashCode() {
	         return (int)this.stocksId.hashCode();
	     }
		public boolean equals(Object obj) {
	         if (obj == this) return true;
	         if (!(obj instanceof IdClassPk)) return false;
	         IdClassPk pk = (IdClassPk) obj;
	         return pk.stocksId.equals(this.stocksId) && pk.portfolioId.equals(this.portfolioId);
	     }

		
		
	}
