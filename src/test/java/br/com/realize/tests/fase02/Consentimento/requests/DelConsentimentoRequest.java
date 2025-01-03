package br.com.realize.tests.fase02.Consentimento.requests;

import br.com.realize.tests.fase02.Consentimento.pojo.*;
import br.com.realize.utils.geradorCpfCnpjRG;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Collections;
import static io.restassured.RestAssured.given;

public class DelConsentimentoRequest {

    String token = "004904950354";
    String tokenInvalido = "11223344556677";
    String url = "/consents/v1/consents/";
    String expirationDateTime = "2021-05-21T08:30:00Z";
    String transactionFromDateTime = "2021-01-01T00:00:00Z";
    String transactionToDateTime = "2021-02-01T23:59:59Z";
    public String consentId;
    String consentIdInvalido = "urn:realizecfi:f5428c42-11ac-422d-a7c3-e57bInvalido";

    public void obterConsentId() throws Exception {
        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

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
        System.out.println("O consentId é " + consentId);
    }

    @Step("Consentimento revogado com sucesso.")

    public Response deletarPedidoConsetimento() throws Exception {
            obterConsentId();
            return  given()
                    .header("Authorization", token)
                    .contentType("application/json")
                    .when()
                    .delete(url + consentId);
    }

    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response pathInvalido() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .delete(url + "s/" + consentId);
    }
    @Step("401 - Cabeçalho de autenticação ausente/inválido ou token inválido")
    public Response tokenInvalido() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .when()
                .delete(url + consentId);
    }

    @Step("403 - O token tem escopo incorreto ou uma política de segurança foi violada.")
    public Response politicaSegurancaVioalada() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .delete(url + consentId);
    }
    @Step("404 - O recurso solicitado não existe ou não foi implementado.")
    public Response recursoNaoExiste() throws Exception {
        obterConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .when()
                .delete("credit-cards-accounts/v1/accounts" + consentId);
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
                .delete(url + consentId);
    }
}

