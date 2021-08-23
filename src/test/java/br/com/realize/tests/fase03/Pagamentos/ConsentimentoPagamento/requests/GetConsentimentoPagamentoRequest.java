package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.factory.ConsentimentoPagamentoDataFactory;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
public class GetConsentimentoPagamentoRequest {

    static Faker fake = new Faker(new Locale("pt-br"));
    static String idempotency = String.valueOf(fake.random());
    static String token = "004904950354";
    String tokenInvalido = "11223344556677";
    String url = "/payments/v1/consents/";
    String consentId;
    String consentIdInvalido = "urn:realizecfi:f5428c42-11ac-422d-a7c3-e57bInvalido";

    public Response obterConsentId() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosConsentId();
        Response response = (Response)  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .extract().response();

        JsonPath extractor = response.jsonPath();
        consentId = extractor.get("data.consentId");
        System.out.println("O consentId é " + consentId);
        return response;
    }
    @Step("Consultar consentimento para iniciação de pagamento")
    public Response obterConsetimentoPamento() throws Exception {
         obterConsentId();
         return  given()
                    .header("Authorization", token)
                    .contentType("application/json")
                    .when()
                    .get(url + consentId);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response pathInvalido() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get("/consents/v1/consentsss/" + consentId);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response requisicaoMalFormada() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(url + "testeRequisicaoMalFormada");
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
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(url + consentId);
    }
    @Step("404 - O recurso solicitado não existe ou não foi implementado.")
    public Response recursoInexistente() throws Exception {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(url + consentIdInvalido);
    }
    @Step("405 - O consumidor tentou acessar o recurso com um método não suportado.")
    public Response metodoNaoSuportado() throws Exception {
        obterConsentId();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .put(url + consentId);
    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8.")
    public Response acceptDiferente() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .accept("application/xml")
                .when()
                .get(url + consentId);
    }
    @Step("429 - A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido.")
    public Response muitasSolicitacoesFeitas() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get(url + consentId);
    }
    @Step("500 - Ocorreu um erro no gateway da API ou no microsserviço.")
    public Response erroGateway() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .get("accounts/v1/accounts");
    }
}

