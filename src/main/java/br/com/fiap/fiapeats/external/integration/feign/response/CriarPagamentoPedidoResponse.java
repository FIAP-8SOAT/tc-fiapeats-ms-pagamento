package br.com.fiap.fiapeats.external.integration.feign.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CriarPagamentoPedidoResponse {

  @JsonProperty("in_store_order_id")
  private final String idPedidoExterno;

  @JsonProperty("qr_data")
  private final String codigoQr;

  public CriarPagamentoPedidoResponse(String idPedido, String codigoQr) {
    this.idPedidoExterno = idPedido;
    this.codigoQr = codigoQr;
  }

  public String getIdPedidoExterno() {
    return idPedidoExterno;
  }

  public String getCodigoQr() {
    return codigoQr;
  }
}
