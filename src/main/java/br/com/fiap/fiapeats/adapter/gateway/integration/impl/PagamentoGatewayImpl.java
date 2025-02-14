package br.com.fiap.fiapeats.adapter.gateway.integration.impl;

import br.com.fiap.fiapeats.adapter.gateway.integration.interfaces.PagamentoIntegration;
import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.usecases.interfaces.out.pagamento.PagamentoGateway;


public class PagamentoGatewayImpl implements PagamentoGateway {

  private final PagamentoIntegration pagamentoIntegration;

  public PagamentoGatewayImpl(PagamentoIntegration pagamentoIntegration) {
    this.pagamentoIntegration = pagamentoIntegration;
  }

  @Override
  public Pagamento criar(Pedido pedido, Pagamento pagamento) {
    return pagamentoIntegration.criarCodigoPagamento(pedido, pagamento);
  }

  @Override
  public PagamentoPedidoExterno consultar(String idPedidoExterno) {
    return pagamentoIntegration.consultarStatusPagamentoPedido(idPedidoExterno);
  }
}
