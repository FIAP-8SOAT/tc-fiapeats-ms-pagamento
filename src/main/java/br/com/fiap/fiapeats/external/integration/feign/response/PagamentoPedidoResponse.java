package br.com.fiap.fiapeats.external.integration.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagamentoPedidoResponse {

  @JsonProperty("id")
  private final String id;

  @JsonProperty("status")
  private final String status;

  public PagamentoPedidoResponse(String id, String status) {
    this.id = id;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }
}
