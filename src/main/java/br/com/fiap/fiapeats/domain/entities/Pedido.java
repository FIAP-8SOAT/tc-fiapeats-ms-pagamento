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

    public UUID getId() {
        return id;
    }

    public List<Produto> getProdutos() {
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
