package com.multitech.FileHandler.repository;

import com.multitech.FileHandler.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l JOIN l.cursos c WHERE c.id = :cursoId")
    List<Livro> findByCursoId(Long cursoId);
}
