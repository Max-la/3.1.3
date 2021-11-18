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
}
