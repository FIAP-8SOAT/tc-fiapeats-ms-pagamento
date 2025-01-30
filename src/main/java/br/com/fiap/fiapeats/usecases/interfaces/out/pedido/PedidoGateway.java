package br.com.fiap.fiapeats.usecases.interfaces.out.pedido;

import br.com.fiap.fiapeats.domain.entities.Pedido;

public interface PedidoGateway {

    Pedido consultar(String idPedido);
}
