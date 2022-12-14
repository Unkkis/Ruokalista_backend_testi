package com.example.RuokalistaApp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findByName(String name);
	List<Recipe> findByCategory(String category);
}
