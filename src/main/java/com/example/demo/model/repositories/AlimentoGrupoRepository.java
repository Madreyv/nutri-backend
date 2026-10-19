package com.example.demo.model.repositories;

import com.example.demo.model.entity.AlimentoGrupo;
import com.example.demo.model.entity.AlimentoGrupoId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlimentoGrupoRepository extends CrudRepository<AlimentoGrupo, AlimentoGrupoId> {
    List<AlimentoGrupo> findAllByAlimentoIdOrderByCodigoAsc(Long alimentoId);
}
