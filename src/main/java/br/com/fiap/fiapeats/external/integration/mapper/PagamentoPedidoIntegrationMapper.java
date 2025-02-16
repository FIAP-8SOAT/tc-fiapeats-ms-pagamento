package br.com.fiap.fiapeats.external.integration.mapper;

import br.com.fiap.fiapeats.domain.entities.PagamentoPedidoExterno;
import br.com.fiap.fiapeats.external.integration.feign.response.ConsultarPagamentoPedidoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoPedidoIntegrationMapper {

  PagamentoPedidoExterno toPedido(
      ConsultarPagamentoPedidoResponse consultarPagamentoPedidoResponse);
}
