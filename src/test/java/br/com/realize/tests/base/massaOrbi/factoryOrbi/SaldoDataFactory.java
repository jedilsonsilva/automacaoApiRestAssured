package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.SQLException;
import java.util.Locale;

public class SaldoDataFactory {

    static Faker fake = new Faker(new Locale("pt-br"));
    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();

    static int dockId;
    static int valorSaldo = (int) fake.number().numberBetween(100, 99999);

    public static void obterIdConta() throws SQLException, ClassNotFoundException {
        Response response = criacaoMassaOrbi.criarConta();
        JsonPath extractor = (JsonPath) response.jsonPath();
        dockId = extractor.get("accountDockId");
    }
    public static BodySaldoConta dadosSaldo() throws SQLException, ClassNotFoundException {
        obterIdConta();
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
        return bodySaldoConta;
    }
}
