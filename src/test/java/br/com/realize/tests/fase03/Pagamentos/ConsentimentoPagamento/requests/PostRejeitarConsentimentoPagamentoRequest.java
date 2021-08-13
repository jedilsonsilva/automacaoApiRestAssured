package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.bodyAtualizacaoContaDebito;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class PostRejeitarConsentimentoPagamentoRequest {

    Faker fake = new Faker(new Locale("pt-br"));
    String idempotency = String.valueOf(fake.random());
    String token = "676378126781236";
    public String url = "/payments/v1/consents/";
    public String consentId;
    String type;
    String ispb;
    String issuer;
    String number;

    public void obterConsentId() throws Exception {

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
            bodyDebtorAccount.setIspb("27351731");
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
                    .statusCode(200)
                    .extract().response();

        JsonPath extractor = (JsonPath) response.jsonPath();
        consentId = extractor.get("data.consentId");
        ispb = extractor.get("data.debtorAccount.ispb");
        issuer = extractor.get("data.debtorAccount.issuer");
        number = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        System.out.println("O consentId é " + consentId);
    }

    @Step("200 - Autorizar um consentimento de pagamento")
    public Response rejeitarConsentimentoPagamento() throws Exception {
        obterConsentId();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb(ispb);
        bodyAtualizacaoContaDebito.setIssuer(issuer);
        bodyAtualizacaoContaDebito.setNumber(number);
        bodyAtualizacaoContaDebito.setType(type);

         return given()
                .when()
                .post(url + consentId + "/reject");
    }
    @Step("200 - Autorizar um consentimento de pagamento")
    public Response rejeitarConsentimentoPagamentoRejeitado() throws Exception {
        rejeitarConsentimentoPagamento();
        bodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new bodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb(ispb);
        bodyAtualizacaoContaDebito.setIssuer(issuer);
        bodyAtualizacaoContaDebito.setNumber(number);
        bodyAtualizacaoContaDebito.setType(type);

        return given()
                .when()
                .post(url + consentId + "/reject");
    }
}
