package com.example.RuokalistaApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.RuokalistaApp.domain.ApplicationUserRepository;

@SpringBootApplication
public class RuokalistaAppApplication {

	@Autowired
	ApplicationUserRepository applicationUserRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RuokalistaAppApplication.class, args);
	}

}
