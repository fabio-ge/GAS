package com.fabio.autenticazione.service;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.ProdottoDTO;
import com.fabio.autenticazione.DTO.ProdottoSaveDTO;
import com.fabio.autenticazione.Repository.FornitoreRepo;
import com.fabio.autenticazione.Repository.ProdottoRepo;
import com.fabio.autenticazione.Repository.TipoQuantitaRepo;
import com.fabio.autenticazione.model.Fornitore;
import com.fabio.autenticazione.model.Prodotto;
import com.fabio.autenticazione.model.TipoQuantita;

import java.util.NoSuchElementException;

@Service
public class ProdottoService {
    
    private final ProdottoRepo prodottoRepo;
    private final TipoQuantitaRepo tipoQuantitaRepo;
    private final FornitoreRepo fornitoreRepo;

    public ProdottoService(ProdottoRepo prodottoRepo,
                          TipoQuantitaRepo tipoQuantitaRepo,
                          FornitoreRepo fornitoreRepo) {
        this.prodottoRepo = prodottoRepo;
        this.tipoQuantitaRepo = tipoQuantitaRepo;
        this.fornitoreRepo = fornitoreRepo;
    }

    public void deleteProdotto(int id) {
        prodottoRepo.deleteById(id);
    }

    public Prodotto saveNewProdotto(int idFornitore,ProdottoSaveDTO prodottoToSave) {
        
        var prodotto = new Prodotto();
        prodotto.setDescrizione(prodottoToSave.descrizione());
        prodotto.setNome(prodottoToSave.nome());
        prodotto.setPrezzoUnitario(prodottoToSave.prezzoUnitario());

        TipoQuantita t = tipoQuantitaRepo.findById(prodottoToSave.idTipo()).orElseThrow(() -> new NoSuchElementException("Non trovo il tipo"));
        prodotto.setTipoQuantita(t);

        Fornitore f = fornitoreRepo.findById(idFornitore).orElseThrow(() -> new NoSuchElementException("Non trovo il fornitore"));
        prodotto.setFornitore(f);

        return prodottoRepo.save(prodotto);

    }
}
