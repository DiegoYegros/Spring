package dragonball.controller;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dragonball.config.DragonballConfig;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {
	
	@Autowired
	DragonballConfig configuration;
	
	@Autowired
	private MeterRegistry registry;
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationNameController.class);
	
	@GetMapping
	
	@Timed("dragonball.name.get")
	public ResponseEntity<String> getAppName(){
		log.info("Getting Application name");
		int value = new Random().nextInt(5);
		registry.counter("dragonball.name", "level", (value<3)?"jr" : "sr").increment(value);;
		return ResponseEntity.ok(configuration.getApplicationName());
	}
}