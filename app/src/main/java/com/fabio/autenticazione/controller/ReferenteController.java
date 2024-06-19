package com.fabio.autenticazione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.autenticazione.DTO.FornitoreDTO;
import com.fabio.autenticazione.DTO.ProdottoSaveDTO;
import com.fabio.autenticazione.model.Fornitore;
import com.fabio.autenticazione.service.FornitoreService;
import com.fabio.autenticazione.service.TipoQuantitaService;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ref")
public class ReferenteController {

    private final FornitoreService fornitoreService;
    private final TipoQuantitaService tipoQuantitaService;

    public ReferenteController(FornitoreService fornitoreService,
                               TipoQuantitaService tipoQuantitaService) {
        this.fornitoreService = fornitoreService;
        this.tipoQuantitaService = tipoQuantitaService;
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

    @GetMapping("/fornitori")
    public String getMethodName(Model model) {

        model.addAttribute("fornitori", fornitoreService.getAllFornitori());
        return "fragments :: fornitori";
    }

    @GetMapping("/prodotti")
    public String getProdottiFornitore(@RequestParam(name = "fornitore") int idFornitore,
                                       Model model) {
        
        FornitoreDTO fornitore = fornitoreService.getFornitoreConProdotti(idFornitore);
        
        model.addAttribute("fornitore",fornitore);
        model.addAttribute("tipi",tipoQuantitaService.getAllTipi());
        model.addAttribute("nuovoProdotto", new ProdottoSaveDTO(null, null, null, idFornitore));
        return "fragments :: fornitore-prodotti";
    }
    
    
}
