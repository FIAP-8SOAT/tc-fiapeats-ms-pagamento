package br.com.fiap.fiapeats.external.integration.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CriarPagamentoPedidoRequest {

    @JsonProperty("external_reference")
    private final String id;

    @JsonProperty("title")
    private final String nome;

    @JsonProperty("description")
    private final String descricao;

    @JsonProperty("notification_url")
    private final String urlNotificacao;

    @JsonProperty("expiration_date")
    private final String dataExpiracao;

    @JsonProperty("total_amount")
    private final BigDecimal valorTotal;

    @JsonProperty("items")
    private final List<ProdutosPedidoRequest> itens;

    public CriarPagamentoPedidoRequest(
            String id, String urlNotificacao, BigDecimal valorTotal, List<ProdutosPedidoRequest> itens) {
        this.id = id;
        this.nome = "pedido-compo";
        this.descricao = "pedido-compo";
        this.urlNotificacao = urlNotificacao;
        this.dataExpiracao =
                LocalDateTime.now()
                        .plusDays(1)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000-00:00"));
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrlNotificacao() {
        return urlNotificacao;
    }

    public String getDataExpiracao() {
        return dataExpiracao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public List<ProdutosPedidoRequest> getItens() {
        return itens;
    }
}
