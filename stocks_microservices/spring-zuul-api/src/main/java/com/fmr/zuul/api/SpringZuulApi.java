package com.fmr.zuul.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.fmr.zuul.api.filter.PostFilter;
import com.fmr.zuul.api.filter.PreFilter;
import com.fmr.zuul.api.filter.RouteFilter;
import com.fmr.zuul.api.filter.ErrorFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@ComponentScan("com.fmr.zuul.cirbreaker")
public class SpringZuulApi {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulApi.class, args);
	}
	@Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
    @Bean
    public ErrorFilter ErrorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public ZuulFallbackProvider zuulFallbackProvider() {
        return new ZuulFallbackProvider() {
 
            @Override
            public String getRoute() {
                // Might be confusing: it's the serviceId property and not the route
                return "schoolser";
            }
 
            @Override
            public ClientHttpResponse fallbackResponse() {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }
 
                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }
 
                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }
 
                    @Override
                    public void close() {}
 
                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }
 
                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        return headers;
                    }
                };
            }
        };
    }

}
