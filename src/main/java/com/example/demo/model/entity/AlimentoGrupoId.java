package com.example.demo.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class AlimentoGrupoId implements Serializable {
    private Long alimentoId;
    private String codigo;

    public AlimentoGrupoId() {
    }

    public AlimentoGrupoId(Long alimentoId, String codigo) {
        this.alimentoId = alimentoId;
        this.codigo = codigo;
    }

    public Long getAlimentoId() { return alimentoId; }
    public void setAlimentoId(Long alimentoId) { this.alimentoId = alimentoId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof AlimentoGrupoId that)) return false;
        return Objects.equals(alimentoId, that.alimentoId) && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() { return Objects.hash(alimentoId, codigo); }
}
