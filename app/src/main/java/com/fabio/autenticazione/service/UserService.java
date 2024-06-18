package com.fabio.autenticazione.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fabio.autenticazione.DTO.RegistrationRequestDTO;
import com.fabio.autenticazione.Repository.UserRepo;
import com.fabio.autenticazione.config.Ruoli;
import com.fabio.autenticazione.model.User;
import com.fabio.autenticazione.model.UserConRuolo;

import java.util.Date;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveNewUser(RegistrationRequestDTO userReq) {
        User user = new User();
        user.setCognome(userReq.cognome());
        user.setNome(userReq.nome());
        user.setEmail(userReq.email());
        user.setPassword(passwordEncoder.encode(userReq.password()));
        user.setIsActive(0);
        user.setTipo(Ruoli.GASISTA);
        user.setRegistrationDate(Date.from(Instant.now()));
        userRepo.save(user);
    }

    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    public List<User> getAllInactiveUsers() {
        return userRepo.findByIsActive(0);
    }

    public List<UserConRuolo> getAllUsers() {
        return userRepo.findAll().stream().map(utente -> {
            
            var u = new UserConRuolo(utente);
            u.setRuolo(getRuolo(utente.getTipo()));

            return u;
        }).toList();
    }

    public UserConRuolo abilitaUser(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Nessuno user trovato"));
    
        user.setIsActive(1);
        userRepo.save(user);

        var userConRuolo = new UserConRuolo(user);
        userConRuolo.setRuolo(getRuolo(user.getTipo()));
        return userConRuolo; 
    }

    public UserConRuolo promuoviUser(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Nessuno user trovato"));
    
        user.setTipo(Ruoli.REFERENTE);
        userRepo.save(user);

        var userConRuolo = new UserConRuolo(user);
        userConRuolo.setRuolo(getRuolo(user.getTipo()));
        return userConRuolo; 
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            User authenticatedUser = userRepo.findByEmail(authentication.getName()).orElseThrow(() -> new NoSuchElementException("non trovo nessuno user"));
            System.out.println("Ho trovato l' utente con username: "+authenticatedUser.getEmail());
            return authenticatedUser;
        }

        System.out.println("non ho trovato utenti collegati: "+authentication.toString());

        return null;

    }

    public boolean existsUser(String email) {
        return userRepo.existsByEmail(email);
    }

    private String getRuolo(int ruoloId) {
        String ruolo;

        switch(ruoloId) {
            case Ruoli.ADMIN:
                ruolo = "ADMIN";
                break;
            case Ruoli.REFERENTE:
                ruolo = "REFERENTE";
                break;
            default:
                ruolo = "GASISTA";
        }

        return ruolo;
    }

    
}
