package com.example.RuokalistaApp.web;

import java.util.ArrayList;
import java.util.Collections;


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
	public Iterable<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}
	
	//return 7 random recipes from database
	@GetMapping("/api/random_week")
	public  Iterable<Recipe> getSevenRecipes(){
		ArrayList<Recipe> sevenRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
		Collections.shuffle(sevenRecipes);
		return sevenRecipes;
		
		
	}
	
	
}
