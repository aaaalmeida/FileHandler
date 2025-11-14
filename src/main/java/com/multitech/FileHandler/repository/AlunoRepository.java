package com.multitech.FileHandler.repository;

import com.multitech.FileHandler.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> { }
