package tech.cetacean.demos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("tech.cetacean.demos.model")
@EnableJpaRepositories("tech.cetacean.demos.repository")

@SpringBootApplication @ComponentScan(basePackages = {"tech.cetacean.demos.*"}) 
public class StaffingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffingApplication.class, args);
	}
	
}
