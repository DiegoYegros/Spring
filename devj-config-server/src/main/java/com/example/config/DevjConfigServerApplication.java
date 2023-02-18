package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DevjConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevjConfigServerApplication.class, args);
	}

}