package br.com.fiap.fiapeats.usecases.dtos;

public class CriarPagamentoResponse {

  private final String codigoQR;

  public CriarPagamentoResponse(String codigoQR) {
    this.codigoQR = codigoQR;
  }

  public String getCodigoQR() {
    return codigoQR;
  }
}
