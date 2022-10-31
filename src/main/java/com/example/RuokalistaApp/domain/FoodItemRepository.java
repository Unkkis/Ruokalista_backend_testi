package com.example.RuokalistaApp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {
	FoodItem findByName(String name);
	List<FoodItem> findFoodItemsByName(String name);
}
