package com.fabio.autenticazione.model;

import java.util.Date;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ordine")
public class Ordine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "inizio")
    private Date inizio;
    @Column(name = "fine")
    private Date fine;
    @Column(name = "descrizione")
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "id_fornitore")
    private Fornitore fornitore;

    @OneToMany(mappedBy = "ordine")
    private List<AcquistoOrdine> acquisti;


}
