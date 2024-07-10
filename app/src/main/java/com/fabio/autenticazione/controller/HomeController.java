package com.fabio.autenticazione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fabio.autenticazione.DTO.RegistrationRequestDTO;
import com.fabio.autenticazione.model.User;
import com.fabio.autenticazione.service.AcquistoOrdineService;
import com.fabio.autenticazione.service.OrdineService;
import com.fabio.autenticazione.service.UserService;


import java.util.List;



@Controller
@RequestMapping("/")
public class HomeController {
    
    private final UserService userService;
    private final OrdineService ordineService;
    private final AcquistoOrdineService acquistoOrdineService;


    public HomeController(UserService userService,
                          OrdineService ordineService,
                          AcquistoOrdineService acquistoOrdineService) {
        this.userService = userService;
        this.ordineService = ordineService;
        this.acquistoOrdineService = acquistoOrdineService;
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
        
        setCurrentUserOnModel(model);

        return "home";
    }

    @GetMapping("/init")
    public String initContent() {
        //Di default mostro i contenuti per il gasista
        return "redirect:/gasactions";
    }

    @GetMapping("/abilita")
    public String abilita(Model model) {
        model.addAttribute("utenti",userService.getAllUsers());
        setCurrentUserOnModel(model);
        return "fragments :: utentelist-admin";
    }

    @GetMapping("/refactions")
    public String getSezioneReferente(Model model) {
        setCurrentUserOnModel(model);
        return "fragments/referente :: referente-section";
    }

    @GetMapping("/gasactions")
    public String getSezioneGas(Model model) {
        setCurrentUserOnModel(model);
        model.addAttribute("ordini", ordineService.getAllOrdiniAttivi());
        model.addAttribute("ordiniFatti",acquistoOrdineService.getAcquistiByUser());
        System.out.println(acquistoOrdineService.getAcquistiByUser().toString());
        return "fragments/gasista :: gasista-section";
    }

    private void setCurrentUserOnModel(Model model) {
        User authUser = userService.getCurrentUser();
        
        if(authUser != null){
            model.addAttribute("user",authUser);
        }else {
            model.addAttribute("user", null);
        }
    }
    
    
    
}
