package com.fabio.autenticazione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.autenticazione.DTO.RegistrationRequestDTO;
import com.fabio.autenticazione.model.User;
import com.fabio.autenticazione.service.UserService;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {
    
    private final UserService userService;


    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("user",new RegistrationRequestDTO(null,null,null,null));
        model.addAttribute("errori",List.of());
        model.addAttribute("erroriLabel",List.of());
        
        User authUser = userService.getCurrentUser();
        
        if(authUser != null){
            model.addAttribute("user",authUser);
        }else {
            model.addAttribute("user", null);
        }

        return "home";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Funziona";
    }
    
}
