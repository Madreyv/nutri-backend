package com.example.demo.controler;

import com.example.demo.model.entity.GrupoAlimento;
import com.example.demo.model.repositories.GrupoAlimentoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/grupos")
public class GrupoAlimentoControler {

    private final GrupoAlimentoRepository grupoAlimentoRepository;

    public GrupoAlimentoControler(GrupoAlimentoRepository grupoAlimentoRepository) {
        this.grupoAlimentoRepository = grupoAlimentoRepository;
    }

    @GetMapping
    public Iterable<GrupoAlimento> getAll() {
        return grupoAlimentoRepository.findAll();
    }
}
