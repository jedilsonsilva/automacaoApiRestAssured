package br.com.realize.runners;

import br.com.realize.suites.Contract;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.tests.GetAtendimentoEletronicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.tests.GetAtendimentoTelefonicoTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.tests.GetCartaoCreditoPessoaNaturalTest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.tests.GetEmprestimoPessoaJuridicaTest;
import br.com.realize.tests.fase02.Contas.Limites.tests.GetLimiteTest;
import br.com.realize.tests.fase02.Contas.ListaContas.tests.GetContaTest;
import br.com.realize.tests.fase02.Contas.Saldos.tests.GetSaldoTest;
import br.com.realize.tests.auth.tests.PostAuthTest;
import br.com.realize.tests.base.tests.BaseTest;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(Contract.class)
@Suite.SuiteClasses({
        //base
        BaseTest.class,
        //auth
        PostAuthTest.class,
        //AtendimentoAletronico
        GetAtendimentoEletronicoTest.class,
        //AtendimentoTelefonico
        GetAtendimentoTelefonicoTest.class,
        //CartoCreditoPessoaNatural
        GetCartaoCreditoPessoaNaturalTest.class,
        //EmprestimoPessoaJuridica
        GetEmprestimoPessoaJuridicaTest.class,
        //ListaContas
        GetContaTest.class,
        //SaldoContas
        GetSaldoTest.class,
        //LimiteContas
        GetLimiteTest.class

})
public class Contracts {
}
