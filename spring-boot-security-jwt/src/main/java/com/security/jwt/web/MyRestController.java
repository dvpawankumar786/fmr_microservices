package com.security.jwt.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.security.jwt.security.UserDetailsImpl;
import com.security.jwt.services.RemoteBankServiceCall;

@RestController
@RequestMapping("")
public class MyRestController {
	
	@Autowired
	RemoteBankServiceCall remoteBankServiceCall;

	
	@RequestMapping("/foo")
	public String unsecured(@AuthenticationPrincipal UserDetailsImpl user) {
		return "UNSECURED. User is " + user;
	}
	
	@RequestMapping("/bar")
	@PreAuthorize("hasRole('USER')")
	public String secured1(@AuthenticationPrincipal UserDetailsImpl user) {
		return "user has been succssfuly authenticated";
	}
	
	@RequestMapping("/baz")
	@PreAuthorize("hasRole('ADMIN')")
	public String secured2(@AuthenticationPrincipal UserDetailsImpl user) {
		return "SECURED2. User is "+ user +".\nID: " + user.getId() + ", Username: " + user.getUsername();
	}
	
	@RequestMapping(value = "/getAccountDetailsByCustId/{custid}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "bankServiceFallBack")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAccountDetailsByCustId(@PathVariable String custid) {
		return remoteBankServiceCall.callBankService(custid);
		
	}
	@RequestMapping(value = "/getProfileDetailsForCust/{custid}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "bankServiceFallBack")
	@PreAuthorize("hasRole('USER')")
	public String getProfileDetails(@PathVariable String custid) {
		return remoteBankServiceCall.callBankProfileService(custid);
		
	}
	public String bankServiceFallBack(String custid)
	{
	
	 return "CIRCUIT BREAKER ENABLED for Bank!!! No Response From the Service at this moment. " +
                " Service will be back shortly - " + new Date();
	}
}