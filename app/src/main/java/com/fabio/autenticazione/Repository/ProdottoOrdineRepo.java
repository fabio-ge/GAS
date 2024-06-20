package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.autenticazione.model.ProdottoOrdine;
import com.fabio.autenticazione.model.ProdottoOrdinePK;

public interface ProdottoOrdineRepo extends JpaRepository<ProdottoOrdine,ProdottoOrdinePK>{
    
}
