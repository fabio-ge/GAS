package com.fabio.autenticazione.service;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.AcquistoOrdineDTO;
import com.fabio.autenticazione.DTO.ProdottoAcquistoSaveDTO;
import com.fabio.autenticazione.Repository.AcquistoOrdineRepo;
import com.fabio.autenticazione.Repository.OrdineRepo;
import com.fabio.autenticazione.Repository.ProdottoRepo;
import com.fabio.autenticazione.model.AcquistoOrdine;
import com.fabio.autenticazione.model.Ordine;
import com.fabio.autenticazione.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AcquistoOrdineService {
    
    private final AcquistoOrdineRepo acquistoOrdineRepo;
    private final OrdineRepo ordineRepo;
    private final UserService userService;
    private final ProdottoRepo prodottoRepo;

    public AcquistoOrdineService(AcquistoOrdineRepo acquistoOrdineRepo,
                                 OrdineRepo ordineRepo,
                                 UserService userService,
                                 ProdottoRepo prodottoRepo) {
        this.acquistoOrdineRepo = acquistoOrdineRepo;
        this.ordineRepo = ordineRepo;
        this.userService = userService;
        this.prodottoRepo = prodottoRepo;
    }

    public void saveNewAcquisto(int idOrdine,List<ProdottoAcquistoSaveDTO> prodottiAcquistati) {
        
        Ordine ordine = ordineRepo.findById(idOrdine).orElseThrow(() -> new NoSuchElementException("Nessun ordine trovato"));
        User currentUser = userService.getCurrentUser();
        prodottiAcquistati.stream().forEach(pa -> {
            var nuovoAcquisto = new AcquistoOrdine();
            nuovoAcquisto.setOrdine(ordine);
            nuovoAcquisto.setUser(currentUser);
            nuovoAcquisto.setProdotto(prodottoRepo.findById(pa.id()).orElseThrow(() -> new NoSuchElementException("nessun prodotto trovato")));
            nuovoAcquisto.setQuantita(pa.quantita());
            acquistoOrdineRepo.save(nuovoAcquisto);
        });
    }

    public List<AcquistoOrdineDTO> getAcquistiByUser() {

        List<AcquistoOrdine> acquisti = acquistoOrdineRepo.findByUser(userService.getCurrentUser());
        List<AcquistoOrdineDTO> acquistiDTO = new ArrayList<>();
        
        acquisti.stream().forEach(a -> {
            //TODO: ordinare per id ordine e costruire un oggetto, con lista di acquisti, per ogni ordine
        });

        return acquistiDTO;
    }
}
