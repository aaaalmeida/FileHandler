package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.repository.CursoRepository;
import com.multitech.FileHandler.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form")
public class FormController {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/livro")
    public String formAddLivro(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "addLivroForm";
    }

    @GetMapping("/curso")
    public String formAddCurso(Model model) {
        return "addCursoForm";
    }

    @GetMapping("/login")
    public String formLogin(Model model) {
        return "login";
    }
}
