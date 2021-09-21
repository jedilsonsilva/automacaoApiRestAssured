package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.requests;

import br.com.realize.tests.base.factory.ConsentimentoPagamentoPixDataFactory;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static br.com.realize.tests.base.factory.ConsentimentoPagamentoPixDataFactory.*;
import static io.restassured.RestAssured.given;

public class PostConsentimentoPagamentoRequest {

    @Step("Insere um pedido de consentimento de pagamento via PiX")

    public Response inserirPedidoConsetimentoPagamento() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return  given()
                    .header("Authorization", token)
                    .contentType("application/json")
                    .header("x-idempotency-key", idempotencyCriacaoConsentimento)
                    .body(bodyConsentimentoPagamento)
                    .when()
                    .post(urlConsetimentoPagamento);
    }
    @Step("Valida o contrato da inclusão de consentimento de pagamento PiX")

    public Response inserirGarantirContratoCriacaoConsentimento() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoContrato)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento);
    }
    @Step("Inserir um pedido de consentimento de pagamento por pix de pagamento com um idempotency-key já usado mas com dados diferentes")

    public Response idempotencyUsadoDadosDiferentes() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", "676378126781236")
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento);
    }

    @Step("404 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response pathInvalido() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoPathInvalido)
                .body(bodyConsentimentoPagamento)
                .when()
                .post("/payments/v5/consents/");
    }
    /*@Step("401 - Cabeçalho de autenticação ausente/inválido ou token inválido")
    public Response tokenInvalido() throws Exception {
        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoDataFactory.dadosParaCriarConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoTokenInvalido)
                .body(bodyConsentimento)
                .when()
                .post(url);
    }*/
    @Step("404 - O recurso solicitado não existe ou não foi implementado.")
    public Response recursoInexistente() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoRecursoInexistente)
                .body(bodyConsentimentoPagamento)
                .when()
                .post("consents/v2/oconsents");
    }
    @Step("405 - O consumidor tentou acessar o recurso com um método não suportado.")
    public Response metodoNaoSuportado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoMetodoNaoSuportado)
                .body(bodyConsentimentoPagamento)
                .when()
                .delete(urlConsetimentoPagamento);

    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8.")
    public Response acceptDiferente() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoAcceptDiferente)
                .accept("application/xml")
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento);
    }
    @Step("415 - O formato do payload não é um formato suportado.")
    public Response payloadNaoSuportado() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return given()
                .header("Authorization", token)
                .contentType("text/plain")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoPayloadNaoSuportado)
                .body("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<receita nome=\"pão\" tempo_de_preparo=\"5 minutos\" tempo_de_cozimento=\"1 hora\">\n" +
                "  <titulo>Pão simples</titulo>\n" +
                "  <ingredientes>\n" +
                "    <ingrediente quantidade=\"3\" unidade=\"xícaras\">Farinha de Trigo</ingrediente>\n" +
                "    <ingrediente quantidade=\"7\" unidade=\"gramas\">Fermento</ingrediente>\n" +
                "    <ingrediente quantidade=\"1.5\" unidade=\"xícaras\" estado=\"morna\">Água</ingrediente>\n" +
                "    <ingrediente quantidade=\"1\" unidade=\"colheres de chá\">Sal</ingrediente>\n" +
                "  </ingredientes>\n" +
                "  <instrucoes>\n" +
                "    <passo>Misture todos os ingredientes, e dissolva bem.</passo>\n" +
                "    <passo>Cubra com um pano e deixe por uma hora em um local morno.</passo>\n" +
                "    <passo>Misture novamente, coloque numa bandeja e asse num forno.</passo>\n" +
                "  </instrucoes>\n" +
                "</receita>")
                .when()
                .post(urlConsetimentoPagamento);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response requisicaoMalFormada() throws Exception {

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
        bodyDocumentCpfPagamento.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpfPagamento.setRel("CPF");
        bodyDataPagamento.setBusinessEntity(bodyBusinessEntityPagamento);
        bodyBusinessEntityPagamento.setDocument(bodyDocumentCnpjPagamento);
        bodyDocumentCnpjPagamento.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpjPagamento.setRel("CNPJ");
        bodyDataPagamento.setCreditor(bodyCreditor);
        bodyCreditor.setPersonType("PESSOA_NATURAL");
        bodyCreditor.setCpfCnpj("");
        bodyCreditor.setName("");
        bodyDataPagamento.setPayment(bodyPayment);
        bodyPayment.setType("PIX");
        bodyPayment.setDate(DataUtils.getDateTime());
        bodyPayment.setCurrency("BRL");
        bodyPayment.setAmount("100000.12");
        bodyDataPagamento.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("12345678");
        bodyDebtorAccount.setIssuer("1774");
        bodyDebtorAccount.setNumber("1234567890");
        bodyDebtorAccount.setAccountType("CACC");

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoRequisicaoMalformada)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response informacoesObrigatorias() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentIdComInformacoesObrigatorias();
        return given()
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoInformacoesObrigatorias)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(urlConsetimentoPagamento);
    }
    @Step("500 - Ocorreu um erro no gateway da API ou no microsserviço.")
    public Response erroGateway() throws Exception {

        BodyConsentimentoPagamento bodyConsentimentoPagamento = (BodyConsentimentoPagamento) ConsentimentoPagamentoPixDataFactory.dadosParaCriarConsentId();
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyCriacaoConsentimentoErroGateway)
                .body("Body preenchido para simular o erro 500")
                .when()
                .post(urlConsetimentoPagamento);
    }
}

