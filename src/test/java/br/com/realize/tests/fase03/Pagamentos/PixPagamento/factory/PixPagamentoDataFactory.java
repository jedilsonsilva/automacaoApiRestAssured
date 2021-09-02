package br.com.realize.tests.fase03.Pagamentos.PixPagamento.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AuthorizeReject.BodyAuthorize;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.tests.fase03.Pagamentos.PixPagamento.pojo.BodyCreditorAccount;
import br.com.realize.tests.fase03.Pagamentos.PixPagamento.pojo.BodyData;
import br.com.realize.tests.fase03.Pagamentos.PixPagamento.pojo.BodyPayment;
import br.com.realize.tests.fase03.Pagamentos.PixPagamento.pojo.BodyPixPagamento;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class PixPagamentoDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static Faker fake = new Faker(new Locale("pt-br"));
    static String nameCliente;
    static String agencia;
    static String numeroConta;
    public static String idempotencyPix = fake.internet().password();
    public static String idempotencyConsentimentoJaAutorizado = fake.internet().password();
    public static String idempotencyAutorizarConsentimento = fake.internet().password();
    public static String token = "676378126781236";
    public static String url = "/payments/v1/consents/";
    public static String consentId;
    public static String consentIdAutorizado;
    public static String consentIdParaAutorizar;

    public static void obterDados() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.dadosConsentimento();
        JsonPath extractor = (JsonPath) response.jsonPath();
        nameCliente = extractor.get("name");
        agencia = extractor.get("branchNumber");
        numeroConta = extractor.get("accountNumber");
        //  System.out.println("O nome do cliente é " + nameCliente);
        //  System.out.println("A Agência do cliente é " + agencia);
        //  System.out.println("A conta do cliente é " + numeroConta);
    }

    public static BodyPixPagamento dadosPixPagamento() throws Exception {
        obterDados();
        BodyPixPagamento bodyPixPagamento = new BodyPixPagamento();
        BodyData bodyData = new BodyData();
        BodyCreditorAccount bodyCreditorAccount = new BodyCreditorAccount();
        BodyPayment bodyPayment = new BodyPayment();

        bodyPixPagamento.setData(bodyData);
            bodyData.setCreditorAccount(bodyCreditorAccount);
                bodyCreditorAccount.setIspb("12345678");
                bodyCreditorAccount.setIssuer(agencia);
                bodyCreditorAccount.setNumber(numeroConta);
                bodyCreditorAccount.setType("CACC");
            bodyData.setLocalInstrument("DICT");
            bodyData.setPayment(bodyPayment);
                bodyPayment.setAmount("100000.12");
                bodyPayment.setCurrency("BRL");
            bodyData.setProxy("12345678901");
            bodyData.setQrCode("00020104141234567890123426660014BR.GOV.BCB.PIX014466756C616E6F32303139406578616D706C652E636F6D27300012  \\nBR.COM.OUTRO011001234567895204000053039865406123.455802BR5915NOMEDORECEBEDOR6008BRASILIA61087007490062  \\n530515RP12345678-201950300017BR.GOV.BCB.BRCODE01051.0.080450014BR.GOV.BCB.PIX0123PADRAO.URL.PIX/0123AB  \\nCD81390012BR.COM.OUTRO01190123.ABCD.3456.WXYZ6304EB76\\n");
            bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
}
