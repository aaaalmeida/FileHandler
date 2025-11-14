package com.multitech.FileHandler.service;


import com.multitech.FileHandler.domain.Livro;
import com.multitech.FileHandler.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService{
    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listAll() {
        return livroRepository.findAll();
    }

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> findLivroById(Long id) {
        return livroRepository.findById(id);
    }

    public void deleteLivroById(Long id) {
        livroRepository.deleteById(id);
    }

}
