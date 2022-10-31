package com.example.RuokalistaApp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Recept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(
			name="recept_ingredients",
			joinColumns = @JoinColumn(name="recept_id"),
			inverseJoinColumns = @JoinColumn(name = "food_item_id"))
	private List<FoodItem> foodIngredients;
	
	private String cookingTime;
	private String preparation;
	private String linkToWebpage;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	public Recept() {
		super();
	}

	public Recept(List<FoodItem> ingredients, String cookingTime, String preparation, String linkToWebpage,
			Category category) {
		super();
		this.foodIngredients = ingredients;
		this.cookingTime = cookingTime;
		this.preparation = preparation;
		this.linkToWebpage = linkToWebpage;
		this.category = category;
	}

	public Recept(String cookingTime, String preparation, String linkToWebpage) {
		super();
		this.cookingTime = cookingTime;
		this.preparation = preparation;
		this.linkToWebpage = linkToWebpage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FoodItem> getFoodIngredients() {
		return foodIngredients;
	}

	public void setIngredients(List<FoodItem> ingredients) {
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
