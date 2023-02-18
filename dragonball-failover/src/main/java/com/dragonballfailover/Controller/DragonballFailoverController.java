package com.dragonballfailover.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/db-failover/dragonball/characters")
public class DragonballFailoverController {
	private Faker faker = new Faker();
	private List<String> characters = new ArrayList<>();
	
	private static final Logger log = LoggerFactory.getLogger(DragonballFailoverController.class);

	@PostConstruct
	public void init() {
		for (int x = 0; x<20; x++) {
			characters.add(String.format("Failover - %s", faker.dragonBall().character()));
		}
	}
	@GetMapping
	public ResponseEntity<List<String>> get(){
		log.info("Getting characters db-failover");
		return ResponseEntity.ok(characters);
	}
}

