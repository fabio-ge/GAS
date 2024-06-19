package com.fabio.autenticazione.service;

import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.TipoQuantitaDTO;
import com.fabio.autenticazione.Repository.TipoQuantitaRepo;

import java.util.List;

@Service
public class TipoQuantitaService {
    private final TipoQuantitaRepo tipoQuantitaRepo;

    public TipoQuantitaService(TipoQuantitaRepo tipoQuantitaRepo) {
        this.tipoQuantitaRepo = tipoQuantitaRepo;
    }

    public List<TipoQuantitaDTO> getAllTipi(){
        return tipoQuantitaRepo.findAll()
                .stream()
                .map(t -> {
                    return new TipoQuantitaDTO(t.getId(),t.getDescrizione());
                })
                .toList();
    }
}
