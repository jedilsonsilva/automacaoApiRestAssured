package br.com.realize.tests.base.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.*;
import static io.restassured.RestAssured.given;

public class ConsentimentoPagamentoDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static Faker fake = new Faker(new Locale("pt-br"));

    //URL's
    public static String urlConsetimentoPagamento = "/payments/v1/consents/";
    public static String urlAuthorizeConsentimentoPagamento = "payments/v1/consent-request/";
    public static String urlRejectConsentimentoPagamento = "payments/v1/consent-request/";


    public static String idempotencyPagamento = fake.internet().password();
    public static String idempotencyConsentimentoJaAutorizado = fake.internet().password();
    public static String idempotencyAutorizarConsentimento = fake.internet().password();
    public static String idempotencyRejeitarConsentimento = fake.internet().password();

    //ESSE TOKEN AINDA É UM ALEATÓRIO ATÉ QUE A SEGURANÇA DAS API's SEJA IMPLEMENTADA
    public static String token = "676378126781236";
    public static String tokenInvalido = "987298274987483";
    //CONSENTID's PARA TESTES COM IDEMPOTENCIAS DIFERENTES
    public static String consentId;
    public static String consentIdParaAutorizarConsentimento;
    public static String consentIdParaRejeitarConsentimento;
    public static String consentIdJaRejeitado;
    public static String consentIdInvalido;
    public static String consentIdAuthorize;
    public static String consentIdJaAutorizado;
    public static String consentIdConsumed;

    public static String type;

    public static String ispb;
    public static String agenciaContaDebito;
    public static String numeroContaDebito;
    public static String consentIdAutorizadoPix;


    public static BodyConsentimentoPagamento dadosParaCriarConsentId() throws Exception {
        criacaoMassaOrbi.criarConta();
        BodyConsentimentoPagamento bodyConsentimentoPagamento = new BodyConsentimentoPagamento();
        BodyDataPagamento bodyDataPagamento = new BodyDataPagamento();
        BodyDocumentCpfPagamento bodyDocumentCpfPagamento = new BodyDocumentCpfPagamento();
        BodyDocumentCnpjPagamento bodyDocumentCnpjPagamento = new BodyDocumentCnpjPagamento();
        BodyBusinessEntityPagamento bodyBusinessEntityPagamento = new BodyBusinessEntityPagamento();
        BodyLoggedUserPagamento bodyLoggedUserPagamento = new BodyLoggedUserPagamento();
        BodyCreditor bodyCreditor = new BodyCreditor();
        BodyPayment bodyPayment = new BodyPayment();
        BodyDebtorAccount bodyDebtorAccount = new BodyDebtorAccount();

        bodyConsentimentoPagamento.setData(bodyDataPagamento);
        bodyDataPagamento.setLoggedUser(bodyLoggedUserPagamento);
        bodyLoggedUserPagamento.setDocument(bodyDocumentCpfPagamento);
        bodyDocumentCpfPagamento.setIdentification(cpf);
        bodyDocumentCpfPagamento.setRel("CPF");
        bodyDataPagamento.setBusinessEntity(bodyBusinessEntityPagamento);
        bodyBusinessEntityPagamento.setDocument(bodyDocumentCnpjPagamento);
        bodyDocumentCnpjPagamento.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpjPagamento.setRel("CNPJ");
        bodyDataPagamento.setCreditor(bodyCreditor);
        bodyCreditor.setPersonType("PESSOA_NATURAL");
        bodyCreditor.setCpfCnpj(cpf);
        bodyCreditor.setName(nameCliente);
        bodyDataPagamento.setPayment(bodyPayment);
        bodyPayment.setType("PIX");
        bodyPayment.setDate(DataUtils.getDateTime());
        bodyPayment.setCurrency("BRL");
        bodyPayment.setAmount("100000.12");
        bodyDataPagamento.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("27351731");
        bodyDebtorAccount.setIssuer(agencia);
        bodyDebtorAccount.setNumber(conta);
        bodyDebtorAccount.setAccountType("TRAN");
        return bodyConsentimentoPagamento;
    }

    public static Response criarConsentimentoPagamento() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPagamento)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then().log().all()
                .statusCode(200)
                .extract().response();

        JsonPath extractor = (JsonPath) response.jsonPath();
        consentIdParaAutorizarConsentimento = extractor.get("data.consentId");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaContaDebito = extractor.get("data.debtorAccount.issuer");
        numeroContaDebito = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        ispb = extractor.get("data.debtorAccount.ispb");
        type = extractor.get("data.debtorAccount.type");
        return response;
    }

    //AUTORIZAR CONSENTIMENTO
    public static BodyAuthorize dadosAutorizarConsentimento() throws Exception {
        criarConsentimentoPagamento();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(numeroContaDebito = numeroContaDebito.replace("-",""));
        // bodyAuthorize.setNumber(numeroConta = numeroConta.substring(0,numeroConta.length()-1));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }

    public void autorizarConsentimento() throws Exception {
        dadosAutorizarConsentimento();
        BodyAuthorize bodyAuthorize = ConsentimentoPagamentoDataFactory.dadosAutorizarConsentimento();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdParaAutorizarConsentimento + "/authorize");
    }

    public static Response obterConsentIdParaConsentimentoJaAutorizado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyConsentimentoJaAutorizado)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentIdJaAutorizado = extractor.get("data.consentId");
        return response;
    }

    public static BodyAuthorize dadosAutorizarConsentimentoJaAutorizado() throws Exception {
        obterConsentIdParaConsentimentoJaAutorizado();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(conta = conta.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }

    //ATUALIZAR DADOS CONSENTIMENTO
    public static BodyAuthorize dadosAtualizacaoContaDebito() throws Exception {
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer("1774");
        bodyAuthorize.setNumber("000622524");
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }

    //REJEITAR CONSENTIMENTO
    public static Response obterConsentIdParaRejeitarConsentimento() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyRejeitarConsentimento)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentIdParaRejeitarConsentimento = extractor.get("data.consentId");
        return response;
    }
    public static Response obterConsentIdParaConsentimentoJaRejeitado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyConsentimentoJaAutorizado)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentIdJaRejeitado = extractor.get("data.consentId");
        return response;
    }

    public static BodyAuthorize dadosConsentimentoJaRejeitado() throws Exception {
        obterConsentIdParaConsentimentoJaRejeitado();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(conta = conta.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }

    public static BodyAuthorize dadosRejeitarConsentimento() throws Exception {
        obterConsentIdParaRejeitarConsentimento();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(conta = conta.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
}
