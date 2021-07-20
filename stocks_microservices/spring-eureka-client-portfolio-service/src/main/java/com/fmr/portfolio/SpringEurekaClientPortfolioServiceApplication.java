package com.fmr.portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.fmr.portfolio.model.PortfolioDto;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableJpaRepositories(basePackages={"com.fmr.portfolio.repository"})
@EntityScan(basePackageClasses=PortfolioDto.class)
@Configuration
public class SpringEurekaClientPortfolioServiceApplication {

 	private static final Logger logger=LoggerFactory.getLogger(SpringEurekaClientPortfolioServiceApplication.class);

	public static void main(String[] args) throws InterruptedException {
    	logger.info("starts");
    	Thread.sleep(10000);
		SpringApplication.run(SpringEurekaClientPortfolioServiceApplication.class, args);
    	logger.info("ends");

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
