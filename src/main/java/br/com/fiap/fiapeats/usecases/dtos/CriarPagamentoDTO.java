package br.com.fiap.fiapeats.usecases.dtos;

import java.util.UUID;

public class CriarPagamentoDTO {

  private UUID idPedido;

  private String urlNotificacao;

  public CriarPagamentoDTO(UUID idPedido, String urlNotificacao) {
    this.idPedido = idPedido;
    this.urlNotificacao = urlNotificacao;
  }

  public void setIdPedido(UUID idPedido) {
    this.idPedido = idPedido;
  }

  public void setUrlNotificacao(String urlNotificacao) {
    this.urlNotificacao = urlNotificacao;
  }

  public UUID getIdPedido() {
    return idPedido;
  }

  public String getUrlNotificacao() {
    return urlNotificacao;
  }
}
