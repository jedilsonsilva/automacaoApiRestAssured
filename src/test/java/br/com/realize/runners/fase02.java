package br.com.realize.runners;

import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.Contas.Limites.tests.GetLimiteTest;
import br.com.realize.tests.fase02.Contas.ListaContas.tests.GetContaTest;
import br.com.realize.tests.fase02.Contas.Saldos.tests.GetSaldoTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(Healthcheck.class)
@Suite.SuiteClasses({
        GetContaTest.class,
        GetSaldoTest.class,
        GetLimiteTest.class
})
public class fase02 {
}
