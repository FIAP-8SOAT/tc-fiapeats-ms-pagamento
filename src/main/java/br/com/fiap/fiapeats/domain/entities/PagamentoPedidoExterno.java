package br.com.fiap.fiapeats.domain.entities;

import java.util.List;

public class PagamentoPedidoExterno {
    private final String status;
    private final String idPedido;
    private final List<PagamentoExterno> pagamento;

    public PagamentoPedidoExterno(String status, String idPedido, List<PagamentoExterno> pagamento) {
        this.status = status;
        this.idPedido = idPedido;
        this.pagamento = pagamento;
    }

    public String getStatus() {
        return status;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public List<PagamentoExterno> getPagamento() {
        return pagamento;
    }
}
