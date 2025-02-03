package br.com.fiap.fiapeats.external.integration.feign.response;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoResponse {

    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String categoria;

    public ProdutoResponse(UUID id, String nome, String descricao, BigDecimal valor, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }
}

