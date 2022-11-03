package com.example.RuokalistaApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.RuokalistaApp.domain.Category;
import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItem;
import com.example.RuokalistaApp.domain.FoodItemRepository;

@Controller
public class IngredientAndCategoryController {

	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired
	private FoodItemRepository foodItemRepository;
		
	
	//add (+modify) fooditems
	@GetMapping("/ingredients")
	public String allIngredients(Model model) {
		model.addAttribute("ingredient", new FoodItem());
		model.addAttribute("ingredients", foodItemRepository.findAll());
		return "ingredients";
	}
	
	@PostMapping("/addIngredient")
	public String addIngredient(Model model, FoodItem ingredient) {
						
			//Change the name to match the formatting in DB
			String newName = ingredient.getName().trim();
			newName = newName.substring(0, 1).toUpperCase() +  newName.substring(1).toLowerCase();
			
			//Check if there is a ingredient with same name in DB
			List<FoodItem> allIngredients = (List<FoodItem>) foodItemRepository.findAll();
			for (int i = 0; i<allIngredients.size(); i++) {	
				if (allIngredients.get(i).getName().toUpperCase().equals(newName.toUpperCase())) {
					model.addAttribute("isAllready", ingredient);
					System.out.println("Ingredient allready in DB");
					return "redirect:ingredients";
				}

			}
			ingredient.setName(newName);
			foodItemRepository.save(ingredient);
	return "redirect:ingredients";
	}
	
	
	//List all categories
	@GetMapping("/categories")
	public String allCategories(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}
	//add category
	@PostMapping("/addCategory")
	public String addCategory(Model model, Category category) {
						
			//Change the name to match the formatting in DB
			String newName = category.getName().trim();
			newName = newName.substring(0, 1).toUpperCase() +  newName.substring(1).toLowerCase();
			
			//Check if there is a ingredient with same name in DB
			List<Category> allCategories = (List<Category>) categoryRepository.findAll();
			for (int i = 0; i<allCategories.size(); i++) {	
				if (allCategories.get(i).getName().toUpperCase().equals(newName.toUpperCase())) {
					model.addAttribute("isAllready", category);
					System.out.println("Ingredient allready in DB");
					return "redirect:ingredients";
				}

			}
			category.setName(newName);
			categoryRepository.save(category);
	return "redirect:ingredients";
	}
	
}
