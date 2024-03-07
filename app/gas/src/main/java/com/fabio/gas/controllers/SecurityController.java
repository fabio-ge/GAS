package com.fabio.gas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fabio.gas.DTO.UserDTO;
import com.fabio.gas.services.UserService;



@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("registration")
    public String getMethodName() {
        return "registration";
    }
    

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserDTO newUser) {
        userService.addUser(newUser);
        return "redirect:/login?success";
    }
    

}
