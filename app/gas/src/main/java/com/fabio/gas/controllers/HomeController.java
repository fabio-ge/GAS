package com.fabio.gas.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping("home")
    public String home(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        model.addAttribute("user", username);
        return "home";
    }
    
}
