package br.com.fiap.fiapeats.domain.entities;

import br.com.fiap.fiapeats.external.integration.feign.response.PagamentoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private UUID id;
    private List<Produto> produtos;
    private String cpf;
    private BigDecimal valor;
    private String statusOrdem;
    private PagamentoPedido pagamento;
    private LocalDateTime dataCriacao;
    private int tempoEspera;

    public Pedido(UUID id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public Pedido(UUID id, List<Produto> produtos, String cpf, BigDecimal valor, String statusOrdem, PagamentoPedido pagamento, LocalDateTime dataCriacao, int tempoEspera) {
        this.id = id;
        this.produtos = produtos;
        this.cpf = cpf;
        this.valor = valor;
        this.statusOrdem = statusOrdem;
        this.pagamento = pagamento;
        this.dataCriacao = dataCriacao;
        this.tempoEspera = tempoEspera;
    }

    public UUID getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getStatusOrdem() {
        return statusOrdem;
    }

    public PagamentoPedido getPagamento() {
        return pagamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }
}
