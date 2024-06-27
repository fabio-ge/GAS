package com.fabio.autenticazione.DTO;

import java.util.List;

public record AcquistoOrdineDTO(String nomeOrdine, String descrizione,List<ProdottoAcquistoDTO> prodotti, int totale) {
    
}
