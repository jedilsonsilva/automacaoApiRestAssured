package br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.sql.SQLException;
import java.util.Locale;
import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.cpf;
import static io.restassured.RestAssured.given;

public class ConsentimentoPagamentoDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static Faker fake = new Faker(new Locale("pt-br"));
    static String nameCliente;
    static String agencia;
    static String numeroConta;

    public static String idempotencyPagamento = fake.internet().password();
    public static String idempotencyConsentimentoJaAutorizado = fake.internet().password();
    public static String idempotencyAutorizarConsentimento = fake.internet().password();
    public static String idempotencyRejeitarConsentimento = fake.internet().password();

    //ESSE TOKEN AINDA É UM ALEATÓRIO ATÉ QUE A SEGURANÇA DAS API's SEJAM IMPLEMENTADA
    public static String token = "676378126781236";
    public static String tokenInvalido = "987298274987483";
    public static String url = "/payments/v1/consents/";
    //CONSENTID's PARA TESTES COM IDEMPOTENCIAS DIFERENTES
    public static String consentId;
    public static String consentIdParaAutorizarConsentimento;
    public static String consentIdParaRejeitarConsentimento;
    public static String consentIdInvalido;
    public static String consentIdAutorizado;
    public static String consentIdConsumed;

    public static String type;

    public static String ispb;
    public static String agenciaContaDebito;
    public static String numeroContaDebito;

    public static void obterDadosContaOrbi() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.dadosConsentimento();
        JsonPath extractor = (JsonPath) response.jsonPath();
        nameCliente = extractor.get("name");
        agencia = extractor.get("branchNumber");
        numeroConta = extractor.get("accountNumber");
    }

    public static BodyConsentimentoPagamento dadosParaCriarConsentId() throws Exception {
        obterDadosContaOrbi();
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

    public void obterDadosConsentIdEDemaisDadosDeConsentimento() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPagamento)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath extractor = (JsonPath) response.jsonPath();
        consentId = extractor.get("data.consentId");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaContaDebito = extractor.get("data.debtorAccount.issuer");
        numeroContaDebito = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        System.out.println("O consentId é " + consentId);
    }

    //AUTORIZAR CONSENTIMENTO
    public static Response obterConsentIdParaAutorizarConsentimento() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given().log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyAutorizarConsentimento)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentIdParaAutorizarConsentimento = extractor.get("data.consentId");
        return response;
    }

    public static Response obterConsentIdParaConsentimentoJaAutorizado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given().log().all()
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
        return response;
    }

    public static BodyAuthorize dadosAutorizarConsentimento() throws Exception {
        obterConsentIdParaAutorizarConsentimento();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber("001387275");//PARA UTILIZAR A VARIAVEL QUE TRAZ A CONTA CRIADA NO ORBI, DEVE CRIAR UM MECANISMO QUE ATUALIZA
        // A CONTA TIRANDO O "-", POIS NA CRIAÇÃO DE CONSENTIMENTO CARACTERES ESPECIAIS NAO SAO ACEITOS.
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }

    public static BodyAuthorize dadosAutorizarConsentimentoJaAutorizado() throws Exception {
        obterConsentIdParaConsentimentoJaAutorizado();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber("001387275");//PARA UTILIZAR A VARIAVEL QUE TRAZ A CONTA CRIADA NO ORBI, DEVE CRIAR UM MECANISMO QUE ATUALIZA
        // A CONTA TIRANDO O "-", POIS NA CRIAÇÃO DE CONSENTIMENTO CARACTERES ESPECIAIS NAO SAO ACEITOS.
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }

    //ATUALIZAR DADOS CONSENTIMENTO
    public static BodyAuthorize dadosAtualizacaoContaDebito() throws Exception {
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer("1774");
        bodyAuthorize.setNumber("000622524");
        bodyAuthorize.setType("TRAN");
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
                .post(url)
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
                .post(url)
                .then()
                .extract().response();
        JsonPath extractor = response.jsonPath();
        consentIdAutorizado = extractor.get("data.consentId");
        return response;
    }

    public static BodyAuthorize dadosConsentimentoJaRejeitado() throws Exception {
        obterConsentIdParaConsentimentoJaRejeitado();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber("001387275"); //PARA UTILIZAR A VARIAVEL QUE TRAZ A CONTA CRIADA NO ORBI, DEVE CRIAR UM MECANISMO QUE ATUALIZA
        // A CONTA TIRANDO O "-", POIS NA CRIAÇÃO DE CONSENTIMENTO CARACTERES ESPECIAIS NAO SAO ACEITOS.
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }

    public static BodyAuthorize dadosRejeitarConsentimento() throws Exception {
        obterConsentIdParaRejeitarConsentimento();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber("001387275"); //PARA UTILIZAR A VARIAVEL QUE TRAZ A CONTA CRIADA NO ORBI, DEVE CRIAR UM MECANISMO QUE ATUALIZA
        // A CONTA TIRANDO O "-", POIS NA CRIAÇÃO DE CONSENTIMENTO CARACTERES ESPECIAIS NAO SAO ACEITOS.
        bodyAuthorize.setType("TRAN");
        return bodyAuthorize;
    }


}
