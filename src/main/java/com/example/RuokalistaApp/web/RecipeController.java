package com.example.RuokalistaApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItemRepository;
import com.example.RuokalistaApp.domain.RecipeRepository;

@Controller
public class RecipeController {
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	
	//mainpage
	@GetMapping(value = {"/", "/main", "/index"})
	public String recipe(Model model) {
		return "mainpage";
	}
	
	//list all recipes
	
	//add recipe
	
	//edit recipe
	
	//save recipe
	
	//delete recipe
	
	//add (+modify) fooditems
	
	//add category
	

	//add, edit, delete USERS
	
}
