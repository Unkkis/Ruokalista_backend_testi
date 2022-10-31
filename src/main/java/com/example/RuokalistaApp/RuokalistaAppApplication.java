package com.example.RuokalistaApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.RuokalistaApp.domain.UserRepository;

@SpringBootApplication
public class RuokalistaAppApplication {

	@Autowired
	UserRepository userRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RuokalistaAppApplication.class, args);
	}

}
