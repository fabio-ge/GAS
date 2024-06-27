package com.fabio.autenticazione.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "prodotto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prodotto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "descrizione")
    private String descrizione;
    
    @Column(name ="prezzo_unitario")
    private Double prezzoUnitario;
    
    @ManyToOne
    @JoinColumn(name ="id_fornitore")
    private Fornitore fornitore;

    @ManyToOne
    @JoinColumn(name = "tipo_quantita")
    private TipoQuantita tipoQuantita;

    @OneToMany(mappedBy = "prodotto")
    private List<AcquistoOrdine> acquisti;
    
}
