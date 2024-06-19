package com.fabio.autenticazione.DTO;

import java.util.List;

public record FornitoreDTO(Integer id,
                           String nome,
                           String descrizione,
                           int cell,
                           String mail, 
                           List<ProdottoDTO> prodotti) {
    
}
