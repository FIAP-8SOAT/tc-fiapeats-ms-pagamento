package br.com.fiap.fiapeats.domain.enums;

public enum StatusPagamento {
    PENDENTE(1, "PENDENTE"),
    APROVADO(2, "APROVADO"),
    RECUSADO(3, "RECUSADO"),
    EM_ANALISE(4, "EM_ANALISE"),
    ESTORNADO(5, "ESTORNADO"),
    CANCELADO(6, "CANCELADO"),
    DESCONHECIDO(7, "DESCONHECIDO");

    private final int codigo;
    private final String nome;

    StatusPagamento(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public static int get(String nome) {
        for (StatusPagamento status : StatusPagamento.values()) {
            if (status.getNome().equals(nome)) {
                return status.codigo;
            }
        }
        throw new IllegalArgumentException("Nome inválido: " + nome);
    }
}
