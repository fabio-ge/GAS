package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Date;
import com.fabio.autenticazione.model.Ordine;

public interface OrdineRepo extends JpaRepository<Ordine,Integer>{
   
    @Query("""
            SELECT o
              FROM Ordine o
             WHERE :oggi between o.inizio AND o.fine
            """)
    List<Ordine> getAllOrdiniAttivi(Date oggi); 
}
