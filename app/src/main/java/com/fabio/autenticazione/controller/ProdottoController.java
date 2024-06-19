package com.fabio.autenticazione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.autenticazione.DTO.ProdottoSaveDTO;
import com.fabio.autenticazione.service.ProdottoService;
import com.fabio.autenticazione.service.TipoQuantitaService;

@RequestMapping("/prodotto")
@Controller
public class ProdottoController {

    private final ProdottoService prodottoService;
    private final TipoQuantitaService tipoQuantitaService;

    public ProdottoController(ProdottoService prodottoService,
                              TipoQuantitaService tipoQuantitaService) {
        this.prodottoService = prodottoService;
        this.tipoQuantitaService = tipoQuantitaService;
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public String deleteProdottoById(@PathVariable int id) {

        prodottoService.deleteProdotto(id);
        return "";
    }

    @PostMapping("/{idFornitore}")
    public String aggiungiProdotto(@PathVariable int idFornitore,
                                   ProdottoSaveDTO prodotto,
                                   Model model) {
        model.addAttribute("prodotto",prodottoService.saveNewProdotto(idFornitore, prodotto));
        model.addAttribute("nuovoProdotto", new ProdottoSaveDTO(null, null, null, idFornitore));
        model.addAttribute("idFornitore",idFornitore);
        model.addAttribute("tipi", tipoQuantitaService.getAllTipi());
        return "fragments :: fornitore-prodotto";
    }
    
}
