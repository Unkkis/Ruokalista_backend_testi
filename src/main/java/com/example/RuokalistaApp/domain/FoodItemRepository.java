package com.example.RuokalistaApp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {
	List<FoodItem> findByName(String name);
}
