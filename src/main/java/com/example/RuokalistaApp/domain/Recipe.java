package com.example.RuokalistaApp.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Pakollinen kenttä")
	private String name;
	
	@ManyToMany
	@JoinTable(
			name="recipe_ingredients",
			joinColumns = @JoinColumn(name="recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "food_item_id"))
	private Set<FoodItem> foodIngredients;
	
	@Column (name="cooking_time")
	private String cookingTime;
	private String preparation;
	@Column (name="link_to_webpage")
	private String linkToWebpage;
	
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	public Recipe() {
		super();
	}

	
	public Recipe(String name, Set<FoodItem> foodIngredients, String cookingTime, String preparation,
			String linkToWebpage, Category category) {
		super();
		this.name = name;
		this.foodIngredients = foodIngredients;
		this.cookingTime = cookingTime;
		this.preparation = preparation;
		this.linkToWebpage = linkToWebpage;
		this.category = category;
	}

	
	public Recipe(String name, Set<FoodItem> foodIngredients, String cookingTime, String preparation,
			String linkToWebpage) {
		super();
		this.name = name;
		this.foodIngredients = foodIngredients;
		this.cookingTime = cookingTime;
		this.preparation = preparation;
		this.linkToWebpage = linkToWebpage;
	}
	
	

	public Recipe(String name, String cookingTime, String preparation, String linkToWebpage,
			Category category) {
		super();
		this.name = name;
		this.cookingTime = cookingTime;
		this.preparation = preparation;
		this.linkToWebpage = linkToWebpage;
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setFoodIngredients(Set<FoodItem> foodIngredients) {
		this.foodIngredients = foodIngredients;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<FoodItem> getFoodIngredients() {
		return foodIngredients;
	}

	public void setIngredients(Set<FoodItem> ingredients) {
		this.foodIngredients = ingredients;
	}

	public String getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public String getLinkToWebpage() {
		return linkToWebpage;
	}

	public void setLinkToWebpage(String linkToWebpage) {
		this.linkToWebpage = linkToWebpage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Recept [id=" + id + ", ingredients=" + foodIngredients + ", cookingTime=" + cookingTime + ", preparation="
				+ preparation + ", linkToWebpage=" + linkToWebpage + ", category=" + category + "]";
	}
	
	
	
	
}
