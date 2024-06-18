package com.fabio.autenticazione.model;

public class UserConRuolo extends User{
    private String ruolo;
    private User user;

    public UserConRuolo(User user) {
        this.user = user;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
