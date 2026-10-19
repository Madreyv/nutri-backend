package com.example.demo.controler;

import com.example.demo.model.entity.CategoriasAlimento;
import com.example.demo.model.repositories.CategoriaAlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/categorias")
public class CategoriasAlimentoControler {

    @Autowired
    private CategoriaAlimentoRepository categoriaAlimentoRepository;

    @GetMapping
    public Iterable<CategoriasAlimento> getAll(){
        return categoriaAlimentoRepository.findAll();
    }
}
