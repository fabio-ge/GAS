package com.fabio.autenticazione.service;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.FornitoreDTO;
import com.fabio.autenticazione.DTO.ProdottoDTO;
import com.fabio.autenticazione.DTO.TipoQuantitaDTO;
import com.fabio.autenticazione.Repository.FornitoreRepo;
import com.fabio.autenticazione.model.Fornitore;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FornitoreService {
    private final FornitoreRepo fornitoreRepo;

    public FornitoreService(FornitoreRepo fornitoreRepo) {
        this.fornitoreRepo = fornitoreRepo;
    }

    public void saveNewFornitore(Fornitore fornitore) {
        fornitoreRepo.save(fornitore);
    }

    public List<Fornitore> getAllFornitori() {
        return fornitoreRepo.findAll();
    }

    public FornitoreDTO getFornitoreConProdotti(int id) {
        Fornitore fornitore = fornitoreRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Nessun fornitore trovato"));
        List<ProdottoDTO> prodotti = fornitore.getProdotti()
                                              .stream()
                                              .map(prodotto ->{
                                                return new ProdottoDTO(prodotto.getId(), 
                                                                       prodotto.getNome(), 
                                                                       prodotto.getDescrizione(), 
                                                                       prodotto.getPrezzoUnitario(), 
                                                                       new TipoQuantitaDTO(prodotto.getTipoQuantita().getId(),prodotto.getTipoQuantita().getDescrizione()));
                                              })
                                              .toList();
        return new FornitoreDTO(fornitore.getId(),fornitore.getNome(), fornitore.getDescrizione(), fornitore.getCell(), fornitore.getMail(), prodotti);
    }
}
