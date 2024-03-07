package com.fabio.gas.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fabio.gas.DTO.UserDTO;
import com.fabio.gas.dao.AuthorityRepository;
import com.fabio.gas.dao.UserRepository;
import com.fabio.gas.models.Authority;
import com.fabio.gas.models.User;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired 
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.username())){
            throw new RuntimeErrorException(null, "Utente "+userDTO.username()+" già esistente");
        }
        User user = new User();
        Authority authority = new Authority();
        authority.setAuthority("READ");
        authority.setUsername(userDTO.username());

        user.setUsername(userDTO.username());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setEnabled(0); //Di default gli utenti creati non sono ancora abilitati
        

        userRepository.save(user);
        authorityRepository.save(authority);
    }

    
    
}
