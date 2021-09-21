package br.com.realize.tests.base.factory;

import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.AuthorizeReject.BodyAuthorize;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.ConsentimentoPagamento.BodyConsentimentoPagamento;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyCreditorAccount;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyData;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyPayment;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyPixPagamento;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Locale;
import static br.com.realize.tests.base.factory.ConsentimentoPagamentoPixDataFactory.*;
import static br.com.realize.tests.base.factory.AutorizacaoConsentimentoPagamentoPixDataFactory.*;
import static br.com.realize.tests.base.factory.RejeitarConsentimentoPagamentoPixDataFactory.urlRejectConsentimentoPagamento;
import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.agencia;
import static io.restassured.RestAssured.given;

public class PixPagamentoDataFactory {

    static Faker fake = new Faker(new Locale("pt-br"));
//VARIAVEIS COMUNS
    public static String urlPagamentoPix = "payments/v1/pix/payments/";
    public static String token = "676378126781236";
    public static String paymentId;
    public static String amount;
    public static String currency;
//VARIAVEIS PARA CENARIO DE SUCESSO
    public static String idempotencyPix = fake.internet().password();
    public static String agenciaParaInicicaoPagamentoPix;
    public static String contaParaInicicaoPagamentoPix;
    public static String consentIdAutorizadoParaPix;
    public static String cnpjConsentimento;
//VARIAVEIS PARA CONSENTIMENTO REJEITADO
    public static String idempotencyPixRejeitado = fake.internet().password();
    public static String agenciaParaInicicaoPagamentoPixRejeitado;
    public static String consentIdRejeitadoParaPix;
    public static String cnpjConsentimentoRejeitado;
    public static String numerContaConsentimentoRejeitado;
//VARIAVEIS PARA CONSENTIMENTO AGUARDANDO AUTORIZAÇÃO
    public static String idempotencyPixAguardandoAutorizacao = fake.internet().password();
    public static String consentIdAguardandoAutorizacaoParaPix;
    public static String agenciaParaInicicaoPagamentoPixAguardandoAutorizacao;
    public static String numerContaConsentimentoAguardandoAutorizacao;
    public static String cnpjConsentimentoAguardandoAutorizacao;
//VARIAVEIS PARA TESTES DE CONTRATO DE INCLUSAO PAGAMENTO PIX
    public static String idempotencyPixTesteContrato = fake.internet().password();
    public static String agenciaParaTesteContratoPix;
    public static String consentIdContratoParaPix;
    public static String cnpjConsentimentoContratoPix;
    public static String numerContaConsentimentoTesteContrato;
//VARIAVEIS PARA CENARIO DE PATH INVALIDO
    public static String idempotencyPixPathInvalido = fake.internet().password();
    public static String agenciaParaInicicaoPagamentoPixPathInvalido;
    public static String contaParaInicicaoPagamentoPixPathInvalido;
    public static String consentIdAutorizadoParaPixPathInvalido;
    public static String cnpjConsentimentoPathInvalido;
    public static String numerContaConsentimentoPathInvalido;
//VARIAVEIS PARA CENARIO DE PATH INVALIDO
    public static String idempotencyPixRequisicaoMalFormada = fake.internet().password();
    public static String agenciaParaInicicaoPagamentoPixRequisicaoMalFormada;
    public static String contaParaInicicaoPagamentoPixRequisicaoMalFormada;
    public static String consentIdAutorizadoParaPixRequisicaoMalFormada;
    public static String cnpjConsentimentoRequisicaoMalFormada;
    public static String numerContaConsentimentoRequisicaoMalFormada;
//VARIAVEIS PARA CENARIO DE SUCESSO DE CONSULTA
    public static String idempotencyPixConsulta = fake.internet().password();
    public static String agenciaParaInicicaoPagamentoPixConsulta;
    public static String contaParaInicicaoPagamentoPixConsulta;
    public static String consentIdAutorizadoParaPixConsulta;
    public static String cnpjConsentimentoConsulta;
    public static String numerContaConsentimentoConsulta;
//VARIAVEIS PARA TESTES DE CONTRATO DE CONSULTA PAGAMENTO PIX
    public static String idempotencyPixTesteContratoConsulta = fake.internet().password();
    public static String agenciaParaTesteContratoPixConsulta;
    public static String consentIdContratoParaPixConsulta;
    public static String cnpjConsentimentoContratoPixConsulta;
    public static String numerContaConsentimentoTesteContratoConsulta;
/****************************** CRIAÇÃO DE INICIAÇÃO DE PAGAMENTO PIX ******************************/

//DADOS DE CONSENTIMENTO AUTORIZADO
    public static void obterDadosConsentimentoAutorizadoParaPix() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) AutorizacaoConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPix)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        agenciaContaDebito = extractor.get("data.debtorAccount.issuer");
        numeroContaDebito = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaParaInicicaoPagamentoPix = extractor.get("data.debtorAccount.issuer");
        contaParaInicicaoPagamentoPix = extractor.get("data.debtorAccount.number");
        currency = extractor.get("data.payment.currency");
        amount = extractor.get("data.payment.amount");
        cnpjConsentimento = extractor.get("data.businessEntity.document.identification");
        consentIdAutorizadoParaPix = extractor.get("data.consentId");
    }
    public static BodyAuthorize dadosAutorizarConsentimento() throws Exception {
        obterDadosConsentimentoAutorizadoParaPix();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agenciaParaInicicaoPagamentoPix);
        bodyAuthorize.setNumber(contaParaInicicaoPagamentoPix = contaParaInicicaoPagamentoPix.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdAutorizado() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosAutorizarConsentimento();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdAutorizadoParaPix + "/authorize");
    }
    public static BodyPixPagamento dadosPixPagamento() throws Exception {
        obterConsentIdAutorizado();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimento);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb("27351731");
        bodyCreditorAccount.setIssuer(agenciaParaInicicaoPagamentoPix);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }

//DADOS DE CONSENTIMENTO REJEITADO
    public static void obterDadosConsentimentoRejeitadoParaPix() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixRejeitado)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaParaInicicaoPagamentoPixRejeitado = extractor.get("data.debtorAccount.issuer");
        numerContaConsentimentoRejeitado = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        currency = extractor.get("data.payment.currency");
        amount = extractor.get("data.payment.amount");
        cnpjConsentimentoRejeitado = extractor.get("data.businessEntity.document.identification");
        consentIdRejeitadoParaPix = extractor.get("data.consentId");
    }
    public static BodyAuthorize dadosRejeitarConsentimento() throws Exception {
        obterDadosConsentimentoRejeitadoParaPix();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(numerContaConsentimentoRejeitado = numerContaConsentimentoRejeitado.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdRejeitado() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosRejeitarConsentimento();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlRejectConsentimentoPagamento + consentIdRejeitadoParaPix + "/reject");
    }

    public static BodyPixPagamento dadosPixPagamentoRejeitado() throws Exception {
        obterConsentIdRejeitado();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoRejeitado);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb(ispb);
        bodyCreditorAccount.setIssuer(agenciaParaInicicaoPagamentoPixRejeitado);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
//DADOS DE CONSENTIMENTO AWAITING_AUTHORISATION
public static void obterDadosConsentimentoAguardandoAutorizacaoParaPix() throws Exception {

    BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
    Response response = (Response) given()
            .header("Authorization", token)
            .contentType("application/json")
            .header("x-idempotency-key", idempotencyPixAguardandoAutorizacao)
            .body(bodyConsentimentoPagamento)
            .when()
            .post(urlConsetimentoPagamento)
            .then()
            .statusCode(200)
            .extract().response();
    JsonPath extractor = (JsonPath) response.jsonPath();
    ispb = extractor.get("data.debtorAccount.ispb");
    agenciaParaInicicaoPagamentoPixAguardandoAutorizacao = extractor.get("data.debtorAccount.issuer");
    numerContaConsentimentoAguardandoAutorizacao = extractor.get("data.debtorAccount.number");
    type = extractor.get("data.debtorAccount.type");
    currency = extractor.get("data.payment.currency");
    amount = extractor.get("data.payment.amount");
    cnpjConsentimentoAguardandoAutorizacao = extractor.get("data.businessEntity.document.identification");
    consentIdAguardandoAutorizacaoParaPix = extractor.get("data.consentId");
}

    public static BodyPixPagamento dadosPixPagamentoAguardandoAutorizacao() throws Exception {
        obterDadosConsentimentoAguardandoAutorizacaoParaPix();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoAguardandoAutorizacao);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb(ispb);
        bodyCreditorAccount.setIssuer(agenciaParaInicicaoPagamentoPixAguardandoAutorizacao);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
//DADOS DE CONSENTIMENTO PARA TESTE DE CONTRATO
    public static void obterDadosTesteContrato() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixTesteContrato)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        ispb = extractor.get("data.debtorAccount.ispb");
        type = extractor.get("data.debtorAccount.type");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaParaTesteContratoPix = extractor.get("data.debtorAccount.issuer");
        numerContaConsentimentoTesteContrato = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        currency = extractor.get("data.payment.currency");
        amount = extractor.get("data.payment.amount");
        cnpjConsentimentoContratoPix = extractor.get("data.businessEntity.document.identification");
        consentIdContratoParaPix = extractor.get("data.consentId");
    }
    public static BodyAuthorize dadosTesteContrato() throws Exception {
        obterDadosTesteContrato();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(numerContaConsentimentoTesteContrato = numerContaConsentimentoTesteContrato.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdAutorizadoTesteContrato() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosTesteContrato();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdContratoParaPix + "/authorize");
    }
    public static BodyPixPagamento dadosPixPagamentoTesteContrato() throws Exception {
        obterConsentIdAutorizadoTesteContrato();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoContratoPix);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb("27351731");
        bodyCreditorAccount.setIssuer(agenciaParaTesteContratoPix);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
//DADOS DE PAGAMENTO PIX PATH INVALIDO
    public static void obterDadosConsentimentoAutorizadoParaPixPathInvalido() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixPathInvalido)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        ispb = extractor.get("data.debtorAccount.ispb");
        type = extractor.get("data.debtorAccount.type");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaParaInicicaoPagamentoPixPathInvalido = extractor.get("data.debtorAccount.issuer");
        numerContaConsentimentoPathInvalido = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        currency = extractor.get("data.payment.currency");
        amount = extractor.get("data.payment.amount");
        cnpjConsentimentoPathInvalido = extractor.get("data.businessEntity.document.identification");
        consentIdAutorizadoParaPixPathInvalido = extractor.get("data.consentId");
    }
    public static BodyAuthorize dadosAutorizarConsentimentoPathInvalido() throws Exception {
        obterDadosConsentimentoAutorizadoParaPixPathInvalido();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agencia);
        bodyAuthorize.setNumber(numerContaConsentimentoPathInvalido = numerContaConsentimentoPathInvalido.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdAutorizadoPathInvalido() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosAutorizarConsentimentoPathInvalido();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdAutorizadoParaPixPathInvalido + "/authorize");
    }
    public static BodyPixPagamento dadosPixPagamentoPathInvalido() throws Exception {
        obterConsentIdAutorizadoPathInvalido();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoPathInvalido);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb("27351731");
        bodyCreditorAccount.setIssuer(agenciaParaInicicaoPagamentoPixPathInvalido);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
//DADOS DE PAGAMENTO PIX REQUISIÇÃO MALFORMADA

public static void obterDadosConsentimentoAutorizadoParaPixRequisicaoMalformada() throws Exception {
    BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
    Response response = (Response) given()
            .header("Authorization", token)
            .contentType("application/json")
            .header("x-idempotency-key", idempotencyPix)
            .body(bodyConsentimentoPagamento)
            .when()
            .post(urlConsetimentoPagamento)
            .then()
            .statusCode(200)
            .extract().response();
    JsonPath extractor = (JsonPath) response.jsonPath();
    ispb = extractor.get("data.debtorAccount.ispb");
    type = extractor.get("data.debtorAccount.type");
    ispb = extractor.get("data.debtorAccount.ispb");
    agenciaParaInicicaoPagamentoPixRequisicaoMalFormada = extractor.get("data.debtorAccount.issuer");
    numerContaConsentimentoRequisicaoMalFormada = extractor.get("data.debtorAccount.number");
    type = extractor.get("data.debtorAccount.type");
    currency = extractor.get("data.payment.currency");
    amount = extractor.get("data.payment.amount");
    cnpjConsentimentoRequisicaoMalFormada = extractor.get("data.businessEntity.document.identification");
    consentIdAutorizadoParaPixRequisicaoMalFormada = extractor.get("data.consentId");
}
    public static BodyAuthorize dadosAutorizarConsentimentoRequisicaoMalformada() throws Exception {
        obterDadosConsentimentoAutorizadoParaPixRequisicaoMalformada();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agenciaParaInicicaoPagamentoPixRequisicaoMalFormada);
        bodyAuthorize.setNumber(numerContaConsentimentoRequisicaoMalFormada = numerContaConsentimentoRequisicaoMalFormada.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdAutorizadoRequisicaoMalformada() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosAutorizarConsentimentoRequisicaoMalformada();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdAutorizadoParaPixRequisicaoMalFormada + "/authorize");
    }
    public static BodyPixPagamento dadosPixPagamentoRequisicaoMalformada() throws Exception {
        obterConsentIdAutorizadoRequisicaoMalformada();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoRequisicaoMalFormada);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb("27351731");
        bodyCreditorAccount.setIssuer(agenciaParaInicicaoPagamentoPixRequisicaoMalFormada);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }


/****************************** CONSULTA DE INICIAÇÃO DE PAGAMENTO PIX ******************************/
//DADOS DE CONSENTIMENTO AUTORIZADO

public static void obterDadosConsentimentoAutorizadoParaConsultaPix() throws Exception {
    BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
    Response response = (Response) given()
            .header("Authorization", token)
            .contentType("application/json")
            .header("x-idempotency-key", idempotencyPixConsulta)
            .body(bodyConsentimentoPagamento)
            .when()
            .post(urlConsetimentoPagamento)
            .then()
            .statusCode(200)
            .extract().response();
    JsonPath extractor = (JsonPath) response.jsonPath();
    ispb = extractor.get("data.debtorAccount.ispb");
    agenciaParaInicicaoPagamentoPixConsulta = extractor.get("data.debtorAccount.issuer");
    numerContaConsentimentoConsulta = extractor.get("data.debtorAccount.number");
    type = extractor.get("data.debtorAccount.type");
    currency = extractor.get("data.payment.currency");
    amount = extractor.get("data.payment.amount");
    cnpjConsentimentoConsulta = extractor.get("data.businessEntity.document.identification");
    consentIdAutorizadoParaPixConsulta = extractor.get("data.consentId");
}
    public static BodyAuthorize dadosAutorizarConsentimentoParaConsultaPix() throws Exception {
        obterDadosConsentimentoAutorizadoParaConsultaPix();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agenciaParaInicicaoPagamentoPixConsulta);
        bodyAuthorize.setNumber(numerContaConsentimentoConsulta = numerContaConsentimentoConsulta.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdAutorizadoParaConsultaPix() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosAutorizarConsentimentoParaConsultaPix();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdAutorizadoParaPixConsulta + "/authorize");
    }
    public static BodyPixPagamento dadosPixPagamentoParaConsultaPix() throws Exception {
        obterConsentIdAutorizadoParaConsultaPix();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoConsulta);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb("27351731");
        bodyCreditorAccount.setIssuer(agenciaParaInicicaoPagamentoPixConsulta);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
//OBTER O PAYMENTID PARA REALIZAR A CONSULTA DE INICIAÇÃO DE PAGAMENTO VIA PIX

public static void obterPaymentIdPix() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoParaConsultaPix();
        Response response = (Response)  given()
                .header("Authorization", consentIdAutorizadoParaPixConsulta)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixConsulta)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        paymentId = extractor.get("data.paymentId");
        System.out.println(paymentId);
    }
    public static void obterPaymentIdPixContrato() throws Exception {
        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamentoTesteContrato();
        Response response = (Response)  given()
                .header("Authorization", consentIdContratoParaPix)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixTesteContrato)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix)
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        paymentId = extractor.get("data.paymentId");
        System.out.println(paymentId);
    }
//DADOS DE CONSENTIMENTO PARA TESTE DE CONTRATO NA CONSULTA
    public static void obterDadosTesteContratoConsulta() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        Response response = (Response) given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPixTesteContratoConsulta)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento)
                .then().log().all()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = (JsonPath) response.jsonPath();
        type = extractor.get("data.debtorAccount.type");
        ispb = extractor.get("data.debtorAccount.ispb");
        agenciaParaTesteContratoPixConsulta = extractor.get("data.debtorAccount.issuer");
        numerContaConsentimentoTesteContratoConsulta = extractor.get("data.debtorAccount.number");
        type = extractor.get("data.debtorAccount.type");
        currency = extractor.get("data.payment.currency");
        amount = extractor.get("data.payment.amount");
        cnpjConsentimentoContratoPixConsulta = extractor.get("data.businessEntity.document.identification");
        consentIdContratoParaPixConsulta = extractor.get("data.consentId");
    }
    public static BodyAuthorize dadosTesteContratoConsulta() throws Exception {
        obterDadosTesteContratoConsulta();
        BodyAuthorize bodyAuthorize = new BodyAuthorize();
        bodyAuthorize.setIspb("27351731");
        bodyAuthorize.setIssuer(agenciaParaTesteContratoPixConsulta);
        bodyAuthorize.setNumber(numerContaConsentimentoTesteContratoConsulta = numerContaConsentimentoTesteContratoConsulta.replace("-",""));
        bodyAuthorize.setAccountType("TRAN");
        return bodyAuthorize;
    }
    public static void obterConsentIdAutorizadoTesteContratoConsulta() throws Exception {
        BodyAuthorize bodyAuthorize = PixPagamentoDataFactory.dadosTesteContratoConsulta();
        Response response = (Response) given()
                .body(bodyAuthorize)
                .when()
                .post(urlAuthorizeConsentimentoPagamento + consentIdContratoParaPixConsulta + "/authorize");
    }
    public static BodyPixPagamento dadosPixPagamentoTesteContratoConsulta() throws Exception {
        obterConsentIdAutorizadoTesteContratoConsulta();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
        bodyData.setCreditorAccount(bodyCreditorAccount);
        bodyData.setCnpjInitiator(cnpjConsentimentoContratoPixConsulta);
        bodyCreditorAccount.setAccountType("TRAN");
        bodyCreditorAccount.setIspb("27351731");
        bodyCreditorAccount.setIssuer(agenciaParaTesteContratoPixConsulta);
        bodyCreditorAccount.setNumber("0020893462");
        bodyData.setLocalInstrument("MANU");
        bodyData.setPayment(bodyPayment);
        bodyPayment.setAmount(amount);
        bodyPayment.setCurrency(currency);
        bodyData.setProxy("12345678901");
        bodyData.setQrCode("qrCodeTest");
        bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
}
