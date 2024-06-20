package com.fabio.autenticazione.DTO;

import java.util.List;

public record OrdineSaveDTO(String descrizione,String inizio,String fine, List<Integer> prodotti,Integer idFornitore) {
    
}
