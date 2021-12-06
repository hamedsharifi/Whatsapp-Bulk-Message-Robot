package ir.haytech.whatsapprobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WhatsappRobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappRobotApplication.class, args);
	}

}
