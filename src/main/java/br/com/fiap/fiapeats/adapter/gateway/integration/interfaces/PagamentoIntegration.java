package br.com.fiap.fiapeats.adapter.gateway.integration.interfaces;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.domain.entities.Pedido;

public interface PagamentoIntegration {

  Pagamento criarCodigoPagamento(Pedido pedido, Pagamento pagamento);
  PagamentoPedidoExterno consultarStatusPagamentoPedido(String idPedidoExterno);
}
