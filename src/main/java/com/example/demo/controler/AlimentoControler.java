package com.example.demo.controler;

import com.example.demo.DTO.response.AlimentoEquivalenteDTO;
import com.example.demo.model.entity.Alimento;
import com.example.demo.model.repositories.AlimentoGrupoRepository;
import com.example.demo.model.repositories.AlimentoRepository;
import com.example.demo.service.AlimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/api/alimentos")
public class AlimentoControler {

    private final AlimentoRepository alimentoRepository;
    private final AlimentoGrupoRepository alimentoGrupoRepository;
    private final AlimentoService alimentoService;

    public AlimentoControler(AlimentoRepository alimentoRepository,
                             AlimentoGrupoRepository alimentoGrupoRepository,
                             AlimentoService alimentoService) {
        this.alimentoRepository = alimentoRepository;
        this.alimentoGrupoRepository = alimentoGrupoRepository;
        this.alimentoService = alimentoService;
    }

    @GetMapping
    public Iterable<Alimento> getAll() {
        return alimentoRepository.findAll();
    }

    // A rota existente filtra por categoria, não por alimento_id.
    @GetMapping(path = "/{categoriaId}")
    public Iterable<Alimento> getByCategory(@PathVariable Long categoriaId) {
        return alimentoRepository.findByCategoria(categoriaId);
    }

    @GetMapping(path = "/{id}/equivalentes")
    public List<AlimentoEquivalenteDTO> getAlimentosEquivalentes(
            @PathVariable Long id, @RequestParam(defaultValue = "100") BigDecimal gramas) {
        return alimentoService.calcularEquivalencias(id, gramas);
    }

    @GetMapping(path = "/{id}/grupos")
    public List<String> getGruposDoAlimento(@PathVariable Long id) {
        if (!alimentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado.");
        }
        return alimentoGrupoRepository.findAllByAlimentoIdOrderByCodigoAsc(id).stream()
                .map(relacao -> relacao.getCodigo())
                .toList();
    }
}
