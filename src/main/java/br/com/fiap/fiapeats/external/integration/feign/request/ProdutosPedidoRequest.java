package br.com.fiap.fiapeats.external.integration.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProdutosPedidoRequest {

    @JsonProperty("sku_number")
    private final String id;

    @JsonProperty("category")
    private final String categoria;

    @JsonProperty("title")
    private final String nome;

    @JsonProperty("description")
    private final String descricao;

    @JsonProperty("unit_price")
    private final BigDecimal valorItem;

    @JsonProperty("quantity")
    private final int quantidade;

    @JsonProperty("unit_measure")
    private final String unidadeMedida;

    @JsonProperty("total_amount")
    private final BigDecimal valorTotal;

    public ProdutosPedidoRequest(
            String id,
            String categoria,
            String nome,
            String descricao,
            BigDecimal valorItem,
            BigDecimal valorTotal) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.valorItem = valorItem;
        this.quantidade = 1;
        this.unidadeMedida = "unit";
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorItem() {
        return valorItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
