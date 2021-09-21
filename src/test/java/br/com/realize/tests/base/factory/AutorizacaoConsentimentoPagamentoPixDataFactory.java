package br.com.realize.tests.base.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.AuthorizeReject.BodyAuthorize;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.*;
import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.conta;
import static io.restassured.RestAssured.given;

public class AutorizacaoConsentimentoPagamentoPixDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();

    static Faker fake = new Faker(new Locale("pt-br"));
//VARIAVEIS COMUNS
    public static String urlAuthorizeConsentimentoPagamento = "payments/v1/consent-request/";
    public static String urlConsetimentoPagamentoPixParaautorizacao = "/payments/v1/consents/";
    public static String token = "676378126781236";
//VARIAVEIS PARA AUTORIZAÇÃO DE CONSENTIMENTO DE PAGAMENTO VIA PIX COM SUCESSO
    public static String idempotencyAutorizacaoConsentimentoPagamentoPix = fake.internet().password();
    public static String consentIdParaAutorizarConsentimento;
    public static String ispb;
    public static String agenciaContaDebito;
    public static String numeroContaDebito;
    public static String accountType;
//VARIAVEIS PARA CONSENTIMENTO JA AUTORIZADO
    public static String idempotencyConsentimentoJaAutorizado = fake.internet().password();
    public static String consentIdJaAutorizado;


    //DADOS PARA AUTORIZAR CONSENTIMENTO DE PAGAMENTO POR PIX COM SUCESSO
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

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyAutorizacaoConsentimentoPagamentoPix)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamentoPixParaautorizacao)
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath extractor = (JsonPath) response.jsonPath();
        consentIdParaAutorizarConsentimento = extractor.get("data.consentId");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaContaDebito = extractor.get("data.debtorAccount.issuer");
        numeroContaDebito = extractor.get("data.debtorAccount.number");
        ispb = extractor.get("data.debtorAccount.ispb");
        accountType = extractor.get("data.debtorAccount.type");
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

//DADOS PARA TENTAR AUTORIZAR UM CONSENTIMENTO DE PAGAMENTO VIA PIX JA AUTORIZADO

    public static Response obterConsentIdParaConsentimentoJaAutorizado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyConsentimentoJaAutorizado)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamentoPixParaautorizacao)
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

}
