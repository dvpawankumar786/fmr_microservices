package com.fmr.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fmr.portfolio.model.PortfolioDto;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableJpaRepositories(basePackages={"com.fmr.portfolio.repository"})
@EntityScan(basePackageClasses=PortfolioDto.class)
public class SpringEurekaClientPortfolioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaClientPortfolioServiceApplication.class, args);
	}
}
