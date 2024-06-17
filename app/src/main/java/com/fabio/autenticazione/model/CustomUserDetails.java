package com.fabio.autenticazione.model;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fabio.autenticazione.config.Ruoli;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        String auth = mapToRuolo(user.getTipo());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();    
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive() == 1;
    }

    private String mapToRuolo(int tipo) {
        switch (tipo) {
            case Ruoli.GASISTA:
                return "GASISTA";                
        
            case Ruoli.REFERENTE:
                return "REFERENTE";
                
            case Ruoli.ADMIN:
                return "ADMIN";
                
            default:
                return "GASISTA";
                
        }
    }
    
}
