package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.bodyAtualizacaoContaDebito;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.bodyDebtorAccount;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.geradorCpfCnpjRG;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAtualizacaoContaDebitoRequest {

    String token = "676378126781236";
    String url = "/payments/v1/consents";
    String tokenInvalido = "11223344556677";
    String consentId;
    String consentIdInvalido = "4d8a5d7a5s7d4as";
    String consentIdAuthorized;
    String consentIdRejected;
    String consentIdConsumed;
    String consentIdExpirationDateTime;

    public void obterConsentId() throws Exception {

        bodyConsentimentoPagamento bodyConsentimentoPagamento = new bodyConsentimentoPagamento();
        bodyDataPagamento bodyDataPagamento = new bodyDataPagamento();
        bodyDocumentCpfPagamento bodyDocumentCpfPagamento = new bodyDocumentCpfPagamento();
        bodyDocumentCnpjPagamento bodyDocumentCnpjPagamento = new bodyDocumentCnpjPagamento();
        bodyBusinessEntityPagamento bodyBusinessEntityPagamento = new bodyBusinessEntityPagamento();
        bodyLoggedUserPagamento bodyLoggedUserPagamento = new bodyLoggedUserPagamento();
        bodyCreditor bodyCreditor = new bodyCreditor();
        bodyPayment bodyPayment = new bodyPayment();
        br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.bodyDebtorAccount bodyDebtorAccount = new br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.bodyDebtorAccount();

        bodyConsentimentoPagamento.setData(bodyDataPagamento);
        bodyDataPagamento.setLoggedUser(bodyLoggedUserPagamento);
        bodyLoggedUserPagamento.setDocument(bodyDocumentCpfPagamento);
        bodyDocumentCpfPagamento.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpfPagamento.setRel("CPF");
        bodyDataPagamento.setBusinessEntity(bodyBusinessEntityPagamento);
        bodyBusinessEntityPagamento.setDocument(bodyDocumentCnpjPagamento);
        bodyDocumentCnpjPagamento.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpjPagamento.setRel("CNPJ");
        bodyDataPagamento.setCreditor(bodyCreditor);
        bodyCreditor.setPersonType("PESSOA_NATURAL");
        bodyCreditor.setCpfCnpj("");
        bodyCreditor.setName("");
        bodyDataPagamento.setPayment(bodyPayment);
        bodyPayment.setType("PIX");
        bodyPayment.setDate("2021-01-01");
        bodyPayment.setCurrency("BRL");
        bodyPayment.setAmount("100000.12");
        bodyDataPagamento.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("12345678");
        bodyDebtorAccount.setIssuer("1774");
        bodyDebtorAccount.setNumber("1234567890");
        bodyDebtorAccount.setAccountType("CACC");

        Response response = (Response)  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentId = extractor.get("data.consentId");
        System.out.println("O consentId de pagamento consultado é " + consentId);
    }

    @Step("201 - Insere um pedido de consentimento de pagamento")
    public Response atualizarDadosContaDebito() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
            bodyDebtorAccount.setIspb("");
            bodyDebtorAccount.setIssuer("");
            bodyDebtorAccount.setNumber("");
            bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentId + "authorize");
    }
    @Step("404 - Informar um endpoint incorreto")
    public Response pathInvalido() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consentsss/" + consentId + "authorize");
    }

    @Step("400 - Solicitar atualização de conta de débito sem informa")
    public Response authorizationNaoInformado() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();

        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentId + "authorize");
    }
    @Step("401 - Informar token inválido")
    public Response tokenInvalido() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentId + "authorize");
    }
    @Step("404 - Informar um consentId não cadastrado")
    public Response consentIdNaoEncontrado() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentIdInvalido + "authorize");
    }
    @Step("409 - Status do consentimento igual a 'AUTHORISED'")
    public Response consentIdAuthorized() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentIdAuthorized + "authorize");
    }
    @Step("409 - Status do consentimento igual a 'REJECTED'")
    public Response consentIdRejected() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentIdRejected + "authorize");
    }
    @Step("409 - Status do consentimento igual a 'CONSUMED'")
    public Response consentIdConsumed() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentIdConsumed + "authorize");
    }
    @Step("412 - Status do consentimento igual a 'CONSUMED'")
    public Response expirationDateTime() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();
        bodyAtualizacaoContaDebito.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("");
        bodyDebtorAccount.setIssuer("");
        bodyDebtorAccount.setNumber("");
        bodyDebtorAccount.setAccountType("");

        return  given()
                .log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post("/payments/v1/consents/" + consentIdExpirationDateTime + "authorize");
    }
}
