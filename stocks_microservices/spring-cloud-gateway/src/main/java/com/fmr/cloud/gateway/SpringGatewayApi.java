package com.fmr.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages={"com.fmr.cloud.gateway.*"})
@EnableEurekaClient
@RestController
@EnableHystrix
@EnableCircuitBreaker
public class SpringGatewayApi {

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApi.class, args);
	}
	
	@GetMapping("/fallback/stockservice")
    public String fallBackForStock(){
        return "stock service fallback";
    }
	@GetMapping("/fallback/portfolioservice")
    public String fallBackForPortfolio(){
        return "portfolio service fallback";
    }
	@GetMapping("/fallback/securityservice")
    public String fallBackForSecurity(){
        return "security service fallback";
    }
	
}
