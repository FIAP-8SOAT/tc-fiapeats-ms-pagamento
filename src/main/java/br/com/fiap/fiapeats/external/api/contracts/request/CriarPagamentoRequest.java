package br.com.fiap.fiapeats.external.api.contracts.request;

import br.com.fiap.fiapeats.domain.entities.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class CriarPagamentoRequest {

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Id do pedido", example = "d212192c-8155-440a-9eda-3d77732458bb")
  private final UUID idPedido;

  @NotNull(message = "Não pode ser null")
  @Schema(
      description = "Produtos associados ao pedido")
  private final List<ProdutoRequest> produtos;

  public CriarPagamentoRequest(UUID idPedido, List<ProdutoRequest> produtos) {
    this.idPedido = idPedido;
    this.produtos = produtos;
  }

  public UUID getIdPedido() {
    return idPedido;
  }

  public List<ProdutoRequest> getProdutos() {
    return produtos;
  }
}
