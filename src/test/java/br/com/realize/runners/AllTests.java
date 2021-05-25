package br.com.realize.runners;

import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.tests.GetAtendimentoEletronicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.tests.GetAtendimentoTelefonicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.tests.GetCorrespondentesBancariosTest;
import br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.tests.GetDependenciasPropriasTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.tests.GetCartaoCreditoPessoaNaturalTest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.tests.GetEmprestimoPessoaJuridicaTest;
import br.com.realize.tests.fase02.Contas.Limites.tests.GetLimiteTest;
import br.com.realize.tests.fase02.Contas.ListaContas.tests.GetContaTest;
import br.com.realize.tests.fase02.Contas.Saldos.tests.GetSaldoTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.realize.suites.AllTests.class)
@Suite.SuiteClasses({
        GetContaTest.class,
        GetSaldoTest.class,
        GetLimiteTest.class,
        GetAtendimentoEletronicoTest.class,
        GetAtendimentoTelefonicoTest.class,
        GetDependenciasPropriasTest.class,
        GetCorrespondentesBancariosTest.class,
        GetCartaoCreditoPessoaNaturalTest.class
})
public class AllTests {
}
