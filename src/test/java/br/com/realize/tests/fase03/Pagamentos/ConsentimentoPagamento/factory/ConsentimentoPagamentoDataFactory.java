package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;

import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.cpf;
import static io.restassured.RestAssured.given;

public class ConsentimentoPagamentoDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static Faker fake = new Faker(new Locale("pt-br"));
    static String nameCliente;
    static String agencia;
    static String numeroConta;
    public static String idempotency = fake.internet().password();
    public static String idempotencyConsentimentoJaAutorizado = fake.internet().password();
    public static String idempotencyAutorizarConsentimento = fake.internet().password();
    public static String token = "676378126781236";
    public static String url = "/payments/v1/consents/";
    public static String consentId;
    public static String consentIdAutorizado;
    public static String consentIdParaAutorizar;

    public static void obterDados() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.dadosConsentimento();
        JsonPath extractor = (JsonPath) response.jsonPath();
            nameCliente = extractor.get("name");
            agencia = extractor.get("branchNumber");
            numeroConta = extractor.get("accountNumber");
          //  System.out.println("O nome do cliente é " + nameCliente);
          //  System.out.println("A Agência do cliente é " + agencia);
          //  System.out.println("A conta do cliente é " + numeroConta);
    }

    public static BodyConsentimentoPagamento dadosConsentId() throws Exception {
        obterDados();
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
        bodyDebtorAccount.setIspb("12345678");
        bodyDebtorAccount.setIssuer(agencia);
        bodyDebtorAccount.setNumber(numeroConta);
        bodyDebtorAccount.setAccountType("TRAN");
        return bodyConsentimentoPagamento;
    }
    public static Response obterConsentId() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosConsentId();
        Response response = (Response)  given().log().all()
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
       // System.out.println("O consentId é " + consentId);
        return response;
    }
    public static Response obterConsentIdParaAutorizarConsentimento() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosConsentId();
        Response response = (Response)  given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyAutorizarConsentimento)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .extract().response();

        JsonPath extractor = response.jsonPath();
        consentIdParaAutorizar = extractor.get("data.consentId");
        // System.out.println("O consentId é " + consentId);
        return response;
    }
    public static Response obterConsentIdParaConsentimentoJaAutorizado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosConsentId();
        Response response = (Response)  given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyConsentimentoJaAutorizado)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .extract().response();

        JsonPath extractor = response.jsonPath();
        consentIdAutorizado = extractor.get("data.consentId");
        // System.out.println("O consentId é " + consentId);
        return response;
    }

    public static BodyAuthorize dadosAutorizarConsentimento() throws Exception {
        obterConsentIdParaAutorizarConsentimento();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber("001387275");
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }
    public static BodyAuthorize dadosAutorizarConsentimentoJaAutorizado() throws Exception {
        obterConsentIdParaConsentimentoJaAutorizado();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber("001387275");
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }


    public static BodyAuthorize dadosAtualizacaoContaDebito() throws Exception {
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer("1774");
        bodyAuthorize.setNumber("000622524");
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }
}
