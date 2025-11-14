package com.multitech.FileHandler.service;

import com.multitech.FileHandler.DTO.AlunoDTO;
import com.multitech.FileHandler.crypto.PasswordUtils;
import com.multitech.FileHandler.domain.Aluno;
import com.multitech.FileHandler.domain.Curso;
import com.multitech.FileHandler.repository.AlunoRepository;
import com.multitech.FileHandler.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<Aluno> listAll(){
        return alunoRepository.findAll();
    }

    public Aluno create(AlunoDTO dto) {
        Curso curso = cursoRepository.findById(dto.idCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso not found"));

        byte[] salt = PasswordUtils.nextSalt();
        byte[] hash = PasswordUtils.hashPassword(dto.senha().toCharArray(),salt);

        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setCurso(curso);
        aluno.setEmail(dto.email());
        aluno.setSalt(salt);
        aluno.setSenha(Base64.getEncoder().encodeToString(hash));

        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    public void deleteAlunoById(Long id) {
        alunoRepository.deleteById(id);
    }
}
