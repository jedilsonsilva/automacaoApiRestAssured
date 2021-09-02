package br.com.realize.tests.base.massaOrbi;

import br.com.realize.tests.base.massaOrbi.factoryOrbi.*;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo.*;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.sql.SQLException;
import static io.restassured.RestAssured.given;

public class CriacaoMassaOrbi {

    GerarTokenOrbi geradorTokenOrbi = new GerarTokenOrbi();

    public static String cpf;
    String token;
    String idConta;
    int externalCardId;
    int dockId;
    String tokenSaldo;
    int saldo;
    String chaveGerada;
    String qrCode;
    String transactionId;
    public static int mailingAddressId;
    public static int personDockId;
    public static String personId;

    public void gerarToken() {

        geradorTokenOrbi.gerarToken();
        Response response = geradorTokenOrbi.gerarToken();
        JsonPath extractor = (JsonPath) response.jsonPath();
        token = extractor.get("access_token");
    }
    public void gerarTokenSaldo() {
        Response response = geradorTokenOrbi.gerarTokenSaldo();
        JsonPath extractor = response.jsonPath();
        tokenSaldo = extractor.get("access_token");
    }
    public Response criarPessoa() {
        gerarToken();
        BodyPessoa bodyPessoa = PessoaDataFactory.dadosPessoa();
        Response response = (Response) given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(bodyPessoa)
                .when()
                .post("https://api-int-dev.realizecfi.io/orbi-person-manager/v1/person")
                .then()
                .statusCode(201)
                .extract().response();
        JsonPath extractor = response.jsonPath();
        cpf = extractor.get("person.cpfCnpj");
        mailingAddressId = extractor.get("person.address.id");
        personDockId = extractor.get("person.dockId");
        personId = extractor.get("person.id");
        //System.out.println("O CPF do cliente é " + cpf);

        return response;
    }
    public Response criarConta() {
        criarPessoa();
        gerarToken();
        BodyConta bodyConta = ContaDataFactory.dadosConta();
        Response response = (Response) given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(bodyConta)
                .when()
                .post("https://api-int-dev.realizecfi.io/orbi-bank-account-manager/v1/accounts/")
                .then()
                .statusCode(201)
                .extract().response();
        JsonPath extractor = response.jsonPath();
        idConta = extractor.get("id");
       // System.out.println("O Id da conta é " + idConta);
        return response;
    }
    public Response dadosConsentimento() throws SQLException, ClassNotFoundException {
        gerarToken();
        criarConta();
        Response response = (Response) given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .when()
                .get("https://api-int-dev.realizecfi.io/orbi-bank-account-manager/accounts/" + idConta)
                .then()
                .statusCode(200)
                .extract().response();
        return response;
    }

    public Response criacaoContaOrbiComSaldo() throws SQLException, ClassNotFoundException {
        gerarToken();
        gerarTokenSaldo();
        BodySaldoConta bodySaldoConta = SaldoDataFactory.dadosSaldo();
        Response response = (Response) given()
                .header("Authorization", tokenSaldo)
                .contentType("application/json")
                .body(bodySaldoConta)
                .when()
                .post("https://api.hml.caradhras.io/ajustes-financeiros")
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = response.jsonPath();
        saldo = extractor.get("valor");
        dockId = extractor.get("idConta");
      //  System.out.println("O saldo inserido é " + saldo);
      //  System.out.println("O dockId inserido é " + dockId);
        return response;
    }
    public Response criarChavePix () throws SQLException, ClassNotFoundException{
        gerarToken();
        BodyChavePix bodyChavePix = ChavePixDataFactory.dadosChavePix();
        Response response = (Response) given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(bodyChavePix)
                .when()
                .post("http://api-dictcrk-hml.realizecfi.io:5180/api/entries/IncluiChave")
                .then().log().body()
                .statusCode(200)
                .extract().response();
        JsonPath extractor = response.jsonPath();
        chaveGerada = extractor.get("txChaveGerada");
      //  System.out.println("A chave PIX gerada é " + chaveGerada);
        return response;
    }
    public Response criacaoCartao() throws SQLException, ClassNotFoundException {
        gerarToken();
        BodyCartao bodyCartao = CartaoDataFactory.dadosCartao();
        Response response = (Response) given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(bodyCartao)
                .when()
                .post("https://api-int-dev.realizecfi.io/dock-connector/v1/card/virtual")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath extractor = response.jsonPath();
        externalCardId = extractor.get("externalCardId");
        //System.out.println("O externalCardId do cliente é " + externalCardId);
        return response;
    }
    public Response criarQrCodePix () throws SQLException, ClassNotFoundException{
        gerarToken();
        BodyQrCodePix bodyQrCodePix = QrCodePixDataFactory.dadosQrCodePix();
        Response response = (Response) given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(bodyQrCodePix)
                .when()
                .post("https://api-int-dev.realizecfi.io/orbi-crk-connector/crk/payments/qrcode/static")
                .then()
                .statusCode(201)
                .extract().response();
        JsonPath extractor = response.jsonPath();
        qrCode = extractor.get("emv");
        transactionId = extractor.get("transactionId");
        //System.out.println("O QR Code gerado é " + qrCode);
       // System.out.println("O transactionId gerado é " + transactionId);
        return response;
    }
    }
