package com.fabio.autenticazione.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.OrdineSaveDTO;
import com.fabio.autenticazione.Repository.FornitoreRepo;
import com.fabio.autenticazione.Repository.OrdineRepo;
import com.fabio.autenticazione.Repository.ProdottoOrdineRepo;
import com.fabio.autenticazione.model.Fornitore;
import com.fabio.autenticazione.model.Ordine;
import com.fabio.autenticazione.model.ProdottoOrdine;

@Service
public class OrdineService {
    
    private final OrdineRepo ordineRepo;
    private final FornitoreRepo fornitoreRepo;
    private final ProdottoOrdineRepo prodottoOrdineRepo;

    public OrdineService(OrdineRepo ordineRepo, 
                         FornitoreRepo fornitoreRepo,
                         ProdottoOrdineRepo prodottoOrdineRepo) {
        this.ordineRepo = ordineRepo;
        this.fornitoreRepo = fornitoreRepo;
        this.prodottoOrdineRepo = prodottoOrdineRepo;
    }

    public void saveNewOrdine(OrdineSaveDTO ordineToSave) {
        var ordine = new Ordine();
        ordine.setDescrizione(ordineToSave.descrizione());
        ordine.setInizio(Date.valueOf(ordineToSave.inizio()));
        ordine.setFine(Date.valueOf(ordineToSave.fine()));

        Fornitore fornitore = fornitoreRepo.findById(ordineToSave.idFornitore()).orElseThrow(() -> new NoSuchElementException("Non trovo il fornitore"));
        ordine.setFornitore(fornitore);

        Ordine ordineSalvato = ordineRepo.save(ordine);
        int idOrdine = ordineSalvato.getId();

        ordineToSave.prodotti().stream().forEach(p -> {
            prodottoOrdineRepo.save(new ProdottoOrdine(Integer.valueOf(p),idOrdine));
        });
        
    }
}
