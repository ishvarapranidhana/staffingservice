package tech.cetacean.demos.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("tech.cetacean.demos.model")
@EnableJpaRepositories("tech.cetacean.demos.repository")

@SpringBootApplication @ComponentScan(basePackages = {"tech.cetacean.demos.*"}) 
public class StaffingApplication {
	
	@Autowired
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context  = SpringApplication.run(StaffingApplication.class, args);
	}
	
	public static void restart() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(StaffingApplication.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }
	
}
