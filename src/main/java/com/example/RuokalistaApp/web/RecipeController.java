package com.example.RuokalistaApp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItem;
import com.example.RuokalistaApp.domain.FoodItemRepository;
import com.example.RuokalistaApp.domain.Recipe;
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
	public String main() {
		return "mainpage";
	}
	
	//list all recipes
	@GetMapping("/allRecipes")
	public String recipe(Model model) {
		model.addAttribute("recipes", recipeRepository.findAll());
		return "allRecipes";
	}
	
	//add recipe
	@RequestMapping("/add")
	public String addNewRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("ingredients", foodItemRepository.findAll());
		model.addAttribute("newIngredients");
		return "addNewRecipe";
	}
	//save recipe
	@PostMapping("/save")
	public String saveRecipe(@Valid Recipe recipe, BindingResult result, Model model) {
		//read new ingredients and make a list from them
		List<String> newIngredients = Arrays.asList(recipe.getNewIngredients().split(","));
		for (int i = 0; i < newIngredients.size(); i++) {
			String newIngredient = newIngredients.get(i).trim();
			newIngredient = newIngredient.substring(0, 1).toUpperCase() +  newIngredient.substring(1).toLowerCase();
			boolean isInDB = false;
			
			List<FoodItem> allIngredients = (List<FoodItem>) foodItemRepository.findAll();
			for (int a = 0; a<allIngredients.size(); a++) {	
				if (allIngredients.get(a).getName().toUpperCase().equals(newIngredient.toUpperCase())) {
					isInDB = true;
				}
				
			}
			if (isInDB == false) {
			FoodItem newFoodItem = new FoodItem();
			newFoodItem.setName(newIngredient);
			foodItemRepository.save(newFoodItem);
			recipe.getFoodIngredients().add(foodItemRepository.findByName(newIngredient));
			}
		}
		
		try{
		    Integer.parseInt(recipe.getCookingTime());
		}catch (NumberFormatException ex) {
		    result.rejectValue("cookingTime", "err.cookingTime", "Syötä aika numeroina minuuteissa.");
		}
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			model.addAttribute("ingredients", foodItemRepository.findAll());
			return "addNewRecipe";
		}
		recipeRepository.save(recipe);
		return "redirect:allRecipes";
	}
	
	//edit recipe
	@RequestMapping("/edit{id}")
	public String editRecipe(@PathVariable("id")Long id, Model model) {
		model.addAttribute("recipe", recipeRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("ingredients", foodItemRepository.findAll());
		return"editRecipe";
	}
	

	
	//delete recipe
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteRecipe(@PathVariable("id")Long id) {
		recipeRepository.deleteById(id);
		return "redirect:../allRecipes";
	}
	
	//make a random recipe list for a week (7 days)
	@GetMapping("/weekMenu")
	public String generateWeeksMenu(Model model) {
		ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
		Collections.shuffle(allRecipes);
		int howManyRecipes = 7;
		ArrayList<Recipe> sevenRecipes = new ArrayList<Recipe>();
		for (int i = 0; i<howManyRecipes; i++) {
			sevenRecipes.add(allRecipes.get(i));
		}
		model.addAttribute("recipes", sevenRecipes);
		System.out.println(sevenRecipes);
		return "weekMenu";
	}


	
}
