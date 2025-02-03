package br.com.fiap.fiapeats.adapter.gateway.integration.interfaces;

import br.com.fiap.fiapeats.domain.entities.Pagamento;
import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.domain.entities.Pedido;
import org.springframework.http.HttpStatusCode;

public interface PedidoIntegration {
  Pedido consultarPedido(String idPedido);
  int atualizarStatusPagamento(String idPedido, Long idStatusPagamento);

}
