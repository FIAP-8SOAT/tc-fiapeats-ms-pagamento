package br.com.fiap.fiapeats.external.api.contracts.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoRequest {

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Id do produto", example = "d212192c-8155-440a-9eda-3d77732458bb")
  private final UUID id;

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Nome do produto", example = "Batata")
  private final String nome;

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Descrição do produto", example = "Batata com cheddar")
  private final String descricao;

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Valor do produto", example = "10.00")
  private final BigDecimal valor;

  @NotNull(message = "Não pode ser null")
  @Schema(description = "Descrição do produto", example = "Bebida")
  private final String categoriaDescricao;

  public ProdutoRequest(UUID id, String nome, String descricao, BigDecimal valor, String categoriaDescricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.valor = valor;
    this.categoriaDescricao = categoriaDescricao;
  }

  public UUID getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public String getCategoriaDescricao() {
    return categoriaDescricao;
  }
}
