package br.com.fiap.fiapeats.external.integration.feign.response;

import br.com.fiap.fiapeats.domain.entities.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoResponse {

    private UUID id;
    private List<ProdutoResponse> produtos;
    private String cliCpf;
    private BigDecimal valor;
    private LocalDateTime dataHoraCriacao;
    private int tempoEspera;

    public PedidoResponse(UUID id, List<ProdutoResponse> produtos, String cliCpf, BigDecimal valor, LocalDateTime dataHoraCriacao, int tempoEspera) {
        this.id = id;
        this.produtos = produtos;
        this.cliCpf = cliCpf;
        this.valor = valor;
        this.dataHoraCriacao = dataHoraCriacao;
        this.tempoEspera = tempoEspera;
    }

    public UUID getId() {
        return id;
    }

    public List<ProdutoResponse> getProdutos() {
        return produtos;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }
}
