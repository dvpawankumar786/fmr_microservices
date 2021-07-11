package com.fmr.portfolio.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fmr.portfolio.model.PortfolioDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.fmr.portfolio.serviceint.PortfolioInt;

@Configuration
@RestController
@RefreshScope
public class PortfolioServiceController {
	
	@Autowired
	PortfolioInt portfolioInt;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${testmessage}")
	 private String msg;

	
	@RequestMapping(value = "/getPortfolioeeee", method = RequestMethod.GET)
	public String getPortfolioDetailsss(@PathVariable String custId) {
		String response = restTemplate.exchange("http://stock-service/getallstocks/{custid}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, custId).getBody();
        return response;
		
	}
	
	@RequestMapping(value = "/getPortfolioDbdetails/{custId}", method = RequestMethod.GET)
	public List<String> getPortfolioDBbyDbCall(@PathVariable String custId) {
		System.out.println("cust id inside-"+custId);
		List<String>folioList=new ArrayList<>();
		List<PortfolioDto> tlist=portfolioInt.getPortfolioDetails(custId);
		for(PortfolioDto p:tlist)
		{
			folioList.add(p.getPortfolioId());
		}
		return folioList;
	}
	
	@RequestMapping(value = "/getPortfolio/{custId}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "portfolioServiceFallBack1")
	public List<PortfolioDto> getPortfolioDetails(@PathVariable String custId) {
	   System.out.println("dfdfds"+custId);
		return portfolioInt.getPortfolioDetails(custId);
		
	}
	
	public List<PortfolioDto> portfolioServiceFallBack1(String custid)
	{
		System.out.println("inside portfolioServiceFallBack1");
		List<PortfolioDto> list=new ArrayList<PortfolioDto>();
	 return list;
	}
	@GetMapping("/testing")
    public String getMsg() {
	 System.out.println("message"+this.msg);
        return this.msg;
    }
	
}
