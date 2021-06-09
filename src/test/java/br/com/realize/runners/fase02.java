package br.com.realize.runners;

import br.com.realize.suites.Healthcheck;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.tests.GetContaPagamentoPosPagoTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPagoId.tests.GetContaPagamentoPosPagoIDTest;
import br.com.realize.tests.fase02.CartaoCredito.LimitesCreditoContaPosPago.tests.GetLimiteCartaoCreditoTest;
import br.com.realize.tests.fase02.Contas.IdentificacaoConta.tests.GetIdentificacaoContaTest;
import br.com.realize.tests.fase02.Contas.Limites.tests.GetLimiteTest;
import br.com.realize.tests.fase02.Contas.ListaContas.tests.GetContaTest;
import br.com.realize.tests.fase02.Contas.Saldos.tests.GetSaldoTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(Healthcheck.class)
@Suite.SuiteClasses({
        //CONTAS
        GetContaTest.class,
        GetSaldoTest.class,
        GetLimiteTest.class,
        GetIdentificacaoContaTest.class,
        //CARTAO DE CREDITO
        GetContaPagamentoPosPagoTest.class,
        GetContaPagamentoPosPagoIDTest.class,
        GetLimiteCartaoCreditoTest.class

})
public class fase02 {
}
