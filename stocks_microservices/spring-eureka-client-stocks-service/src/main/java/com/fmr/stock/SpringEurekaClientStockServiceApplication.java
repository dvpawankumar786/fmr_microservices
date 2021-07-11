package com.fmr.stock;


import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"com.fmr.stock.*"})
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
public class SpringEurekaClientStockServiceApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringEurekaClientStockServiceApplication.class, args);

	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public AlwaysSampler defaultSampler() {
	   return new AlwaysSampler();
	}
}
