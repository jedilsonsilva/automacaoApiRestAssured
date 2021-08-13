package br.com.realize.tests.fase02.Consentimento.requests;

import br.com.realize.tests.fase02.Consentimento.pojo.*;
import br.com.realize.utils.geradorCpfCnpjRG;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Collections;
import static io.restassured.RestAssured.given;

public class PostConsentimentoRequest {

    String token = "676378126781236";
    String url = "consents/v1/consents";
    String tokenInvalido = "11223344556677";
    String expirationDateTime = "2021-05-21T08:30:00Z";
    String transactionFromDateTime = "2021-01-01T00:00:00Z";
    String transactionToDateTime = "2021-02-01T23:59:59Z";

    @Step("Insere um pedido de consentimento")

    public Response inserirPedidoConsetimento() throws Exception {

            BodyData bodyData = new BodyData();
            BodyConsentimento bodyConsentimento = new BodyConsentimento();
            BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
            BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
            BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
            BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
            bodyData.setBusinessEntity(bodyBusinessEntity);
                bodyBusinessEntity.setDocument(bodyDocumentCnpj);
                    bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
                    bodyDocumentCnpj.setRel("CNPJ");
            bodyData.setExpirationDateTime(expirationDateTime);
            bodyData.setLoggedUser(bodyLoggedUser);
                bodyLoggedUser.setDocument(bodyDocumentCpf);
                    bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
                    bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
            bodyData.setTransactionFromDateTime(transactionFromDateTime);
            bodyData.setTransactionToDateTime(transactionToDateTime);

            return  given().log().all()
                   // .header("Authorization", token)
                    .contentType("application/json")
                    .body(bodyConsentimento)
                    .when()
                    .post(url);
    }

    @Step("404 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response pathInvalido() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
               // .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
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

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }
    @Step("404 - O recurso solicitado não existe ou não foi implementado.")
    public Response recursoInexistente() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post("consents/v2/oconsents");
    }
    @Step("405 - O consumidor tentou acessar o recurso com um método não suportado.")
    public Response metodoNaoSuportado() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);


        return  given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .put(url);
    }
    @Step("406 - A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8.")
    public Response acceptDiferente() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .accept("application/xml")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }
    @Step("415 - O formato do payload não é um formato suportado.")
    public Response payloadNaoSuportado() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/xml")
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

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }

    @Step("429 - A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido.")
    public Response muitasSolicitacoesFeitas() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response requisicaoMalFormada() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }
    @Step("400 - A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.")
    public Response informacoesObrigatorias() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime("");

        return given()
                .contentType("application/json")
                .body(bodyConsentimento)
                .when()
                .post(url);
    }
    @Step("500 - Ocorreu um erro no gateway da API ou no microsserviço.")
    public Response erroGateway() throws Exception {

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);


        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body("Body preenchido para simular o erro 500")
                .when()
                .post(url);
    }

}

