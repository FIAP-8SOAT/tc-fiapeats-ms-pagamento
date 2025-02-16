package br.com.fiap.fiapeats.domain.entities;

import java.util.UUID;

public class Pagamento {
  private UUID idPedido;
  private String urlNotificacao;
  private String codigoQr;

  public Pagamento(UUID idPedido, String urlNotificacao, String codigoQR) {
    this.idPedido = idPedido;
    this.urlNotificacao = urlNotificacao;
    this.codigoQr = codigoQR;
  }

  public UUID getIdPedido() {
    return idPedido;
  }

  public String getUrlNotificacao() {
    return urlNotificacao;
  }

  public String getCodigoQr() {
    return codigoQr;
  }

  public void setIdPedido(UUID idPedido) {
    this.idPedido = idPedido;
  }

  public void setUrlNotificacao(String urlNotificacao) {
    this.urlNotificacao = urlNotificacao;
  }

  public void setCodigoQr(String codigoQr) {
    this.codigoQr = codigoQr;
  }
}
