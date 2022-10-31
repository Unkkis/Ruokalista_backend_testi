package com.example.RuokalistaApp.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RuokalistaApp.domain.Category;
import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItem;
import com.example.RuokalistaApp.domain.FoodItemRepository;
import com.example.RuokalistaApp.domain.Recipe;
import com.example.RuokalistaApp.domain.RecipeRepository;

@RestController
public class RecipesRestController {
	
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private FoodItemRepository ingredientRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	//search and return all recipes from database
	@GetMapping("/api/all")
	public Iterable<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}
	
	//return 1 random recipe from database
	@GetMapping({"/api", "/api/random_one"})
	public Recipe getOneRandomRecipe(){
		ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
		Collections.shuffle(allRecipes);
		Recipe oneRecipe = allRecipes.get(0);
		return oneRecipe;
	}
	
	//return 7 random recipes from database
	@GetMapping("/api/random_{number}days")
	public  Iterable<Recipe> getSevenRecipes(@PathVariable("number")int number){
		ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
		Collections.shuffle(allRecipes);
		int howManyRecipes = number;
		ArrayList<Recipe> sevenRecipes = new ArrayList<Recipe>();
		for (int i = 0; i<howManyRecipes; i++) {
			sevenRecipes.add(allRecipes.get(i));
		}
		return sevenRecipes;
	}
		
	//find recipe with ID
	@GetMapping("/api/recipes/{id}")
	public Optional<Recipe> findRecipeWithId(@PathVariable("id")Long Id){
		return recipeRepository.findById(Id);
	}
	
	//delete recipe by ID
	@DeleteMapping("/api/recipes/{id}")
	public Iterable<Recipe> deleteRecipe(@PathVariable("id")Long id) {
		recipeRepository.deleteById(id);
		return recipeRepository.findAll();
	}
	
	//add new recipe
	@PostMapping("/api/recipes")
	public Recipe newRecipe(@RequestBody Recipe newRecipe) {
		return recipeRepository.save(newRecipe);
	}
	
	//edit recipe
	@PutMapping("/api/recipes/{id}")
	public Recipe editRecipe(@RequestBody Recipe editedRecipe, @PathVariable Long id) {
		editedRecipe.setId(id);
		return recipeRepository.save(editedRecipe);
	}
	
	//find and list all Ingredients in database
	@GetMapping("/api/ingredients")
	public Iterable<FoodItem> getAllIngredients(){
		return ingredientRepository.findAll();
	}
	//add ingredients to DB
	@PostMapping("/api/ingredients")
	public FoodItem newFoodItem(@RequestBody FoodItem newFoodItem) {
		if (ingredientRepository.findByName(newFoodItem.getName()) == newFoodItem) {
			return ingredientRepository.findByName(newFoodItem.getName());
					}
		else {
		return ingredientRepository.save(newFoodItem);
		}
	}
	
	//find ingredient by name
	@GetMapping("/api/ingredients/{name}")
	public FoodItem findIngredientByName(@PathVariable("name")String name) {
		return ingredientRepository.findByName(name);
	}
	
	//find and list all receipts by category
	@GetMapping("/api/categories")
	public Iterable<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	
}
