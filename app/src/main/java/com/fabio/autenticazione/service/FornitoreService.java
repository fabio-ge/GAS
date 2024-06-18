package com.fabio.autenticazione.service;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.Repository.FornitoreRepo;
import com.fabio.autenticazione.model.Fornitore;

@Service
public class FornitoreService {
    private final FornitoreRepo fornitoreRepo;

    public FornitoreService(FornitoreRepo fornitoreRepo) {
        this.fornitoreRepo = fornitoreRepo;
    }

    public void saveNewFornitore(Fornitore fornitore) {
        fornitoreRepo.save(fornitore);
    }
}
