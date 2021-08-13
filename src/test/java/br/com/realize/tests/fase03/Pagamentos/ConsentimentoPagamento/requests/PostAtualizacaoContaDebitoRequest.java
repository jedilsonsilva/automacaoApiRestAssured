package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.bodyAtualizacaoContaDebito;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.tests.PostAutorizarConsentimentoPagamentoTest;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.tests.PostRejeitarConsentimentoPagamentoTest;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;
import static io.restassured.RestAssured.given;

public class PostAtualizacaoContaDebitoRequest {

    PostRejeitarConsentimentoPagamentoTest postRejeitarConsentimentoPagamentoTest = new PostRejeitarConsentimentoPagamentoTest();
    PostAutorizarConsentimentoPagamentoTest postAutorizarConsentimentoPagamentoTest = new PostAutorizarConsentimentoPagamentoTest();

    Faker fake = new Faker(new Locale("pt-br"));
    String idempotency = String.valueOf(fake.random());
    String token = "676378126781236";
    String url = "/payments/v1/consents/";
    String tokenInvalido = "11223344556677";
    String consentId;
    String consentIdInvalido = "4d8a5d7a5s7d4as";
    String consentIdAuthorized = "urn:realizecfi:e5137f39-2322-4e89-b346-d4c1139b8bb7";
    String consentIdRejected;
    String consentIdConsumed;
    String issuer;
    String number;

    public Response obterDadosConta() throws Exception {
    bodyConsentimentoPagamento bodyConsentimentoPagamento = new bodyConsentimentoPagamento();
    bodyDataPagamento bodyDataPagamento = new bodyDataPagamento();
    bodyDocumentCpfPagamento bodyDocumentCpfPagamento = new bodyDocumentCpfPagamento();
    bodyDocumentCnpjPagamento bodyDocumentCnpjPagamento = new bodyDocumentCnpjPagamento();
    bodyBusinessEntityPagamento bodyBusinessEntityPagamento = new bodyBusinessEntityPagamento();
    bodyLoggedUserPagamento bodyLoggedUserPagamento = new bodyLoggedUserPagamento();
    bodyCreditor bodyCreditor = new bodyCreditor();
    bodyPayment bodyPayment = new bodyPayment();
    bodyDebtorAccount bodyDebtorAccount = new bodyDebtorAccount();

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
    bodyCreditor.setCpfCnpj(geradorCpfCnpjRG.geraCPF());
    bodyCreditor.setName("Ana Maria");
    bodyDataPagamento.setPayment(bodyPayment);
    bodyPayment.setType("PIX");
    bodyPayment.setDate(DataUtils.getDateTime());
    bodyPayment.setCurrency("BRL");
    bodyPayment.setAmount("100000.12");
    bodyDataPagamento.setDebtorAccount(bodyDebtorAccount);
    bodyDebtorAccount.setIspb("12345678");
    bodyDebtorAccount.setIssuer("1774");
    bodyDebtorAccount.setNumber("0006225246");
    bodyDebtorAccount.setAccountType("TRAN");

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

   /* public void obterConsentIdRejeitado() throws Exception {
        Response response = postRejeitarConsentimentoPagamentoTest.testRejeitarConsentimento();
        JsonPath extractor = response.jsonPath();
        consentIdRejected = extractor.get("data.consentId");
        System.out.println(consentIdRejected);

    }*/

    @Step("201 - Insere um pedido de consentimento de pagamento")
    public Response atualizarDadosContaDebito() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

         return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
    @Step("404 - Informar um endpoint incorreto")
    public Response pathInvalido() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorizee");
    }

    @Step("400 - Solicitar atualização de conta de débito sem informa")
    public Response authorizationNaoInformado() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");
        return  given()
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
    @Step("401 - Informar token inválido")
    public Response tokenInvalido() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", tokenInvalido)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
    @Step("404 - Informar um consentId não cadastrado")
    public Response consentIdNaoEncontrado() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdInvalido + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'AUTHORISED'")
    public Response consentIdAuthorized() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdAuthorized + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'REJECTED'")
    public Response consentIdRejected() throws Exception {
        //obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdRejected + "/authorize");
    }
    @Step("409 - Status do consentimento igual a 'CONSUMED'")
    public Response consentIdConsumed() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentIdConsumed + "/authorize");
    }
    @Step("412 - Status do consentimento igual a 'CONSUMED'")
    public Response expirationDateTime() throws Exception {
        obterDadosConta();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + "urn:realizecfi:59e651b8-07ca-465d-ad26-d62f63004791/authorize");
    }
}
