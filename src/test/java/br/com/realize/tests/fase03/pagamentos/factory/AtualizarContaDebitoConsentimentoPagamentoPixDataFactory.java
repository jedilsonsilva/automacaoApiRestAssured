package br.com.realize.tests.fase03.pagamentos.factory;

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
import static io.restassured.RestAssured.given;

public class AtualizarContaDebitoConsentimentoPagamentoPixDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();

    static Faker fake = new Faker(new Locale("pt-br"));
//VARIAVEIS COMUNS
    public static String urlAtualizarContaDebitoConsentimentoPagamento = "payments/v1/consent-request/";
    public static String urlConsetimentoPagamentoPixParaAtualizacaoContaDebito = "/payments/v1/consents/";
    public static String tokenAtualizacaoContaDebito = "676378126781236";
//VARIAVEIS PARA ATUALIZAÇÃO DE CONTA DEBITO DE CONSENTIMENTO DE PAGAMENTO VIA PIX COM SUCESSO
    public static String idempotencyAtualizacaoContaDebitoConsentimentoPagamentoPix = fake.internet().password();
    public static String consentIdParaAtualizacaoContaDebitoConsentimento;
    public static String ispb;
    public static String agenciaContaDebitoParaAtualizacao;
    public static String numeroContaDebitoParaAtualizacao;
    public static String accountType;
    public static String consentIdInvalidoAtualizacaoContaDebito;

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
    public static Response criarConsentimentoPagamentoPixParaAtualizacaoContaDebito() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", tokenAtualizacaoContaDebito)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyAtualizacaoContaDebitoConsentimentoPagamentoPix)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamentoPixParaAtualizacaoContaDebito)
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath extractor = (JsonPath) response.jsonPath();
        consentIdParaAtualizacaoContaDebitoConsentimento = extractor.get("data.consentId");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaContaDebitoParaAtualizacao = extractor.get("data.debtorAccount.issuer");
        numeroContaDebitoParaAtualizacao = extractor.get("data.debtorAccount.number");
        ispb = extractor.get("data.debtorAccount.ispb");
        accountType = extractor.get("data.debtorAccount.type");
        return response;
    }

//FALTA CRIAR UM MÉTODO QUE CRIE UMA OUTRA CONTA PARA O MESMO CLIENTE
//PARA ASSIM ATUALIZAR A CONT DE DÉBITO MANTENDO O MESMO CLIENTE.

//ATUALIZAR DADOS CONSENTIMENTO
        public static BodyAuthorize dadosAtualizacaoContaDebito() throws Exception {
        criarConsentimentoPagamentoPixParaAtualizacaoContaDebito();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer("0001");
        bodyAuthorize.setNumber(numeroContaDebitoParaAtualizacao = numeroContaDebitoParaAtualizacao.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
}
