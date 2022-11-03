package com.example.RuokalistaApp.web;

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
	
	//add (+modify) fooditems
	
	//add category
	

	//add, edit, delete USERS
	
}
