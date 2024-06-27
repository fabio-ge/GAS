package com.fabio.autenticazione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabio.autenticazione.DTO.AcquistoSaveDTO;
import com.fabio.autenticazione.DTO.ProdottoAcquistoSaveDTO;
import com.fabio.autenticazione.service.AcquistoOrdineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@Controller
@RequestMapping("/gas")
public class GasistaController {

    private final AcquistoOrdineService acquistoOrdineService;

    public GasistaController(AcquistoOrdineService acquistoOrdineService) {
        this.acquistoOrdineService = acquistoOrdineService;
    }

    @ResponseBody
    @PostMapping(value = "/acquista/{idOrdine}")
    public String acquistaFromOrdine(@PathVariable int idOrdine,
                                     AcquistoSaveDTO payload) {

        List<ProdottoAcquistoSaveDTO> prodottiAcquistati = new ArrayList<>();                                
        payload.prodotti().stream().forEach(p -> {
            System.out.println("Adesso esamino "+p.toString());
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<String,Object> map = mapper.readValue(p, Map.class);
                System.out.println("Aggiungo il prodotto: "+map.get("id")+"-"+map.get("quantita"));
                prodottiAcquistati.add(new ProdottoAcquistoSaveDTO(Integer.valueOf(map.get("id").toString()),Integer.valueOf(map.get("quantita").toString())));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Prodotti Acquistati finali: "+prodottiAcquistati);
        acquistoOrdineService.saveNewAcquisto(idOrdine,prodottiAcquistati);


        return "Prodotti acquistati con successo";
    }
    
}
