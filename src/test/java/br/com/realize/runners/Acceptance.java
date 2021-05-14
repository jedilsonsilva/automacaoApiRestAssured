package br.com.realize.runners;

import br.com.realize.tests.fase02.Contas.ListaContas.requests.GetContaRequest;
import br.com.realize.tests.base.tests.BaseTest;
import br.com.realize.tests.auth.tests.PostAuthTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.realize.suites.Acceptance.class)
@Suite.SuiteClasses({
        //base
        BaseTest.class,
        //auth
        PostAuthTest.class,
        //ListaContas
        GetContaRequest.class,

})
public class Acceptance {
}
