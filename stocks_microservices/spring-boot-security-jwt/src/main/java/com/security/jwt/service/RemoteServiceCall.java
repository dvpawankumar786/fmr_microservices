package com.security.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.security.jwt.model.PortfolioReqDto;
import com.security.jwt.model.PortfolioResponseDto;
import com.security.jwt.model.Stocks;
import com.security.jwt.model.custDetailsReqDto;

@Service
public class RemoteServiceCall {
	
	@Autowired
	RestTemplate restTemplate;
	
public Stocks callStockService(Stocks stockDto)
{
	System.out.println("inside callStockService");
	Stocks response = restTemplate.postForObject("http://stock-service/savestock", stockDto, Stocks.class);
    return response;
}

public List<Stocks> callStockServiceForGet(PortfolioReqDto portfolioId)
{
	System.out.println("inside jwt service");
		
		 System.out.println("kdsufhf"+portfolioId.getPortfolioId());
		 List<Stocks> response = restTemplate.exchange("http://stock-service/getallstocks/{portfolioId}",HttpMethod.GET, null, new ParameterizedTypeReference<List<Stocks>>(){ 
		},portfolioId.getPortfolioId()).getBody();
		  return response;
		 
}
public List<String> callPortfolioremote(String custid)
{
	System.out.println("inside jwt service");
		
		 System.out.println("kdsufhf"+custid);
		 List<String> response = restTemplate.exchange("http://portfolio-service/getPortfolioDbdetails/{custid}",HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){ 
		},custid).getBody();
		  return response;
		 
}

public List<PortfolioResponseDto> callPortfolioAlldata(custDetailsReqDto custid)
{
	System.out.println("inside jwt service");
		
		 System.out.println("kdsufhf"+custid.getCustid());
		 List<PortfolioResponseDto> response = restTemplate.exchange("http://portfolio-service/getPortfolio/{custid}",HttpMethod.GET, null, new ParameterizedTypeReference<List<PortfolioResponseDto>>(){ 
		},custid.getCustid()).getBody();
		  return response;
		 
}
public List<Stocks> callMasterStockServiceForGet()
{
	System.out.println("inside master stock service");
		
		 List<Stocks> response = restTemplate.exchange("http://stock-service/getmasterstocks",HttpMethod.GET, null, new ParameterizedTypeReference<List<Stocks>>(){ 
		},"").getBody();
		  return response;
		 
}
}
