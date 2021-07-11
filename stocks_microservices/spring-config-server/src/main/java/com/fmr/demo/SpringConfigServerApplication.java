package com.fmr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;

@EnableConfigServer
@SpringBootApplication
public class SpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerApplication.class, args);
	}
	
	 @GetMapping("/testing1")
	    public String getMsg() {
		 
	        return "";
	    }

}
