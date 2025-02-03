package br.com.fiap.fiapeats.adapter.gateway.integration.impl;

import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PedidoIntegration;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.usecases.interfaces.out.pedido.PedidoGateway;
import org.springframework.http.HttpStatusCode;


public class PedidoGatewayImpl implements PedidoGateway {

  private final PedidoIntegration pedidoIntegration;

  public PedidoGatewayImpl(PedidoIntegration pedidoIntegration) {
    this.pedidoIntegration = pedidoIntegration;
  }

  @Override
  public Pedido consultar(String idPedido) {
    return pedidoIntegration.consultarPedido(idPedido);
  }

  @Override
  public int atualizarStatusPagamento(String idPedido, Long idStatusPagamento) {
    return pedidoIntegration.atualizarStatusPagamento(idPedido, idStatusPagamento);
  }
}
