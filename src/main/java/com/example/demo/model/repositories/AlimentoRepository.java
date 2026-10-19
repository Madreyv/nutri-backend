package com.example.demo.model.repositories;


import com.example.demo.model.entity.Alimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlimentoRepository extends CrudRepository<Alimento, Long> {
    List<Alimento> findByCategoria(Long categoria);

    @Query("select distinct a from Alimento a, AlimentoGrupo ag " +
            "where a.id = ag.alimentoId and ag.codigo in :codigos and a.id <> :alimentoId")
    List<Alimento> findSubstitutosDoMesmoGrupo(@Param("alimentoId") Long alimentoId,
                                               @Param("codigos") List<String> codigos);
}
