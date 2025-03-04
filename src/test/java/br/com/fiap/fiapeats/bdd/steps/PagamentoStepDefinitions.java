package br.com.fiap.fiapeats.bdd.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yml", properties = "spring.profiles.active=test")
@CucumberContextConfiguration
public class PagamentoStepDefinitions {

    @LocalServerPort
    private int port;
    private Response response;
    private String baseUrl = "/mspagamento/pagamento";
    private String topico;
    private String pedido;


    @Dado("que eu tenho os dados do status de pagamento do pedido:")
    public void eu_tenho_dados_do_status_de_pagamento_do_pedido(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> atualizacaoStatusData = data.get(0);

        topico = atualizacaoStatusData.get("topic");
        pedido = atualizacaoStatusData.get("id");
    }

    @Quando("eu tento atualizar o status do pagamento do pedido")
    public void eu_tento_atualizar_o_status_do_pagamento_do_pedido() {
        response = given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("topic", topico)
                .queryParam("id", pedido)
                .when()
                .post(baseUrl + "/notificacao");
    }

    @Entao("o sistema retorna pedido não encontrado associado ao pagamento")
    public void o_sistema_retorna_pedido_nao_encontrado_para_pagamento() {
        assertEquals(404, response.statusCode());
    }

}
