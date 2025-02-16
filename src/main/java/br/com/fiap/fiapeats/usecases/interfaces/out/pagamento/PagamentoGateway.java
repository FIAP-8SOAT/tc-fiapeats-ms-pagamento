package br.com.fiap.fiapeats.usecases.interfaces.out.pagamento;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.domain.entities.Pedido;

public interface PagamentoGateway {
  Pagamento criar(Pedido pedido, Pagamento pagamento);
  PagamentoPedidoExterno consultar(String idPedidoExterno);
}
