package com.multitech.FileHandler.repository;

import com.multitech.FileHandler.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> { }
