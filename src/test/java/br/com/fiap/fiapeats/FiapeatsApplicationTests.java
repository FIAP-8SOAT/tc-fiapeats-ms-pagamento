package br.com.fiap.fiapeats;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "feign.mercadopago.autenticacao=http://your-service-url.com",
        "feign.mercadopago.pedido=http://your-service-url.com",
        "feign.fiapeats.mspedido=http://your-service-url.com"
})
class FiapeatsApplicationTests {}
