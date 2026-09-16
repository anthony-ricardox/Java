package com.aulajava.aulajava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulajava.aulajava.Models.Professor;

public interface RepositoryProfessor extends JpaRepository <Professor,Long> {
    
}
