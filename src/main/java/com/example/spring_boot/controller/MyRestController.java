package com.example.spring_boot.controller;

import com.example.spring_boot.Model.Role;
import com.example.spring_boot.Model.User;
import com.example.spring_boot.Servise.RoleService;
import com.example.spring_boot.Servise.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class MyRestController {

	private final UserService userService;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public MyRestController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("")
	public ResponseEntity<List<User>> showAllUser(){
		List<User> user = userService.getAllUser();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}


	@PostMapping(value = "/create")
	public ResponseEntity<User> newUser(@RequestBody User user){
		HttpHeaders headers = new HttpHeaders();
		userService.add(user);
		return new ResponseEntity<>(user,headers,HttpStatus.CREATED);
	}

	@GetMapping("getUserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		User user = userService.getUser(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	@PutMapping("/edit")
	public ResponseEntity<User> editUser(@RequestBody User user){

		userService.updateUser(user);
		System.out.println(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
