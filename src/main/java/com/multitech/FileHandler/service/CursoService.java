package com.multitech.FileHandler.service;

import com.multitech.FileHandler.domain.Curso;
import com.multitech.FileHandler.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> findCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    public void deleteCursoById(Long id) {
        cursoRepository.deleteById(id);
    }
}
