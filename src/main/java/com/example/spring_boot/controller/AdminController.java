package com.example.spring_boot.controller;

import com.example.spring_boot.Model.Role;
import com.example.spring_boot.Model.User;
import com.example.spring_boot.Servise.RoleService;
import com.example.spring_boot.Servise.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

	private final UserServiceImpl userService;

	private final RoleService roleService;

	@Autowired
	public AdminController(UserServiceImpl userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping("/admin")
	public String allUser(Model model, @AuthenticationPrincipal User user){
		model.addAttribute("getall",userService.getAllUser());
		model.addAttribute("getAllRoles",roleService.getAllRoles());
		model.addAttribute("user",user);
		return "admin";
	}

	@GetMapping(value = "/admin/new")
	public String newUser(Model model){
		model.addAttribute("user",new User());
		model.addAttribute("roles",roleService.getAllRoles());
		return "new";
	}

	@PostMapping(value = "/admin/add-user")
	public String create(@ModelAttribute("user") User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles){
		Set<Role> roles = new HashSet<>();
		for (String role : checkBoxRoles){
			roles.add(roleService.getRoleByName(role));
		}
		user.setRoles(roles);
		userService.add(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/admin/edit/{id}")
	public String updateUserForm(@PathVariable("id") Long id,Model model){
		User user = userService.getUser(id);
		model.addAttribute("user",user);
		model.addAttribute("roles",roleService.getRole());
		return "/admin";
	}

	@PutMapping(value = "/admin/edit/{id}")
	public String updateUser(@ModelAttribute User user,
	                         @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles){
		Set<Role> roles1 = new HashSet<>();
		for(String role : checkBoxRoles){
			roles1.add(roleService.getRoleByName(role));
			roles1.add(roleService.getRoleByName(role));
		}
		user.setRoles(roles1);
		userService.updateUser(user);
		return "redirect:/admin";
	}

	@DeleteMapping(value = "/admin/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id){
		userService.delete(id);
		return "redirect:/admin";
	}
}
