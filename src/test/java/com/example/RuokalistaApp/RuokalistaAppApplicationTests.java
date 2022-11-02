package com.example.RuokalistaApp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.RuokalistaApp.web.RecipeController;
import com.example.RuokalistaApp.web.RecipesRestController;

@SpringBootTest
class RuokalistaAppApplicationTests {

	@Autowired
	private RecipeController recipeController;
	@Autowired
	private RecipesRestController recipeRestController;
		
	@Test
	void contextLoads() {
		assertThat(recipeController).isNotNull();
		assertThat(recipeRestController).isNotNull();
	}

}
