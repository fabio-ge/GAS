package com.fabio.autenticazione.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcquistoOrdineDTO {
    private Integer idOrdine;
    private String nomeOrdine;
    private String descrizione;
    private List<ProdottoAcquistoDTO> prodotti;
    private Double totale;
}
