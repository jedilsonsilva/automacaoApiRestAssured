package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;

public class CartaoDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();

    static int externalAccountId;

    public static void dadosConta() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.criarConta();
        JsonPath extractor = (JsonPath) response.jsonPath();
        externalAccountId = extractor.get("accountDockId");
    }
    public static BodyCartao dadosCartao() throws SQLException, ClassNotFoundException {
        dadosConta();
        BodyCartao bodyCartao = new BodyCartao();
        bodyCartao.setBlocked("false");
        bodyCartao.setExternalAccountId(externalAccountId);
        bodyCartao.setValidationDate("2024-12-01T00:00:00.000Z");
        return bodyCartao;
    }
}
