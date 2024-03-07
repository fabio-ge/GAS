package com.fabio.gas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.gas.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    public boolean existsByUsername(String username);
}
