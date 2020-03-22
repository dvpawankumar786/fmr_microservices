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
	 
	

		
		
	}
