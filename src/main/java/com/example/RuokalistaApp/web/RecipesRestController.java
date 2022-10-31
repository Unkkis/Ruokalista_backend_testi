package com.example.RuokalistaApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RuokalistaApp.domain.Recipe;
import com.example.RuokalistaApp.domain.RecipeRepository;

@RestController
public class RecipesRestController {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	//search and return all recipes from database
	@GetMapping("/api")
	public Iterable<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}
	
	
	
}
