package com.Mohamed.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/*@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("http://localhost:8082/"))
				.route(r->r.path("/inventroys/**").uri("http://localhost:8090/"))
				.build();
	}*/
	/* yml properties:
	*spring:
  cloud:
    gateway:
      routes:
        - id: r1
          uri: http://localhost:8082/
          predicates:
            - Path=/customers/**
        - id: r2
          uri: http://localhost:8090/
          predicates:
            - Path=/inventroys/**
    discovery:
      enabled: false
server:
  port: 8888
	* */

	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}
	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("lb://customer-service"))
				.route(r->r.path("/products/**").uri("lb://inventory-service"))
				.route(r->r.path("/bills/**").uri("lb://billing-service"))
				.route(r->r.path("/suppliers/**").uri("lb://supplier-service"))
				.route(r->r.path("/**").uri("lb://front-service"))

				.build();
	}




}
