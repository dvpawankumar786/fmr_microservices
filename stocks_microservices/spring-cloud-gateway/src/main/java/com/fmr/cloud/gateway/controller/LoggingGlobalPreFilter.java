package com.fmr.cloud.gateway.controller;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalPreFilter implements GlobalFilter ,Ordered{
	  @Override
	    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

	        return chain.filter(
	                exchange.mutate().request(
	                        exchange.getRequest().mutate()
	                                .header("gatefal", "yes")
	                                .build())
	                        .build());
	    }

	    @Override
	    public int getOrder() {
	        return 0;
	    } 

}
