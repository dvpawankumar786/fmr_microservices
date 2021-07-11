package com.fmr.cloud.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@RefreshScope
public class GateWayController {
	
	@Value("${testmessage}")
	 private String msg;
	
	
	@GetMapping("/testing")
    public String getMsg() {
	 System.out.println("message"+this.msg);
        return this.msg;
    }

}
