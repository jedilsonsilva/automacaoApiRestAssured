package br.com.realize.runners;

import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.tests.GetAtendimentoEletronicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.tests.GetAtendimentoTelefonicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.tests.GetCorrespondentesBancariosTest;
import br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.tests.GetDependenciasPropriasTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.realize.suites.Healthcheck.class)
@Suite.SuiteClasses({
        GetAtendimentoEletronicoTest.class,
        GetAtendimentoTelefonicoTest.class,
        GetCorrespondentesBancariosTest.class,
        GetDependenciasPropriasTest.class
})
public class fase01 {
}
