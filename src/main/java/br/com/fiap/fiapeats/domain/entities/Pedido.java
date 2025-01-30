package br.com.fiap.fiapeats.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private UUID id;
    private List<Produto> produtos;
    private String cliCpf;
    private BigDecimal valor;
    private LocalDateTime dataHoraCriacao;
    private int tempoEspera;

    public Pedido(
            UUID id,
            List<Produto> produtos,
            String cliCpf,
            BigDecimal valor,
            LocalDateTime dataHoraCriacao,
            int tempoEspera) {
        this.id = id;
        this.produtos = produtos;
        this.cliCpf = cliCpf;
        this.valor = valor;
        this.dataHoraCriacao = dataHoraCriacao;
        this.tempoEspera = tempoEspera;
    }

    public Pedido() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
}
