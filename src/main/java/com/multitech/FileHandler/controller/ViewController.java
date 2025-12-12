package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.repository.CursoRepository;
import com.multitech.FileHandler.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private LivroService livroService;

    @GetMapping("/livro")
    public String findAllLivro(Model model) {
        model.addAttribute("livros", livroService.listAll());
        model.addAttribute("cursoId", null);
        return "listaLivros";
    }

    @GetMapping("/livro/curso/{id}")
    public String findLivroByCursoId(@PathVariable Long id,  Model model) {
        model.addAttribute("livros", livroService.findByIdCurso(id));
        model.addAttribute("cursoId", id);
        return "listaLivros";
    }

    @GetMapping("/livro/curso")
    public String findLivroByCurso(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "listaLivrosPorCurso";
    }

    @GetMapping("/curso")
    public String findAllCurso(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "listaCursos";
    }
}
