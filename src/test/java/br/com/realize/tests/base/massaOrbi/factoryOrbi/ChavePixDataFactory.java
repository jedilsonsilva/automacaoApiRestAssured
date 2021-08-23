package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;

public class ChavePixDataFactory {

    static CriacaoMassaOrbi massaOrbiPessoa = new CriacaoMassaOrbi();
    static ObterDadosChavePix obterDadosChavePix = new ObterDadosChavePix();

    static String cpf;
    static String numeroAgencia;
    static String numeroConta;
    static String nomeCliente;

    public static void obterCpf() throws SQLException, ClassNotFoundException {
        Response response = massaOrbiPessoa.criarPessoa();
        JsonPath extractor = (JsonPath) response.jsonPath();
        cpf = extractor.get("person.cpfCnpj");
    }
    public static void obterInformacoesChavePix() throws SQLException, ClassNotFoundException{
        Response response = (Response) obterDadosChavePix.dadosChavePix();
        JsonPath extractor = (JsonPath) response.jsonPath();
        numeroAgencia = extractor.get("branchNumber");
        numeroConta = extractor.get("accountNumber");
        nomeCliente = extractor.get("name");

        System.out.println("O número da agência da conta é " + numeroAgencia);
        System.out.println("O número da conta é " + numeroConta);
        System.out.println("O nome do(a) cliente é " + nomeCliente);
    }
    public static BodyChavePix dadosChavePix() throws SQLException, ClassNotFoundException {
        obterCpf();
        obterInformacoesChavePix();
        BodyChavePix bodyChavePix = new BodyChavePix();
        bodyChavePix.setTpChave("CPF");
        bodyChavePix.setTxChave(cpf);
        bodyChavePix.setTpPessoa("NATURAL_PERSON");
        bodyChavePix.setNrCpfCnpjPessoa(cpf);
        bodyChavePix.setNmPessoa(nomeCliente);
        bodyChavePix.setNmFantasiaPessoa("");
        bodyChavePix.setTpConta("TRAN");
        bodyChavePix.setNrAgencia(numeroAgencia);
        bodyChavePix.setNrConta(numeroConta);
        bodyChavePix.setDataAberturaConta("2021-07-30T16:36:55.237Z");
        bodyChavePix.setNrSpbParticipante("27351731");
        return bodyChavePix;
    }
}
