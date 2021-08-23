package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.base.massaOrbi.GerarTokenOrbi;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class ObterDadosChavePix {

    Faker fake = new Faker(new Locale("pt-br"));
    GerarTokenOrbi geradorTokenOrbi = new GerarTokenOrbi();
    CriacaoMassaOrbi massaOrbiConta = new CriacaoMassaOrbi();

    String token;
    String id;
    int dockId;
    String tokenSaldo;
    int saldo;
    int valorSaldo = (int) fake.number().numberBetween(100, 99999);

    public void gerarToken() {

        Response response = geradorTokenOrbi.gerarToken();
        JsonPath extractor = (JsonPath) response.jsonPath();
        token = extractor.get("access_token");
    }
    public void gerarTokenSaldo() {
        Response response = geradorTokenOrbi.gerarTokenSaldo();
        JsonPath extractor = response.jsonPath();
        tokenSaldo = extractor.get("access_token");
    }
    public void recuperarIdConta() throws SQLException, ClassNotFoundException {
        Response response = massaOrbiConta.criarConta();
        JsonPath extractor = (JsonPath) response.jsonPath();
        dockId = extractor.get("accountDockId");
        id = extractor.get("id");

    }
    public Response obterDadosContaSaldo() throws SQLException, ClassNotFoundException {
        gerarToken();
        gerarTokenSaldo();
        recuperarIdConta();
        BodySaldoConta bodySaldoConta = new BodySaldoConta();
        bodySaldoConta.setIdTipoAjuste(1492);
        bodySaldoConta.setDataAjuste("2021-07-15T10:00:00");
        bodySaldoConta.setValorAjuste((int) (valorSaldo + .00));
        bodySaldoConta.setIdentificadorExterno(1);
        bodySaldoConta.setIdTransacaoOriginal(1);
        bodySaldoConta.setIdEstabelecimento(1);
        bodySaldoConta.setFlagAtendimento(false);
        bodySaldoConta.setMensagemAtendimento("Teste");
        bodySaldoConta.setDescricaoEstabelecimentoExterno("Teste");
        bodySaldoConta.setIdConta(dockId);
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
        System.out.println("O saldo inserido é " + saldo);
        return response;
    }
public Response dadosChavePix() throws SQLException, ClassNotFoundException {
    gerarToken();
    obterDadosContaSaldo();
    Response response = (Response) given()
            .header("Authorization", "Bearer " + token)
            .contentType("application/json")
            .when()
            .get("https://api-int-dev.realizecfi.io/orbi-bank-account-manager/accounts/" + id)
            .then()
            .statusCode(200)
            .extract().response();
    return response;
}
}
