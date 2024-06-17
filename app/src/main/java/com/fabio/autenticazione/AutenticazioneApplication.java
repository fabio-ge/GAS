package com.fabio.autenticazione;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AutenticazioneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticazioneApplication.class, args);
	}

	@Bean
	public CommandLineRunner debugIniziale() {
		return args -> {
			System.out.println("Stampare qua eventuali messaggi iniziali");
		};
	}

}
