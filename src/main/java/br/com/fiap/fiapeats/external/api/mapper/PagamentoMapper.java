package br.com.fiap.fiapeats.external.api.mapper;

import br.com.fiap.fiapeats.external.api.contracts.request.CriarPagamentoRequest;
import br.com.fiap.fiapeats.usecases.dtos.CriarPagamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
  CriarPagamentoDTO toCriarPagamentoDTO(CriarPagamentoRequest criarPagamentoRequest);
}
