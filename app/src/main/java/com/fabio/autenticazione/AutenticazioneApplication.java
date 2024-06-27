package com.fabio.autenticazione;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fabio.autenticazione.util.MyMailSender;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class AutenticazioneApplication {


	public static void main(String[] args) {
		SpringApplication.run(AutenticazioneApplication.class, args);
	}

	@Bean
	public CommandLineRunner debugIniziale(MyMailSender mail) {
		return args -> {
			
			//mail.sendSimpleMail("fabio_perelli@yahoo.it", "Nuovo ordine", "Attenzione nuovo ordine disponibile per il GAS");
			//System.out.println("Stampare qua eventuali messaggi iniziali");
		};
	}

}
