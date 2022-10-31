package com.example.RuokalistaApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItemRepository;
import com.example.RuokalistaApp.domain.ReceptRepository;

@Controller
public class ReceptController {
	@Autowired
	private ReceptRepository receptRepository;
	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	
	//mainpage
	@GetMapping(value = {"/", "/main", "/index"})
	public String recept(Model model) {
		return "mainpage";
	}
	
	//list all recepts
	
	//add recept
	
	//edit recept
	
	//save recept
	
	//delete recept
	
	//add (+modify) fooditems
	
	//add category
	

	
	//edit
	
}
