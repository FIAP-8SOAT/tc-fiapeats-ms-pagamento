package br.com.fiap.fiapeats.external.api;

import br.com.fiap.fiapeats.adapter.controller.PagamentoController;
import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import br.com.fiap.fiapeats.external.api.mapper.PagamentoMapper;
import br.com.fiap.fiapeats.usecases.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "Pagamentos")
@RequestMapping("/pagamento")
public class PagamentoSpringController {

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
      @RequestBody @Valid CriarPagamentoRequest criarPagamentoRequest) {
    log.info("Requisição para criar QR Code para pagamento recebida");

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
      @RequestParam(value = "topic", required = false) String topico,
      @RequestParam(value = "id", required = false) String idPedidoExterno) {
    log.info(
        "Requisição de atualização do pagamento do id pedido externo {} recebida", idPedidoExterno);

    pagamentoController.atualizarPagamento(idPedidoExterno, topico);

    return ResponseEntity.status(200).build();
  }
}
