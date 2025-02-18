package br.com.fiap.fiapeats.domain.enums;

public enum StatusPagamento {
    PENDENTE(1L, "Pendente"),
    APROVADO(2L, "Aprovado"),
    RECUSADO(3L, "Recusado"),
    EM_ANALISE(4L, "Em analise"),
    ESTORNADO(5L, "Estornado"),
    CANCELADO(6L, "Cancelado"),
    DESCONHECIDO(7L, "Desconhecido");

    private final long codigo;
    private final String nome;

    StatusPagamento(long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public static long get(String nome) {
        for (StatusPagamento status : StatusPagamento.values()) {
            if (status.getNome().equals(nome)) {
                return status.codigo;
            }
        }
        throw new IllegalArgumentException("Nome inválido: " + nome);
    }
}
