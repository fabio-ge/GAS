package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.autenticazione.model.Ordine;

public interface OrdineRepo extends JpaRepository<Ordine,Integer>{
    
}
