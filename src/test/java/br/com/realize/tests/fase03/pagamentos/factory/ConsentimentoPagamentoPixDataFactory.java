package br.com.realize.tests.fase03.pagamentos.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Locale;

import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.*;
import static io.restassured.RestAssured.given;

public class ConsentimentoPagamentoPixDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static Faker fake = new Faker(new Locale("pt-br"));

    //VARIAVEIS COMUNS
    public static String urlConsetimentoPagamento = "/payments/v1/consents/";
    public static String token = "676378126781236";
    public static String tokenInvalido = "987298274987483";

    /***************** CONSULTA *****************/

//VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO
    public static String idempotencyCriacaoConsentimento = fake.internet().password();
    //VARIAVEIS PARA VALIDAÇÃO DO CONTRATO DE CRIAÇÃO DE CONSENTIMENTO
    public static String idempotencyCriacaoConsentimentoContrato = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM DADOS DIFERENTES
    public static String idempotencyCriacaoConsentimentoDadosDiferentes = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM ENPOINT INCORRETO
    public static String idempotencyCriacaoConsentimentoPathInvalido = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM TOKEN INVALIDO
    public static String idempotencyCriacaoConsentimentoTokenInvalido = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM RECURSOS INEXISTENTES
    public static String idempotencyCriacaoConsentimentoRecursoInexistente = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM MÉTODO NÃO SUPORTADO
    public static String idempotencyCriacaoConsentimentoMetodoNaoSuportado = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM ACCEPT DIFERENTE
    public static String idempotencyCriacaoConsentimentoAcceptDiferente = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM PAYLOAD NÃO SUPORTADO
    public static String idempotencyCriacaoConsentimentoPayloadNaoSuportado = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM REQUISIÇÃO MALFORMADA
    public static String idempotencyCriacaoConsentimentoRequisicaoMalformada = fake.internet().password();
    //VARIAVEIS PARA CRIAÇÃO DE CONSENTIMENTO COM SUCESSO COM CAMPOS OBRIGATÓRIOS NÃO PREENCHIDOS
    public static String idempotencyCriacaoConsentimentoInformacoesObrigatorias = fake.internet().password();
    //VARIAVEIS PARA VALIDAÇÃO DO CONTRATO DE CRIAÇÃO DE CONSENTIMENTO
    public static String idempotencyCriacaoConsentimentoErroGateway = fake.internet().password();

    /***************** CONSULTA *****************/

//VARIAVEIS PARA CONSULTA DE CONSENTIMENTO DE PAGAMENTO VIA PIX
    public static String idempotencyConsultaConsentimentoPagamentoPix = fake.internet().password();
    public static String consentIdParaConsulta;

    //VARIAVEIS PARA CONSULTA DE CONSENTIMENTO DE PAGAMENTO VIA PIX
    public static String idempotencyContratoConsultaConsentimentoPagamentoPix = fake.internet().password();
    public static String consentIdContratoConsultaConsentimentoPagamentoPix;

    //CONSENTID's PARA TESTES COM IDEMPOTENCIAS DIFERENTES
    public static String consentId;
    public static String consentIdInvalido = fake.internet().password();
    public static String type;

    /****************************** CRIAÇÃO DE CONSENTIMENTO INICIAÇÃO DE PAGAMENTO PIX ******************************/

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

    public static BodyConsentimentoPagamento dadosParaCriarConsentIdComInformacoesObrigatorias() throws Exception {
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
        bodyDocumentCnpjPagamento.setIdentification("");
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

    /****************************** CONSULTA DE CONSENTIMENTO INICIAÇÃO DE PAGAMENTO PIX ******************************/

//OBTER CONSENTID PARA CONSULTA DE CONSENTIMENTO DE PAGAMENTO VIA PIX
    public static BodyConsentimentoPagamento dadosParaCriarConsentIdParaConsulta() throws Exception {
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

    public static void obterConsentIdParaConsulta() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentIdParaConsulta();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyConsultaConsentimentoPagamentoPix)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        consentIdParaConsulta = extractor.get("data.consentId");
    }

    //OBTER CONSENTID PARA VALIDAÇÃO DE CONTRATO DA CONSULTA DE CONSENTIMENTO DE PAGAMENTO VIA PIX
    public static BodyConsentimentoPagamento dadosParaCriarConsentIdParaValidarContrato() throws Exception {
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

    public static void obterConsentIdParaValidarContratoConsulta() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentIdParaValidarContrato();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyContratoConsultaConsentimentoPagamentoPix)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        consentIdContratoConsultaConsentimentoPagamentoPix = extractor.get("data.consentId");
    }
}
