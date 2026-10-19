package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "alimento_grupo", schema = "taco_import")
@IdClass(AlimentoGrupoId.class)
public class AlimentoGrupo {

    @Id
    @Column(name = "alimento_id", nullable = false)
    private Long alimentoId;

    @Id
    @Column(name = "codigo", nullable = false)
    private String codigo;

    public AlimentoGrupo() {
    }

    public Long getAlimentoId() { return alimentoId; }
    public void setAlimentoId(Long alimentoId) { this.alimentoId = alimentoId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}
