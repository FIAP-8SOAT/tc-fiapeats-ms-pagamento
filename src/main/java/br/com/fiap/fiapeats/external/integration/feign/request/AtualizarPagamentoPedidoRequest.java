package br.com.fiap.fiapeats.external.integration.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AtualizarPagamentoPedidoRequest {

    @JsonProperty("paymentId")
    private final Long id;

    @JsonProperty("paymentStatus")
    private final String status;

    public AtualizarPagamentoPedidoRequest(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

}
