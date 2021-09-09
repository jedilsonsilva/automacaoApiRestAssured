package br.com.realize.tests.fase03.pagamentos.pixPagamento.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyCreditorAccount;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyData;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyPayment;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.pojo.BodyPixPagamento;
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
    public static String qrCode;
    public static String idempotencyPix = fake.internet().password();
    public static String idempotencyConsentimentoJaAutorizado = fake.internet().password();
    public static String idempotencyAutorizarConsentimento = fake.internet().password();
    public static String token = "676378126781236";
    public static String urlPagamento = "/payments/v1/consents/";
    public static String urlPagamentoPix = "/payments/v1/pix/payments/";
    public static String consentId;
    public static String consentIdAutorizado;
    public static String consentIdParaAutorizar;
    public static String paymentId;
    String tokenInvalido = "11223344556677";

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
    public static void obterQRCode() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.criarQrCodePix();
        JsonPath extractor = (JsonPath) response.jsonPath();
        qrCode = extractor.get("emv");
    }
    public static Response obterPaymentId() throws Exception {

        BodyPixPagamento bodyPixPagamento = (BodyPixPagamento) PixPagamentoDataFactory.dadosPixPagamento();
        Response response = (Response)  given()
                .header("Authorization", token)
                .contentType("application/json")
                .header("x-idempotency-key", idempotencyPix)
                .body(bodyPixPagamento)
                .when()
                .post(urlPagamentoPix)
                .then()
                .extract().response();

        JsonPath extractor = response.jsonPath();
        paymentId = extractor.get("data.paymentId");
        System.out.println("O paymenId é " + paymentId);
        return response;
    }

    public static BodyPixPagamento dadosPixPagamento() throws Exception {
        obterDados();
        obterQRCode();
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
            bodyData.setQrCode(qrCode);
            bodyData.setRemittanceInformation("Pagamento da nota XPTO035-002.");
        return bodyPixPagamento;
    }
}
