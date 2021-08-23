package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.factory;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito.BodyAtualizacaoContaDebito;
import br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento.*;
import br.com.realize.utils.DataUtils;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;
import java.util.Locale;

public class ConsentimentoPagamentoDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();

    static String cpfCliente;
    static String nameCliente;
    static String agencia;
    static String numeroConta;

    static Faker fake = new Faker(new Locale("pt-br"));
    public static String consentId;

    public static void obterDados() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.criacaoContaOrbiComSaldo();
        JsonPath extractor = (JsonPath) response.jsonPath();
            cpfCliente = extractor.get("");
            nameCliente = extractor.get("");
            agencia = extractor.get("");
            numeroConta = extractor.get("");
    }

    public static BodyConsentimentoPagamento dadosConsentId() throws Exception {
        obterDados();
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
        bodyDocumentCpfPagamento.setIdentification(cpfCliente);
        bodyDocumentCpfPagamento.setRel("CPF");
        bodyDataPagamento.setBusinessEntity(bodyBusinessEntityPagamento);
        bodyBusinessEntityPagamento.setDocument(bodyDocumentCnpjPagamento);
        bodyDocumentCnpjPagamento.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpjPagamento.setRel("CNPJ");
        bodyDataPagamento.setCreditor(bodyCreditor);
        bodyCreditor.setPersonType("PESSOA_NATURAL");
        bodyCreditor.setCpfCnpj(geradorCpfCnpjRG.geraCPF());
        bodyCreditor.setName(nameCliente);
        bodyDataPagamento.setPayment(bodyPayment);
        bodyPayment.setType("PIX");
        bodyPayment.setDate(DataUtils.getDateTime());
        bodyPayment.setCurrency("BRL");
        bodyPayment.setAmount("100000.12");
        bodyDataPagamento.setDebtorAccount(bodyDebtorAccount);
        bodyDebtorAccount.setIspb("12345678");
        bodyDebtorAccount.setIssuer(agencia);
        bodyDebtorAccount.setNumber(numeroConta);
        bodyDebtorAccount.setAccountType("TRAN");
        return bodyConsentimentoPagamento;
    }

    public static BodyAtualizacaoContaDebito dadosAtualizacaoContaDebito() throws Exception {
        BodyAtualizacaoContaDebito bodyAtualizacaoContaDebito = new BodyAtualizacaoContaDebito();
        bodyAtualizacaoContaDebito.setIspb("27351731");
        bodyAtualizacaoContaDebito.setIssuer("1774");
        bodyAtualizacaoContaDebito.setNumber("000622524");
        bodyAtualizacaoContaDebito.setType("TRAN");
        return bodyAtualizacaoContaDebito;
    }
}
