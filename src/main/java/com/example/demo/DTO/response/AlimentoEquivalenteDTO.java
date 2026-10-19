package com.example.demo.DTO.response;

import java.math.BigDecimal;

public class AlimentoEquivalenteDTO {
    private Long id;
    private String nome;
    private BigDecimal energia;
    private BigDecimal quantidadeGramas;

    public AlimentoEquivalenteDTO() { }

    public AlimentoEquivalenteDTO(Long id, String nome, BigDecimal energia, BigDecimal quantidadeGramas) {
        this.id = id;
        this.nome = nome;
        this.energia = energia;
        this.quantidadeGramas = quantidadeGramas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getEnergia() { return energia; }
    public void setEnergia(BigDecimal energia) { this.energia = energia; }
    public BigDecimal getQuantidadeGramas() { return quantidadeGramas; }
    public void setQuantidadeGramas(BigDecimal quantidadeGramas) { this.quantidadeGramas = quantidadeGramas; }
}
