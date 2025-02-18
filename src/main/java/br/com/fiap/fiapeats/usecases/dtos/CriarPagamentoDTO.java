package br.com.fiap.fiapeats.usecases.dtos;

import br.com.fiap.fiapeats.domain.entities.Produto;

import java.util.List;
import java.util.UUID;

public class CriarPagamentoDTO {

  private UUID idPedido;

  private List<ProdutosDTO> produtos;

  public CriarPagamentoDTO(UUID idPedido, List<ProdutosDTO> produtos) {
    this.idPedido = idPedido;
    this.produtos = produtos;
  }

  public UUID getIdPedido() {
    return idPedido;
  }

  public List<ProdutosDTO> getProdutos() {
    return produtos;
  }
}
