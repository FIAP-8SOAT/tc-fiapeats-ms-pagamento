package br.com.fiap.fiapeats.external.integration.feign.response;

import br.com.fiap.fiapeats.domain.entities.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoResponse {

    private UUID id;
    private List<ProdutoResponse> produtos;
    private String cpf;
    private BigDecimal valor;
    private String statusOrdem;
    private PagamentoResponse pagamento;
    private LocalDateTime dataCriacao;
    private int tempoEspera;

    public PedidoResponse(UUID id, List<ProdutoResponse> produtos, String cpf, BigDecimal valor, String statusOrdem, PagamentoResponse pagamento, LocalDateTime dataCriacao, int tempoEspera) {
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

    public List<ProdutoResponse> getProdutos() {
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

    public PagamentoResponse getPagamento() {
        return pagamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }
}
