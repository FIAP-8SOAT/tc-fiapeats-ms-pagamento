package br.com.fiap.fiapeats.usecases.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutosDTO {

    private UUID id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    private String categoriaDescricao;

    public ProdutosDTO(UUID id, String nome, String descricao, BigDecimal valor, String categoriaDescricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoriaDescricao = categoriaDescricao;
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

    public String getCategoriaDescricao() {
        return categoriaDescricao;
    }
}
