package br.com.fiap.fiapeats.domain.enums;

public enum StatusPagamento {
    PENDENTE(1L, "PENDENTE"),
    APROVADO(2L, "APROVADO"),
    RECUSADO(3L, "RECUSADO"),
    EM_ANALISE(4L, "EM_ANALISE"),
    ESTORNADO(5L, "ESTORNADO"),
    CANCELADO(6L, "CANCELADO"),
    DESCONHECIDO(7L, "DESCONHECIDO");

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
