package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.factory.ConsentimentoPagamentoDataFactory;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.BodyAtualizacaoContaDebito;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class PostAutorizarConsentimentoPagamentoRequest {

    static Faker fake = new Faker(new Locale("pt-br"));
    static String idempotency = String.valueOf(fake.random());
    static String token = "676378126781236";
    public static String url = "/payments/v1/consents/";
    public static String consentId;
    String type;
    String ispb;
    String issuer;
    String number;

    public static Response obterConsentId() throws Exception {

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


    @Step("200 - Autorizar um consentimento de pagamento")
    public Response autorizarConsentimento() throws Exception {
        obterConsentId();
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new BodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb(ispb);
        bodyAtualizacaoContaDebito.setIssuer(issuer);
        bodyAtualizacaoContaDebito.setNumber(number);
        bodyAtualizacaoContaDebito.setType(type);

         return given()
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }

    @Step("409 - Autorizar um consentimento de pagamento que já esteja autorizado")
    public Response autorizarConsentimentoAutorizado() throws Exception {
        autorizarConsentimento();
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new BodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb(ispb);
        bodyAtualizacaoContaDebito.setIssuer(issuer);
        bodyAtualizacaoContaDebito.setNumber(number);
        bodyAtualizacaoContaDebito.setType(type);

        return given()
                .body(bodyAtualizacaoContaDebito)
                .when()
                .post(url + consentId + "/authorize");
    }
}
