package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi;
import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.sql.SQLException;
import java.util.Collections;

public class ContaDataFactory {

    static CriacaoMassaOrbi criacaoMassaOrbi = new CriacaoMassaOrbi();
    static int mailingAddressId;
    static int personDockId;
    static String personId;

    public static void dadosPessoa() {

        Response response = criacaoMassaOrbi.criarPessoa();
        JsonPath extractor = (JsonPath) response.jsonPath();
        mailingAddressId = extractor.get("person.address.id");
        personDockId = extractor.get("person.dockId");
        personId = extractor.get("person.id");
    }
    @org.jetbrains.annotations.NotNull
    public static BodyConta dadosConta() {
        dadosPessoa();
        BodyConta bodyConta = new BodyConta();
        bodyConta.setCommercialOriginId(1);
        bodyConta.setIncome(1000);
        bodyConta.setMailingAddressId(mailingAddressId);
        bodyConta.setPersonConductorId(null);
        bodyConta.setPersonDockId(personDockId);
        bodyConta.setPoints(0);
        bodyConta.setPersonId(personId);
        bodyConta.setProducts(Collections.singletonList("BANK_ACCOUNT"));
        return bodyConta;
    }
}
