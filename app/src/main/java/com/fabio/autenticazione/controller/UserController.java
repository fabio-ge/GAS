package com.fabio.autenticazione.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.autenticazione.DTO.ErroreDto;
import com.fabio.autenticazione.DTO.RegistrationRequestDTO;
import com.fabio.autenticazione.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public String registerNewUser(RegistrationRequestDTO userReq,
                                  Model model){
        List<ErroreDto> errori = checkRegistration(userReq);
        model.addAttribute("errori",errori);
        model.addAttribute("erroriLabel",errori.stream().map(err -> err.nome()).toList());
        if(errori.size()== 0) {
            userService.saveNewUser(userReq);
        } else {
            model.addAttribute("userPayload",userReq);
        }

        return "home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    @PutMapping("/abilita/{id}")
    public String abilitaUser(@PathVariable int id, Model model) {
        
        model.addAttribute("utente",userService.abilitaUser(id));
        return "fragments :: utente-admin";
    }

    @PutMapping("/promuovi/{id}")
    public String promuoviUser(@PathVariable int id, Model model) {
        /*Promuovi: cioè da semplice GASISTA passi a REFERENTE*/
        model.addAttribute("utente",userService.promuoviUser(id));
        return "fragments :: utente-admin";
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public String cancellaUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "";
    }

    private List<ErroreDto> checkRegistration(RegistrationRequestDTO payload) {
        List<ErroreDto> errori = new ArrayList<>();

        if(payload.nome().isEmpty()){
            errori.add(new ErroreDto("nome", "Il nome è obbligatorio"));
        }
        if(payload.cognome().isEmpty()) {
            errori.add(new ErroreDto("cognome","Il cognome è obbligatorio"));
        }
        if(payload.email().isEmpty()) {
            errori.add(new ErroreDto("email","la mail è obbligatoria"));
        }else if(userService.existsUser(payload.email())) {
            errori.add(new ErroreDto("email","Attenzione, la mail indicata esiste già"));
        }
        if(payload.password().isEmpty()) {
            errori.add(new ErroreDto("password", "scegli una password"));
        }
        
        return errori;
    }

}
