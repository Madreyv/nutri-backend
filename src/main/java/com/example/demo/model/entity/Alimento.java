package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "alimento", schema = "taco_import")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alimento_id")
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "energia", precision = 10, scale = 3)
    private BigDecimal energia;

    @Column(name = "proteina", precision = 10, scale = 3)
    private BigDecimal proteina;

    @Column(name = "lipideos", precision = 10, scale = 3)
    private BigDecimal lipideos;

    @Column(name = "carboidrato", precision = 10, scale = 3)
    private BigDecimal carboidrato;

    @Column(name = "fibra", precision = 10, scale = 3)
    private BigDecimal fibra;

    // Mantém no JSON a categoria como ID, preservando o contrato anterior da API.
    @Column(name = "categoria_id", nullable = false)
    private Long categoria;

    public Alimento() {
    }

    public Alimento(Long id, String descricao, BigDecimal energia, BigDecimal proteina,
                    BigDecimal lipideos, BigDecimal carboidrato, BigDecimal fibra, Long categoria) {
        this.id = id;
        this.descricao = descricao;
        this.energia = energia;
        this.proteina = proteina;
        this.lipideos = lipideos;
        this.carboidrato = carboidrato;
        this.fibra = fibra;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getEnergia() { return energia; }
    public void setEnergia(BigDecimal energia) { this.energia = energia; }
    public BigDecimal getProteina() { return proteina; }
    public void setProteina(BigDecimal proteina) { this.proteina = proteina; }
    public BigDecimal getLipideos() { return lipideos; }
    public void setLipideos(BigDecimal lipideos) { this.lipideos = lipideos; }
    public BigDecimal getCarboidrato() { return carboidrato; }
    public void setCarboidrato(BigDecimal carboidrato) { this.carboidrato = carboidrato; }
    public BigDecimal getFibra() { return fibra; }
    public void setFibra(BigDecimal fibra) { this.fibra = fibra; }
    public Long getCategoria() { return categoria; }
    public void setCategoria(Long categoria) { this.categoria = categoria; }
}
