package br.com.fiap.fiapeats.external.integration.feign;

import br.com.fiap.fiapeats.domain.enums.StatusPagamento;
import br.com.fiap.fiapeats.external.config.FeignConfig;
import br.com.fiap.fiapeats.external.integration.feign.request.AtualizarPagamentoPedidoRequest;
import br.com.fiap.fiapeats.external.integration.feign.request.CriarPagamentoPedidoRequest;
import br.com.fiap.fiapeats.external.integration.feign.response.ConsultarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.CriarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.PedidoResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "api-consultarPedido", url = "${feign.fiapeats.mspedido}", configuration = FeignConfig.class)
public interface PedidoFeign {

    @GetMapping(value = "/order/{orderId}")
    Optional<PedidoResponse> consultar(@PathVariable("orderId") String id);

    @PatchMapping(value = "/payment/{idOrdem}", consumes = "application/json")
    void atualizarStatusPagamento(@PathVariable("idOrdem") String id, @RequestBody AtualizarPagamentoPedidoRequest atualizarPagamentoPedidoRequest);
}
