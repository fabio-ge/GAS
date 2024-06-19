package com.fabio.autenticazione.DTO;

public record ProdottoDTO(int id, 
                          String nome, 
                          String descrizione,
                          Double prezzoUnitario,
                          TipoQuantitaDTO tipoQuantita) {
    
}
