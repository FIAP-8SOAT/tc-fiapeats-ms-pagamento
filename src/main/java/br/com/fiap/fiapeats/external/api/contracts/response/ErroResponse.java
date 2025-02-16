package br.com.fiap.fiapeats.external.api.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ErroResponse {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String tipo;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String codigo;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String mensagem;
}
