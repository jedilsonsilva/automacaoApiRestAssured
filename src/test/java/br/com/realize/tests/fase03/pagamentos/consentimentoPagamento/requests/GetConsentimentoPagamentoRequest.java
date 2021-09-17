package br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static br.com.realize.tests.base.factory.ConsentimentoPagamentoDataFactory.*;

public class GetConsentimentoPagamentoRequest {

    @Step("Consultar consentimento para iniciação de pagamento")
    public Response obterConsetimentoPagamento() throws Exception {
         return  given()
                    .header("Authorization", token)
                    .contentType("application/json")
                    .when()
                    .get(urlConsetimentoPagamento + consentId);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response pathInvalido() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get("/consents/v1/consentsss/" + consentId);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response requisicaoMalFormada() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(urlConsetimentoPagamento + "testeRequisicaoMalFormada");
    }
   /* @Step("401 - Cabeçalho de autenticação ausente/inválido ou token inválido")
    public Response tokenInvalido() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .when()
                .get(url + consentId);
    }*/

    @Step("403 - O token tem escopo incorreto ou uma política de segurança foi violada.")
    public Response politicaSegurancaVioalada() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(urlConsetimentoPagamento + consentId);
    }
    @Step("404 - O recurso solicitado não existe ou não foi implementado.")
    public Response recursoInexistente() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(urlConsetimentoPagamento + consentIdInvalido);
    }
    @Step("405 - O consumidor tentou acessar o recurso com um método não suportado.")
    public Response metodoNaoSuportado() throws Exception {
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .put(urlConsetimentoPagamento + consentId);
    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8.")
    public Response acceptDiferente() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .accept("application/xml")
                .when()
                .get(urlConsetimentoPagamento + consentId);
    }
    @Step("429 - A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido.")
    public Response muitasSolicitacoesFeitas() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(urlConsetimentoPagamento + consentId);
    }
    @Step("500 - Ocorreu um erro no gateway da API ou no microsserviço.")
    public Response erroGateway() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get("accounts/v1/accounts");
    }
}

