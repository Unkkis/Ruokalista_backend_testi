package com.example.RuokalistaApp.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Recept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private List<FoodItem> ingredients;
	
	private String cookingTime;
	private String preparation;
	private String linkToWebpage;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
}
