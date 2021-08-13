package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.requests;

import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Date;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class PostConsentimentoPagamentoRequest {

    Faker fake = new Faker(new Locale("pt-br"));
    String idempotency = String.valueOf(fake.random());
    String token = "676378126781236";
    String url = "/payments/v1/consents";
    String mesmoIdempotency = "1234567890";
    String tokenInvalido = "11223344556677";


    @Step("Insere um pedido de consentimento de pagamento")

    public Response inserirPedidoConsetimentoPagamento() throws Exception {

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
            bodyDebtorAccount.setIssuer("1111");
            bodyDebtorAccount.setNumber("0006225246");
            bodyDebtorAccount.setAccountType("TRAN");


            return  given()
                    .header("Authorization", token)
                    .contentType("application/json")
                    .header("x-idempotency-key", idempotency)
                    .body(bodyConsentimentoPagamento)
                    .when()
                    .post(url);
    }
    @Step("Inserir um pedido de consentimento de pagamento com um idempotency-key já usado mas com dados diferentes")

    public Response idempotencyUsadoDadosDiferentes() throws Exception {

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
        bodyDebtorAccount.setIspb("12345678");
        bodyDebtorAccount.setIssuer("1774");
        bodyDebtorAccount.setNumber("0006225246");
        bodyDebtorAccount.setAccountType("TRAN");

        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", mesmoIdempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }

    @Step("404 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response pathInvalido() throws Exception {

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
        bodyDebtorAccount.setIspb("12345678");
        bodyDebtorAccount.setIssuer("1774");
        bodyDebtorAccount.setNumber("1234567890");
        bodyDebtorAccount.setAccountType("CACC");

        return given().log().body()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url + "ss");
    }
    /*@Step("401 - Cabeçalho de autenticação ausente/inválido ou token inválido")
    public Response tokenInvalido() throws Exception {

        bodyConsentimento bodyConsentimento = new bodyConsentimento();
        bodyDocumentCpf bodyDocumentCpf = new bodyDocumentCpf();
        bodyDocumentCnpj bodyDocumentCnpj = new bodyDocumentCnpj();
        bodyBusinessEntity bodyBusinessEntity = new bodyBusinessEntity();
        bodyLoggedUser bodyLoggedUser = new bodyLoggedUser();

        bodyConsentimento.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyConsentimento.setExpirationDateTime(expirationDateTime);
        bodyConsentimento.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyConsentimento.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyConsentimento.setTransactionFromDateTime(transactionFromDateTime);
        bodyConsentimento.setTransactionToDateTime(transactionToDateTime);


        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }*/

    @Step("403 - O token tem escopo incorreto ou uma política de segurança foi violada.")
    public Response politicaSegurancaVioalada() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }
    @Step("404 - O recurso solicitado não existe ou não foi implementado.")
    public Response recursoInexistente() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post("consents/v2/oconsents");
    }
    @Step("405 - O consumidor tentou acessar o recurso com um método não suportado.")
    public Response metodoNaoSuportado() throws Exception {

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


        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .delete(url);

    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8.")
    public Response acceptDiferente() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .accept("application/xml")
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }
    @Step("415 - O formato do payload não é um formato suportado.")
    public Response payloadNaoSuportado() throws Exception {

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
                .contentType("text/plain")
                .header("x-idempotency-key", idempotency)
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
                .post(url);
    }
    @Step("422 - A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes..")
    public Response impossivelProcessarInstrucoes() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }

    @Step("429 - A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido.")
    public Response muitasSolicitacoesFeitas() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response requisicaoMalFormada() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response informacoesObrigatorias() throws Exception {

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
                .contentType("application/json")
                .header("x-idempotency-key", idempotency)
                .body(bodyConsentimentoPagamento)
                .when()
                .post(url);
    }
    @Step("500 - Ocorreu um erro no gateway da API ou no microsserviço.")
    public Response erroGateway() throws Exception {

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
                .header("x-idempotency-key", idempotency)
                .body("Body preenchido para simular o erro 500")
                .when()
                .post(url);
    }

}

