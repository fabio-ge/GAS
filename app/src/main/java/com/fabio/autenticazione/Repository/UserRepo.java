package com.fabio.autenticazione.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.autenticazione.model.User;

import java.util.Optional;
import java.util.List;


public interface UserRepo extends JpaRepository<User,Integer>{

    Optional<User> findByEmail(String email);

    List<User> findByIsActive(int isActive);

    boolean existsByEmail(String email);
    
}
