package com.fabio.autenticazione.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name ="tipo_quantita")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TipoQuantita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy="tipoQuantita")
    List<Prodotto> prodotti;
}
