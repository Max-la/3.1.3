package com.example.spring_boot.controller;

import com.example.spring_boot.Model.User;
import com.example.spring_boot.Servise.RoleService;
import com.example.spring_boot.Servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model,@AuthenticationPrincipal User user) {
        model.addAttribute("user",user);
        model.addAttribute("roles",user.getRoles());
        return "userpage";
    }

}
