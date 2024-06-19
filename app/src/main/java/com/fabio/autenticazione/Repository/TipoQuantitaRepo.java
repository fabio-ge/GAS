package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.autenticazione.model.TipoQuantita;

public interface TipoQuantitaRepo extends JpaRepository<TipoQuantita, Integer>{
    
}
