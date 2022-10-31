package com.example.RuokalistaApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RuokalistaApp.domain.Recept;
import com.example.RuokalistaApp.domain.ReceptRepository;

@RestController
public class ReceptsRestController {
	
	@Autowired
	private ReceptRepository receptRepository;
	
	//search and return all recepts from database
	@GetMapping("/api")
	public Iterable<Recept> getRecepts() {
		return receptRepository.findAll();
	}
	
	
	
}
