package com.aulajava.aulajava.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulajava.aulajava.Models.Aluno;

public interface RepositoryAluno extends JpaRepository<Aluno,Long>{
    
}
