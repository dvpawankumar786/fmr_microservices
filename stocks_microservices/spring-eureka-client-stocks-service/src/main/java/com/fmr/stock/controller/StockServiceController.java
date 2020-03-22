package com.fmr.stock.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fmr.stock.model.MasterStock;
import com.fmr.stock.model.PortFolioDto;
import com.fmr.stock.model.StockDto;
import com.fmr.stock.service.StockInt;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class StockServiceController {
	@Autowired
	StockInt stockInt;
	
	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@RequestMapping(value = "/savestock", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "sellingStockServiceFallBack")
	    public StockDto saveStock(@RequestBody StockDto stockDto) {
		String flag=System.getProperty("gatewayflag");
		System.out.println("insied savestock-"+flag);
		stockInt.recordStock(stockDto);
        return new StockDto();
    }
	@RequestMapping(value = "/getmasterstocks", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getmasterstocksFallBack")
	    public List<MasterStock> getMasterStocks(Throwable t) {
		return stockInt.getMasterData();
       
    }
	@RequestMapping(value = "/getallstocks/{portfolioId}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getStockServiceFallBack")
	    public List<StockDto> getAllStocks(@PathVariable String portfolioId) {
		System.out.println("hhhhh"+portfolioId);
		
		return stockInt.retrieveAllStock(portfolioId);
        
    }
	public StockDto sellingStockServiceFallBack(StockDto stockDto)
	{
		System.out.println("inside sellingStockServiceFallBack");
	StockDto stk=new StockDto();
	stk.setTransStatus("Failed");
	stk.setTransFlag("F");
	 return stk;
	}
	public List<StockDto> getStockServiceFallBack(String portfolioId)
	{
		System.out.println("inside getStockServiceFallBack");
	 List<StockDto> list=new ArrayList<StockDto>();
	 list.add(new StockDto());
	 return list;
	}

	@RequestMapping(value = "/getallstocksdummy", method = RequestMethod.GET)
    public String getallstocksdummy() {
	System.out.println("hhhhh");
	return "valid";
    
}
	public List<MasterStock> getmasterstocksFallBack( Throwable t)
	{
		System.out.println("inside getmasterstocksFallBack");
		System.out.println(t.getMessage());
	 List<MasterStock> list=new ArrayList<MasterStock>();
	 list.add(new MasterStock());
	 return list;
	}
}
