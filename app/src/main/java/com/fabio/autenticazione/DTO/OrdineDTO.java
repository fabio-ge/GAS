package com.fabio.autenticazione.DTO;

import java.util.Date;
import java.util.List;

public record OrdineDTO(int id,String descrizione, String inizio, String fine, String nomeFornitore,List<ProdottoDTO> prodottiDisponibili) {
    
}
