package com.example.RuokalistaApp.domain;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<ApplicationUser, Long>{

	ApplicationUser findByUsername(String username);
}
