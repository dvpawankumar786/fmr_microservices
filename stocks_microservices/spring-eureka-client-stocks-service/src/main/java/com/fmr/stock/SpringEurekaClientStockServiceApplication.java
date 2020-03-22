package com.fmr.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication(scanBasePackages={"com.fmr.stock"})
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
public class SpringEurekaClientStockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaClientStockServiceApplication.class, args);
	}
}
