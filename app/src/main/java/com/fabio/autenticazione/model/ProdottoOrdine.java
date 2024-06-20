package com.fabio.autenticazione.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "prodotto_ordine")
@IdClass(ProdottoOrdinePK.class)
public class ProdottoOrdine {
    @Id
    @Column(name ="id_prodotto")
    private int idProdotto;
    
    @Id
    @Column(name = "id_ordine")
    private int idOrdine;
}
