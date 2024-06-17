package com.fabio.autenticazione.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fabio.autenticazione.Repository.UserRepo;
import com.fabio.autenticazione.model.CustomUserDetails;
import com.fabio.autenticazione.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Nessun utente con user: "+username));    
        
        return new CustomUserDetails(user);
    }
    
}
