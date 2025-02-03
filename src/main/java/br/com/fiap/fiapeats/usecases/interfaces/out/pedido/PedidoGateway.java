package br.com.fiap.fiapeats.usecases.interfaces.out.pedido;

import br.com.fiap.fiapeats.domain.entities.Pedido;
import org.springframework.http.HttpStatusCode;

public interface PedidoGateway {

    Pedido consultar(String idPedido);

    int atualizarStatusPagamento(String idPedido, Long idStatusPagamento);

}
