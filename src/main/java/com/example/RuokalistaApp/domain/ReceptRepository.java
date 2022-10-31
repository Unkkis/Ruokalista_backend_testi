package com.example.RuokalistaApp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReceptRepository extends CrudRepository<Recept, Long> {
	List<Recept> findByName(String name);
	List<Recept> findByCategory(String category);
}
