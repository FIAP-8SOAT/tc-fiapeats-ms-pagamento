package br.com.fiap.fiapeats.external.integration.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConsultarPagamentoPedidoResponse {

    @JsonProperty("status")
    private final String status;

    @JsonProperty("external_reference")
    private final String idPedido;

    @JsonProperty("payments")
    private final List<PagamentoPedidoResponse> pagamento;

    public ConsultarPagamentoPedidoResponse(
            String status, String idPedido, List<PagamentoPedidoResponse> pagamento) {
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

    public List<PagamentoPedidoResponse> getPagamento() {
        return pagamento;
    }
}
