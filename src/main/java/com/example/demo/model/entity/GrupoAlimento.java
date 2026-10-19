package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo", schema = "taco_import")
public class GrupoAlimento {

    @Id
    @Column(name = "codigo", nullable = false)
    private String codigo;

    public GrupoAlimento() {
    }

    public GrupoAlimento(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}
