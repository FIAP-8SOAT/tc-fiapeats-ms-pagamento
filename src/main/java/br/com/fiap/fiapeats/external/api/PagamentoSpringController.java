package br.com.fiap.fiapeats.external.api;

import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
import br.com.fiap.fiapeats.domain.utils.Constants;
import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import br.com.fiap.fiapeats.external.api.mapper.PagamentoMapper;
import br.com.fiap.fiapeats.usecases.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@Tag(name = "Pagamentos")
@RequestMapping("/pagamento")
public class PagamentoSpringController {

  public static final String CORRELATION_ID = "correlationId={";

  @Autowired private PagamentoController pagamentoController;

  @Autowired private PagamentoMapper pagamentoMapper;

  @PostMapping
  @Operation(
      summary = "Cria um QR Code para pagamento de um pedido",
      description = "Recebendo os dados necessários, cria-se um novo QR Code para pagamento")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "QR Code de pagamento criado com sucesso")
      })
  public ResponseEntity<CriarPagamentoResponse> criarCodigoPagamento(
          @RequestHeader(value = "correlationId", required = false) String correlationId,
      @RequestBody CriarPagamentoRequest criarPagamentoRequest) {

    if (correlationId == null || correlationId.isEmpty()) {
      correlationId = UUID.randomUUID().toString();
    }

    gerarCorrelationId(correlationId, "Requisição para criar QR Code para pagamento recebida");

    CriarPagamentoDTO criarPagamentoDTO =
        pagamentoMapper.toCriarPagamentoDTO(criarPagamentoRequest);

    return ResponseEntity.status(201).body(pagamentoController.criarPagamento(criarPagamentoDTO));
  }

  @PostMapping("/notificacao")
  @Operation(
      summary = "Recebe notificação de atualização de pagamento do pedido",
      description = "Recebendo os dados necessários, atualiza o status de pagamento do pedido")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Notificação de atualização de status de pagamento do pedido recebida com sucesso")
      })
  public ResponseEntity<Object> atualizarPagamento(
          @RequestHeader(value = "correlationId", required = false) String correlationId,
      @RequestParam(value = "topic", required = false) String topico,
      @RequestParam(value = "id", required = false) String idPedidoExterno) {

    if (correlationId == null || correlationId.isEmpty()) {
      correlationId = UUID.randomUUID().toString();
    }

    gerarCorrelationId(correlationId, "Requisição de atualização do pagamento recebida para o id pedido externo " + idPedidoExterno);

    pagamentoController.atualizarPagamento(idPedidoExterno, topico);

    return ResponseEntity.status(200).build();
  }

  private void gerarCorrelationId(String correlationId, String description) {
    ThreadContext.put(Constants.CORRELATION_ID, correlationId);
    log.info(CORRELATION_ID
            + ThreadContext.get(Constants.CORRELATION_ID)
            + "} "
            + description);
  }
}
