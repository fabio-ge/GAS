package com.fabio.gas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabio.gas.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Integer>{
    
}
