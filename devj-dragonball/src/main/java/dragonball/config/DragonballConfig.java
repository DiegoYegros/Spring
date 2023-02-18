package dragonball.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.annotation.Timed;

@Configuration
@ConfigurationProperties
@RefreshScope
public class DragonballConfig {
	
	@Value("${application.name}")
	private String applicationName;


	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
