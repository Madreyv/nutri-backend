package com.example.demo.service;

import com.example.demo.DTO.response.AlimentoEquivalenteDTO;
import com.example.demo.model.entity.Alimento;
import com.example.demo.model.entity.AlimentoGrupo;
import com.example.demo.model.repositories.AlimentoGrupoRepository;
import com.example.demo.model.repositories.AlimentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Comparator;

@Service
public class AlimentoService {
    private static final BigDecimal CEM = BigDecimal.valueOf(100);
    private static final int ESCALA_RESULTADO = 3;

    private final AlimentoRepository alimentoRepository;
    private final AlimentoGrupoRepository alimentoGrupoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository, AlimentoGrupoRepository alimentoGrupoRepository) {
        this.alimentoRepository = alimentoRepository;
        this.alimentoGrupoRepository = alimentoGrupoRepository;
    }

    public List<AlimentoEquivalenteDTO> calcularEquivalencias(Long id, BigDecimal gramas) {
        if (gramas == null || gramas.signum() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gramas deve ser maior que zero.");
        }

        Alimento alimentoBase = alimentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado."));

        BigDecimal energiaPorCemGramas = alimentoBase.getEnergia();
        if (energiaPorCemGramas == null || energiaPorCemGramas.signum() <= 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "O alimento base não possui energia positiva disponível.");
        }

        BigDecimal energiaDaPorcao = energiaPorCemGramas
                .multiply(gramas)
                .divide(CEM, ESCALA_RESULTADO, RoundingMode.HALF_UP);

        List<String> codigosGrupo = alimentoGrupoRepository
                .findAllByAlimentoIdOrderByCodigoAsc(id).stream()
                .map(AlimentoGrupo::getCodigo)
                .distinct()
                .toList();

        if (codigosGrupo.isEmpty()) {
            return List.of();
        }

        return alimentoRepository.findSubstitutosDoMesmoGrupo(id, codigosGrupo).stream()
                .filter(alimento -> alimento.getEnergia() != null && alimento.getEnergia().signum() > 0)
                .sorted(Comparator.comparing(Alimento::getDescricao, String.CASE_INSENSITIVE_ORDER))
                .map(alimento -> montarAlimentoDTO(energiaDaPorcao, alimento))
                .toList();
    }

    private AlimentoEquivalenteDTO montarAlimentoDTO(BigDecimal energiaDaPorcao, Alimento alimento) {
        BigDecimal quantidadeGramas = energiaDaPorcao.multiply(CEM)
                .divide(alimento.getEnergia(), ESCALA_RESULTADO, RoundingMode.HALF_UP);
        BigDecimal energiaEquivalente = alimento.getEnergia().multiply(quantidadeGramas)
                .divide(CEM, ESCALA_RESULTADO, RoundingMode.HALF_UP);

        return new AlimentoEquivalenteDTO(alimento.getId(), alimento.getDescricao(),
                energiaEquivalente, quantidadeGramas);
    }
}
