package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.autenticazione.model.AcquistoOrdine;
import com.fabio.autenticazione.model.User;

import java.util.List;


public interface AcquistoOrdineRepo extends JpaRepository<AcquistoOrdine,Integer>{
    List<AcquistoOrdine> findByUser(User user);  
}
