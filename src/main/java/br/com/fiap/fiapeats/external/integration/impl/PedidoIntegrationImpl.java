package br.com.fiap.fiapeats.external.integration.impl;

import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PedidoIntegration;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import br.com.fiap.fiapeats.domain.utils.Constants;
import br.com.fiap.fiapeats.external.integration.feign.PedidoFeign;
import br.com.fiap.fiapeats.external.integration.feign.request.AtualizarPagamentoPedidoRequest;
import br.com.fiap.fiapeats.external.integration.mapper.PedidoIntegrationMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;

@Slf4j
public class PedidoIntegrationImpl implements PedidoIntegration {

  private final PedidoFeign pedidoFeign;

  public PedidoIntegrationImpl(PedidoFeign pedidoFeign) {
    this.pedidoFeign = pedidoFeign;
  }

  @Override
  public Pedido consultarPedido(String idPedido) {
    log.info(
            "correlationId={"
                    + ThreadContext.get(Constants.CORRELATION_ID)
                    + "} "
                    + "[PedidoIntegrationImpl-consultarPedido] ");

    try {
      return PedidoIntegrationMapper.toPedido(pedidoFeign.consultar(idPedido).orElse(null));
    } catch (FeignException e) {
      log.info("Erro a consultar a API de pedido. HTTP: " + e.status());
    }
    return null;
  }

  @Override
  public void atualizarStatusPagamento(String idPedido, StatusPagamento statusPagamento) {
    log.info(
            "correlationId={"
                    + ThreadContext.get(Constants.CORRELATION_ID)
                    + "} "
                    + "[PedidoIntegrationImpl-atualizarStatusPagamento] ");
    pedidoFeign.atualizarStatusPagamento(idPedido, new AtualizarPagamentoPedidoRequest(statusPagamento.getCodigo(), statusPagamento.getNome()));
  }
}
