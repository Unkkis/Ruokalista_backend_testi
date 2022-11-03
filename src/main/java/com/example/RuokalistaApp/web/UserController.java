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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.RuokalistaApp.domain.ApplicationUser;
import com.example.RuokalistaApp.domain.ApplicationUserRepository;
import com.example.RuokalistaApp.domain.UserRegistration;


@Controller
public class UserController {
	
	@Autowired
	ApplicationUserRepository userRepository;
	
	//Users page (list, add and remove users)
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/users")
	public String listAndModifyUsers(Model model) {
		model.addAttribute("newUser", new UserRegistration());
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
	//add user
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/addUser")
	public String addUser(@Valid @ModelAttribute("newuser") UserRegistration newUser, BindingResult bindingResult, Model model) {

		if (!bindingResult.hasErrors()) { // validation errors
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { // check password match
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				ApplicationUser newAppUser = new ApplicationUser();
				newAppUser.setFirstName(newUser.getFirstName());
				newAppUser.setLastName(newUser.getLastName());
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole(newUser.getRole());
				if (userRepository.findByUsername(newUser.getUsername()) == null) { // Check if user exists
					userRepository.save(newAppUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					
					return "users";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				
				return "users";
			}
		} else {
			return "redirect:/users";
		}
		return "redirect:/users";
	}

	//delete user
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deleteUser{id}")
	public String deleteUser(@PathVariable("id")Long id) {
		userRepository.deleteById(id);
		return "redirect:/users";
	}
	

	//edit USERS?
}
