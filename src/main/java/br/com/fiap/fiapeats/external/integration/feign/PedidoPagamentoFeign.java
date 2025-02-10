package br.com.fiap.fiapeats.external.integration.feign;

import br.com.fiap.fiapeats.external.integration.feign.request.CriarPagamentoPedidoRequest;
import br.com.fiap.fiapeats.external.integration.feign.response.ConsultarPagamentoPedidoResponse;
import br.com.fiap.fiapeats.external.integration.feign.response.CriarPagamentoPedidoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api-criarPedido", url = "https://api.mercadopago.com")
public interface PedidoPagamentoFeign {

    @PostMapping(
            value = "/instore/orders/qr/seller/collectors/2000983121/pos/FIAPEATSPOS001/qrs")
    CriarPagamentoPedidoResponse criar(
            @RequestHeader("Authorization") String token,
            @RequestBody CriarPagamentoPedidoRequest criarPedidoRequest);

    @GetMapping(value = "/merchant_orders/{id}")
    ConsultarPagamentoPedidoResponse consultar(
            @RequestHeader("Authorization") String token, @PathVariable String id);
}
