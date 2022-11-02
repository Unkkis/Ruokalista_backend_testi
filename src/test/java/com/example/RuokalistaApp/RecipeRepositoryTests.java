package com.example.RuokalistaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.RuokalistaApp.domain.CategoryRepository;
import com.example.RuokalistaApp.domain.FoodItem;
import com.example.RuokalistaApp.domain.FoodItemRepository;
import com.example.RuokalistaApp.domain.Recipe;
import com.example.RuokalistaApp.domain.RecipeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class RecipeRepositoryTests {
	
	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	FoodItemRepository ingredientRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test //testataan että löytyy nimellä, DB pitäisi sisältää "Jauhelihakeitto" reseptin
	public void findRecipeByName() {
		List<Recipe> recipes = recipeRepository.findByName("Jauhelihakeitto");
		assertEquals(recipes.size(), 1);
	}
	
	@Test //etsitään resepti ID:llä 7, joka pitäisi olla Jauhelihakeitto
	public void findRecipeById() {
		String testRecipe = recipeRepository.findById((long) 7).get().getName();
		assertEquals(testRecipe, "Jauhelihakeitto");
	}
	
	@Test //testataan että reseptillä on ainekset ja kategoria
	public void testAllElementsFromRecept() {
		Recipe testRecipe = recipeRepository.findById((long) 7).get();
		assertEquals(testRecipe.getCategory().getName(), "Keitto" );
		Set<FoodItem> ingredients = testRecipe.getFoodIngredients(); 
		assertEquals(ingredients.size(), 5);
	}
	
	@Test //testataan että voi lisätä ja poistaa reseptin
	public void insertAndDeleteRecipe() {
		//make a new recipe
		Recipe newRecipe = new Recipe("Testi Resepti", "40", "Valmistusohje", "Nettisivu", categoryRepository.findById((long)1).get());
		recipeRepository.save(newRecipe);
		//find that recipe and assert that it matches
		assertEquals(recipeRepository.findByName("Testi Resepti").get(0).getName(), "Testi Resepti");
		//delete the new recipe
		recipeRepository.delete(newRecipe);
		assertEquals(recipeRepository.findByName("Testi Resepti").size(), 0);
	}
	
}
