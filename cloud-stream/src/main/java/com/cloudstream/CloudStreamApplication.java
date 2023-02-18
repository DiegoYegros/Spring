package com.cloudstream;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class CloudStreamApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CloudStreamApplication.class);
	
	@Bean 
	public Function<String, String> toUpperCase() {
		return data->data.toUpperCase();
	}
	
	@Bean
	public Supplier<Flux<Long>> producer(){
		return () -> Flux.interval(Duration.ofSeconds(1)).log();
	}
	
	@Bean
	public Function<Flux<Long>, Flux<Long>> processor(){
		return flux -> flux.map(numero->numero*numero); 
	}
	
	@Bean
	public Consumer<Long> consumer(){
		return (number)->{
			log.info("Message received {}", number);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(CloudStreamApplication.class, args);
	}

}
