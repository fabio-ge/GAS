package com.fabio.autenticazione.service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.OrdineSaveDTO;
import com.fabio.autenticazione.DTO.ProdottoDTO;
import com.fabio.autenticazione.DTO.TipoQuantitaDTO;
import com.fabio.autenticazione.DTO.OrdineDTO;
import com.fabio.autenticazione.Repository.FornitoreRepo;
import com.fabio.autenticazione.Repository.OrdineRepo;
import com.fabio.autenticazione.Repository.ProdottoOrdineRepo;
import com.fabio.autenticazione.Repository.ProdottoRepo;
import com.fabio.autenticazione.model.Fornitore;
import com.fabio.autenticazione.model.Ordine;
import com.fabio.autenticazione.model.Prodotto;
import com.fabio.autenticazione.model.ProdottoOrdine;
import java.util.List;
import java.util.ArrayList;

@Service
public class OrdineService {
    
    private final OrdineRepo ordineRepo;
    private final FornitoreRepo fornitoreRepo;
    private final ProdottoOrdineRepo prodottoOrdineRepo;
    private final ProdottoRepo prodottoRepo;

    public OrdineService(OrdineRepo ordineRepo, 
                         FornitoreRepo fornitoreRepo,
                         ProdottoOrdineRepo prodottoOrdineRepo,
                         ProdottoRepo prodottoRepo) {
        this.ordineRepo = ordineRepo;
        this.fornitoreRepo = fornitoreRepo;
        this.prodottoOrdineRepo = prodottoOrdineRepo;
        this.prodottoRepo = prodottoRepo;
    }

    public void saveNewOrdine(OrdineSaveDTO ordineToSave) {
        var ordine = new Ordine();
        ordine.setDescrizione(ordineToSave.descrizione());
        ordine.setInizio(java.sql.Date.valueOf(ordineToSave.inizio()));
        ordine.setFine(java.sql.Date.valueOf(ordineToSave.fine()));

        Fornitore fornitore = fornitoreRepo.findById(ordineToSave.idFornitore()).orElseThrow(() -> new NoSuchElementException("Non trovo il fornitore"));
        ordine.setFornitore(fornitore);

        Ordine ordineSalvato = ordineRepo.save(ordine);
        int idOrdine = ordineSalvato.getId();

        ordineToSave.prodotti().stream().forEach(p -> {
            prodottoOrdineRepo.save(new ProdottoOrdine(Integer.valueOf(p),idOrdine));
        });
        
    }

    public List<OrdineDTO> getAllOrdiniAttivi() {
        Date today = new Date();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");

        return ordineRepo
                .getAllOrdiniAttivi(today)
                .stream()
                .map(o -> {
                    int idOrdine = o.getId();
                    List<ProdottoOrdine> prodottiOrdine = prodottoOrdineRepo.findByIdOrdine(idOrdine);
                    List<ProdottoDTO> prodottiDisponibili = new ArrayList<>();

                    prodottiOrdine.forEach(po ->{
                        Prodotto p = prodottoRepo.findById(po.getIdProdotto()).get();
                        ProdottoDTO pDTO = new ProdottoDTO(po.getIdProdotto(), p.getNome(), p.getDescrizione(), p.getPrezzoUnitario(), new TipoQuantitaDTO(p.getTipoQuantita().getId(),p.getTipoQuantita().getDescrizione()));
                    
                        prodottiDisponibili.add(pDTO);
                    });

                    return new OrdineDTO(idOrdine,o.getDescrizione(), formatter.format(o.getInizio()), formatter.format(o.getFine()), o.getFornitore().getNome(),prodottiDisponibili);
                })
                .toList();
    }

    public List<OrdineDTO> getAllOrdini() {
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");

        return ordineRepo
                    .findByOrderByFineDesc()
                    .stream()
                    .map(o -> new OrdineDTO(o.getId(),o.getDescrizione(), formatter.format(o.getInizio()), formatter.format(o.getFine()), o.getFornitore().getNome(),null)
                    ).toList();
    }
}
