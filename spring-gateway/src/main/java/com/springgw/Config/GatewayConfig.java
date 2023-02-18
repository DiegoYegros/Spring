package com.springgw.Config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class GatewayConfig {
	
	@Bean
	@Profile("localhostRouter-noEureka")
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/dragonball/*")
				.uri("http://localhost:8081"))
				.route(r -> r.path("/api/v1/gameofthrones/*")
				.uri("http://localhost:8083"))
				.build();
	}  
	
	@Bean
	@Profile("localhost-Eureka")
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/dragonball/*")
				.uri("lb://dragonball"))
				.route(r -> r.path("/api/v1/gameofthrones/*")
				.uri("lb://game-of-thrones"))
				.build();
	}
	
	@Bean
	@Profile("localhost-Eureka-cb")
	public RouteLocator configLocalEurekaCb(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/dragonball/*")
						.filters(f->f.circuitBreaker(c->c.setName("failoverCB")
								.setFallbackUri("forward:/api/v1/db-failover/dragonball/characters")
								.setRouteId("dbFailOver")))
						.uri("lb://dragonball"))
				.route(r -> r.path("/api/v1/gameofthrones/*")
						.uri("lb://game-of-thrones"))
				.route(r -> r.path("/api/v1/db-failover/dragonball/characters")
						.uri("lb://dragonball-failover"))
				.build();
	}
}	
