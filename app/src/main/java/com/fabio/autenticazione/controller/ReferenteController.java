package com.fabio.autenticazione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.autenticazione.model.Fornitore;
import com.fabio.autenticazione.service.FornitoreService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/ref")
public class ReferenteController {

    private final FornitoreService fornitoreService;

    public ReferenteController(FornitoreService fornitoreService) {
        this.fornitoreService = fornitoreService;
    }
    
    @GetMapping("/inserisci")
    public String inserisciFornitore(Model model){
        model.addAttribute("newFornitore", new Fornitore());
    
        return "fragments :: nuovo-fornitore";
    }

    @ResponseBody
    @PostMapping("/inserisci")
    public String salvaNuovoFornitore(Fornitore fornitore) {
        
        fornitoreService.saveNewFornitore(fornitore);
        return """
                <div onclick='this.remove()' style="background-color: green; color: ghostwhite;">
                    Fornitore salvato con successo
                </div>
                """;
    }
    
}
