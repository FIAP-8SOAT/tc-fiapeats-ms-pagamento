package br.com.fiap.fiapeats.external.integration.mapper;

import br.com.fiap.fiapeats.domain.entities.Pedido;
import br.com.fiap.fiapeats.external.integration.feign.response.PedidoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoIntegrationMapper {

  Pedido toPedido(PedidoResponse pedidoResponse);
}
