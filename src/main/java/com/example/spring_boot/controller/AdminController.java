package com.example.spring_boot.controller;

import com.example.spring_boot.Model.Role;
import com.example.spring_boot.Model.User;
import com.example.spring_boot.Servise.RoleService;
import com.example.spring_boot.Servise.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController {

	private final UserServiceImpl userService;

	private final RoleService roleService;

	@Autowired
	public AdminController(UserServiceImpl userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping("/users")
	public String allUser(Model model){
		model.addAttribute("getall",userService.getAllUser());
		model.addAttribute("roles",roleService.getRole().toString());
		return "/admin/users";
	}

	@GetMapping("/new")
	public String newUser(Model model){
		List<Role> roles = roleService.getRole();
		model.addAttribute("user",new User());
		roles.forEach(System.out::println);
		return "/admin/new";
	}

	@PostMapping()
	public String create(@RequestParam(value = "ADMIN",required = false) String ADMIN,
			@RequestParam(value = "USER",required = false) String USER,
			@ModelAttribute("user") User user){
		Set<Role> roles = new HashSet<>();
		if (ADMIN != null){
			roles.add(new Role(2L,ADMIN));
		}
		if (USER != null){
			roles.add(new Role(1L,USER));
		}
		if (ADMIN == null && USER == null){
			roles.add(new Role(1L,USER));
		}
		user.setRoles(roles);
		userService.add(user);
		return "redirect:/admin/users";
	}

	@GetMapping(value = "/{id}/user-update")
	public String updateUserForm(@PathVariable("id") Long id,Model model){
		User user = userService.getUser(id);
		model.addAttribute("user",user);
		model.addAttribute("roles",roleService.getRole());
		return "/admin/user-update";
	}

	@PostMapping("/{id}")
	public String updateUser(@RequestParam(value = "ADMIN",required = false) String ADMIN,
	                         @RequestParam(value = "USER",required = false) String USER,
			@ModelAttribute("user") User user){
		Set<Role> roles1 = new HashSet<>();
		if (ADMIN != null){
			roles1.add(new Role(2L,ADMIN));
		}
		if (USER != null){
			roles1.add(new Role(1L,USER));
		}
		if (ADMIN == null && USER == null){
			roles1.add(new Role(1L,USER));
		}
		user.setRoles(roles1);
		userService.updateUser(user);
		return "redirect:/admin/users";
	}

	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id){
		userService.delete(id);
		return "redirect:/admin/users";
	}
}
