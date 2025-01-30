package br.com.fiap.fiapeats.domain.enums;

public enum StatusPagamento {
    PENDENTE(1, "Pendente"),
    APROVADO(2, "Aprovado"),
    REJEITADO(3, "Recusado"),
    EM_ANALISE(4, "Em Análise"),
    ESTORNADO(5, "Estornado"),
    CANCELADO(6, "Cancelado"),
    DESCONHECIDO(0, "Desconhecido");

    private final int codigo;
    private final String descricao;

    StatusPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String obterDescricaoPorCodigo(Long codigoStatus) {
        if (codigoStatus == null) {
            return "Código de status inválido";
        }

        for (StatusPagamento status : StatusPagamento.values()) {
            if (status.getCodigo() == codigoStatus.intValue()) {
                return status.getDescricao();
            }
        }

        return DESCONHECIDO.getDescricao();
    }
}
