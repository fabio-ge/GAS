package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.autenticazione.model.Prodotto;

public interface ProdottoRepo extends JpaRepository<Prodotto,Integer>{
    
}
