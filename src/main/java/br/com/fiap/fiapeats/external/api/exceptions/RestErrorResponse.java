package br.com.fiap.fiapeats.external.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestErrorResponse {

  private final long timestamp = new Date().getTime();

  @JsonProperty("mensagem")
  private final String message;

  @JsonProperty("erros")
  private final List<String> errors;

  public RestErrorResponse(String message, List<String> errors) {
    this.message = message;
    this.errors = errors;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
