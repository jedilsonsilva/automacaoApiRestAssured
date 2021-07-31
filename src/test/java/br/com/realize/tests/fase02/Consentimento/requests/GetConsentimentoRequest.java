package br.com.realize.tests.fase02.Consentimento.requests;

import br.com.realize.tests.fase02.Consentimento.pojo.*;
import br.com.realize.utils.geradorCpfCnpjRG;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class GetConsentimentoRequest {

    String token = "004904950354";
    String tokenInvalido = "11223344556677";
    String url = "/consents/v1/consents/";
    String expirationDateTime = "2021-05-21T08:30:00Z";
    String transactionFromDateTime = "2021-01-01T00:00:00Z";
    String transactionToDateTime = "2021-02-01T23:59:59Z";
    String consentId;
    String consentIdInvalido = "urn:realizecfi:f5428c42-11ac-422d-a7c3-e57bInvalido";

    public void obterConsentId() throws Exception {
        bodyConsentimento bodyConsentimento = new bodyConsentimento();
        bodyDocumentCpf bodyDocumentCpf = new bodyDocumentCpf();
        bodyDocumentCnpj bodyDocumentCnpj = new bodyDocumentCnpj();
        bodyBusinessEntity bodyBusinessEntity = new bodyBusinessEntity();
        bodyLoggedUser bodyLoggedUser = new bodyLoggedUser();

        bodyConsentimento.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyConsentimento.setExpirationDateTime(expirationDateTime);
        bodyConsentimento.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyConsentimento.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyConsentimento.setTransactionFromDateTime(transactionFromDateTime);
        bodyConsentimento.setTransactionToDateTime(transactionToDateTime);

        Response response = (Response)  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentId = extractor.get("data.consentId");
        System.out.println("O consentId consultado é " + consentId);
    }

    @Step("Obter detalhes do consentimento identificado por consentId.")
    public Response obterConsetimento() throws Exception {
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

