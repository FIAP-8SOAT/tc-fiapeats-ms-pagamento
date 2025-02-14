package br.com.fiap.fiapeats.external.integration.feign;

import br.com.fiap.fiapeats.external.integration.feign.request.CriarAutenticacaoRequest;
import br.com.fiap.fiapeats.external.integration.feign.response.CriarAutenticacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "api-gerarToken", url = "https://api.mercadopago.com/oauth/token")
public interface AutenticacaoFeign {

    @PostMapping(produces = "application/json")
    CriarAutenticacaoResponse obterToken(
            @RequestBody CriarAutenticacaoRequest criarAutenticacaoRequest);
}
