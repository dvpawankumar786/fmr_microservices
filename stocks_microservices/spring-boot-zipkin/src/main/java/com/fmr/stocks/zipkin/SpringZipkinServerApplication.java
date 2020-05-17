package com.fmr.stocks.zipkin;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringZipkinServerApplication {

	private static final Logger logger=LoggerFactory.getLogger(SpringZipkinServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringZipkinServerApplication.class, args);
	}
	@RequestMapping(value = "/testingzipkin", method = RequestMethod.GET)
	public String getPortfolioDetailsss() {
		logger.info("inside testing");
		
        logger.info("inside testing ends");

        return "hello";
		
	}

}
