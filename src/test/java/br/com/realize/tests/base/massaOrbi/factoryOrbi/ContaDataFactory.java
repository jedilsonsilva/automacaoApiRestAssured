package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.pojoOrbi.*;
import java.util.Collections;

import static br.com.realize.tests.base.massaOrbi.CriacaoMassaOrbi.*;


public class ContaDataFactory {

    public static BodyConta dadosConta() {
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
