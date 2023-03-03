package ca.sheridancollege.nguye482.a4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class A4Nguye482Application {

	public static void main(String[] args) {
		SpringApplication.run(A4Nguye482Application.class, args);
	}

}
