package br.com.mgr.personapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"br.com.mgr.personapi.configuration"})
public class PersonapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonapiApplication.class, args);
	}

}
