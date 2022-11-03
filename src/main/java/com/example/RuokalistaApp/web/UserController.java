package com.example.RuokalistaApp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.RuokalistaApp.domain.ApplicationUser;
import com.example.RuokalistaApp.domain.ApplicationUserRepository;

@Controller
public class UserController {
	
	@Autowired
	ApplicationUserRepository userRepository;
	
	//Users page (list, add and remove users)
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/users")
	public String listAndModifyUsers(Model model) {
		model.addAttribute("newUser", new ApplicationUser());
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
	//add user
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public String addUser(@Valid @ModelAttribute("newUser")ApplicationUser newUser, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) {
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				ApplicationUser newAppUser = new ApplicationUser();
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole("USER");
				if (userRepository.findByUsername(newUser.getUsername()) == null) { // Check if user exists
					userRepository.save(newAppUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "registration";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "registration";
			}
		} else {
			return "registration";
		}
		return "redirect:/login";
		
	}

	
	
	
	
	//add, edit, delete USERS
}
