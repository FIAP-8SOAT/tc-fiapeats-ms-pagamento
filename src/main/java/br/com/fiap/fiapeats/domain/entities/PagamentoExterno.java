package br.com.fiap.fiapeats.domain.entities;

public class PagamentoExterno {
  private final String id;
  private final String status;

  public PagamentoExterno(String id, String status) {
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
