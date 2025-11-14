package com.multitech.FileHandler.service;

import com.multitech.FileHandler.domain.Aluno;
import com.multitech.FileHandler.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listAll(){
        return alunoRepository.findAll();
    }

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    public void deleteAlunoById(Long id) {
        alunoRepository.deleteById(id);
    }
}
