package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;

public class QrCodePixDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static String chaveGerada;

    public static void obterChavePix() throws SQLException, ClassNotFoundException {

        Response response = criacaoMassaOrbi.criarChavePix();
        JsonPath extractor = (JsonPath) response.jsonPath();
        chaveGerada = extractor.get("txChaveGerada");
    }
    public static BodyQrCodePix dadosQrCodePix() throws SQLException, ClassNotFoundException {
        obterChavePix();
        BodyQrCodePix bodyQrCodePix = new BodyQrCodePix();
        bodyQrCodePix.setCityName("Brasília");
        bodyQrCodePix.setDescription("Teste");
        bodyQrCodePix.setIspbNumber("27351731");
        bodyQrCodePix.setKeyValue(chaveGerada);
        bodyQrCodePix.setValue(0);
        return bodyQrCodePix;
    }
}
