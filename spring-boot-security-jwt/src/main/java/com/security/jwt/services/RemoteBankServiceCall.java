package com.security.jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteBankServiceCall {
	
	@Autowired
	RestTemplate restTemplate;
	
public String callBankService(String custid)
{
	System.out.println("Getting account details for " + custid);
	String response = restTemplate.exchange("http://bank-service/getAccountDetailsBtCustId/{custid}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
	}, custid).getBody();
    return response;
}

public String callBankProfileService(String custid)
{
	System.out.println("Getting profile details for " + custid);
	String response = restTemplate.exchange("http://bank-service/getProfileDetails/{custid}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
	}, custid).getBody();
    return response;
}


}
