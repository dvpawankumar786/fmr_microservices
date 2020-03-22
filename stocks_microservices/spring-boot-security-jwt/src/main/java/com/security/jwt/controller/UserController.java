package com.security.jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.security.jwt.model.PortfolioReqDto;
import com.security.jwt.model.PortfolioResponseDto;
import com.security.jwt.model.Stocks;
import com.security.jwt.model.User;
import com.security.jwt.model.UserDto;
import com.security.jwt.model.custDetailsReqDto;
import com.security.jwt.service.RemoteServiceCall;
import com.security.jwt.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/verify")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
   	RemoteServiceCall remoteBankServiceCall;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }


    
    @RequestMapping(value = "/saveStockDetailsIn", method = RequestMethod.POST)
   	@HystrixCommand(fallbackMethod = "stockServiceFallBack")
   	@PreAuthorize("hasRole('ADMIN')")
   	public ResponseEntity<Stocks> saveStocksDetails(@RequestBody Stocks stockDto) {
   		System.out.println("INSIDE saveStocksDetails");
   		return new ResponseEntity<Stocks>(remoteBankServiceCall.callStockService(stockDto),HttpStatus.OK);
   		
   	}
       
   	@RequestMapping(value = "/getStockDetails", method = RequestMethod.POST)
   	@HystrixCommand(fallbackMethod = "stockGetServiceFallBack")
   	@PreAuthorize("hasRole('ADMIN')")
   	public ResponseEntity<List<Stocks>> getStocksDetails(@RequestBody PortfolioReqDto portReqDto,HttpSession session) {
   		
   		System.out.println("INISDE getStocksDetails");
   		
   		List<Stocks> finList=remoteBankServiceCall.callStockServiceForGet(portReqDto);
   		System.out.println("count"+finList.size());
   		session.setAttribute("stockslist", finList);
   		return new ResponseEntity<List<Stocks>>(finList,HttpStatus.OK);
   		
   	}
   	
 	@RequestMapping(value = "/getAllPortfolioDetails", method = RequestMethod.POST)
   	@HystrixCommand(fallbackMethod = "portfolioServiceFallBack")
   	@PreAuthorize("hasRole('ADMIN')")
   	public ResponseEntity<List<PortfolioResponseDto>> getAllPortfolioDetails(@RequestBody custDetailsReqDto custDetailsReq,HttpSession session) {
   		System.out.println("INISDE getAllPortfolioDetails");
   		
   		List<PortfolioResponseDto> finList=remoteBankServiceCall.callPortfolioAlldata(custDetailsReq);
   		//System.out.println("count"+finList.size());
   		System.out.println("fin count port-"+finList.size());
   		if(finList!=null && finList.size()==0)
   		{
   			PortfolioResponseDto p=new PortfolioResponseDto();
   			p.setTransFlag("F");
   			p.setTransStatus("No data found");
   			finList.add(p);
   		} else 
   		if(finList==null)
   		{
   			PortfolioResponseDto p=new PortfolioResponseDto();
   			p.setTransFlag("F");
   			p.setTransStatus("No data found");
   			finList.add(p);
   		}
   		session.setAttribute("portlist", finList);
   		return new ResponseEntity<List<PortfolioResponseDto>>(finList,HttpStatus.OK);
   		
   	}
	@RequestMapping(value = "/logoutser", method = RequestMethod.POST)
   	@PreAuthorize("hasRole('ADMIN')")
   	public @ResponseBody ResponseEntity<String> logout(PortfolioReqDto obj,HttpSession httpSession) {
   		System.out.println("INISDE logout");
   		httpSession.invalidate();
   	return new ResponseEntity<String>("invalidated",HttpStatus.OK);
   		
   	}
	
	@RequestMapping(value = "/getStockMasterDetails", method = RequestMethod.POST)
   	@HystrixCommand(fallbackMethod = "stockGetMasterServiceFallBack")
   	@PreAuthorize("hasRole('ADMIN')")
   	public ResponseEntity<List<Stocks>> getStocksMasterDetails(@RequestBody PortfolioReqDto portReqDto,HttpSession session) {
   		
   		System.out.println("INISDE getStocksDetails master call");
   		
   		List<Stocks> finList=remoteBankServiceCall.callMasterStockServiceForGet();
   		System.out.println("count"+finList.size());
   		session.setAttribute("masterstockslist", finList);
   		return new ResponseEntity<List<Stocks>>(finList,HttpStatus.OK);
	}
   	
   	public ResponseEntity<Stocks> stockServiceFallBack(Stocks stockDto,Throwable throwable)
   	{
   	Stocks stk=new Stocks();
   	System.out.println(throwable);
   	stk.setTransStatus("failed");
   	stk.setTransFlag("F");

   		return new ResponseEntity<Stocks>(stk,HttpStatus.OK);
   	}
   	public ResponseEntity<List<Stocks>> stockGetServiceFallBack(@RequestBody PortfolioReqDto portReqDto,HttpSession session)
   	{
   	System.out.println("inside fallback stocks new");
   	 List<Stocks> list=new ArrayList<Stocks>();
   	 Stocks s=new Stocks();
   	 s.setTransFlag("F");
   	 s.setTransStatus("fallback for stocks service");
   	 System.out.println("stocks details"+s);
   	 list.add(s);
		session.setAttribute("stockslist", list);

   	 return new ResponseEntity<List<Stocks>>(list, HttpStatus.OK);
   	}
	public ResponseEntity<List<PortfolioResponseDto>> portfolioServiceFallBack(@RequestBody custDetailsReqDto portReqDto,HttpSession session)
   	{
   	
   	 List<PortfolioResponseDto> list=new ArrayList<PortfolioResponseDto>();
   	PortfolioResponseDto pobj=new PortfolioResponseDto();
   	pobj.setTransFlag("F");
   	pobj.setTransStatus("fallback from portfolio service");
   	 list.add(pobj);
   	session.setAttribute("portlist", list);
   	 return new ResponseEntity<List<PortfolioResponseDto>>(list, HttpStatus.OK);
   	}
	public ResponseEntity<List<Stocks>> stockGetMasterServiceFallBack(@RequestBody PortfolioReqDto portReqDto,HttpSession session)
   	{
   	System.out.println("inside fallback stocks master");
   	 List<Stocks> list=new ArrayList<Stocks>();
   	 Stocks s=new Stocks();
   	 s.setTransFlag("F");
   	 s.setTransStatus("fallback for stocks service");
   	 System.out.println("stocks details"+s);
   	 list.add(s);
		session.setAttribute("masterstockslist", list);

   	 return new ResponseEntity<List<Stocks>>(list, HttpStatus.OK);
   	}
}
