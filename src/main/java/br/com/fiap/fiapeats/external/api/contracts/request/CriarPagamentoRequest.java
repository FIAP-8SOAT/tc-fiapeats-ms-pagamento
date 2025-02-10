package br.com.fiap.fiapeats.external.api.contracts.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class CriarPagamentoRequest {

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Id do pedido", example = "d212192c-8155-440a-9eda-3d77732458bb")
  private final UUID idPedido;

  @NotEmpty(message = "Não pode ser vazio")
  @Schema(
      description = "Url para receber notificações de status de pagamento",
      example = "https://ba08-45-173-179-15.ngrok-free.app/fiapeats/pagamento/notificacao")
  private final String urlNotificacao;

  public CriarPagamentoRequest(UUID idProduto, String urlNotificacao) {
    this.idPedido = idProduto;
    this.urlNotificacao = urlNotificacao;
  }

  public UUID getIdPedido() {
    return idPedido;
  }

  public String getUrlNotificacao() {
    return urlNotificacao;
  }
}
