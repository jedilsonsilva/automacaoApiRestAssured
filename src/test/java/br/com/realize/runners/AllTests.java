package br.com.realize.runners;

import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.tests.DelBackoffideOutagesTest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.tests.GetBackoffideOutagesTest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.tests.PostBackoffideOutagesTest;
import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.tests.PutBackoffideOutagesTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.tests.GetAtendimentoEletronicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.tests.GetAtendimentoTelefonicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.tests.GetCorrespondentesBancariosTest;
import br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.tests.GetDependenciasPropriasTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.tests.GetCartaoCreditoPessoaNaturalTest;
import br.com.realize.tests.fase01.ProdutosServicos.ContaPessoaNatural.tests.GetContaPessoaNaturalTest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.tests.GetEmprestimoPessoaJuridicaTest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaNatural.tests.GetEmprestimoPessoaNaturalTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.tests.GetContaPagamentoPosPagoTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPagoId.tests.GetContaPagamentoPosPagoIDTest;
import br.com.realize.tests.fase02.CartaoCredito.LimitesCreditoContaPosPago.requests.GetLimiteCartaoCreditoRequest;
import br.com.realize.tests.fase02.Contas.IdentificacaoConta.tests.GetIdentificacaoContaTest;
import br.com.realize.tests.fase02.Contas.Limites.tests.GetLimiteTest;
import br.com.realize.tests.fase02.Contas.ListaContas.tests.GetContaTest;
import br.com.realize.tests.fase02.Contas.Saldos.tests.GetSaldoTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.realize.suites.AllTests.class)
@Suite.SuiteClasses({

//***************FASE 01***************

//Feature - Canais de Atendimento
        GetAtendimentoEletronicoTest.class,
        GetAtendimentoTelefonicoTest.class,
        GetDependenciasPropriasTest.class,
        GetCorrespondentesBancariosTest.class,
//Feature - Produtos e Serviços
        GetCartaoCreditoPessoaNaturalTest.class,
        GetContaPessoaNaturalTest.class,
        GetEmprestimoPessoaJuridicaTest.class,
        GetEmprestimoPessoaNaturalTest.class,
//Feature - API's Comuns e Internas
        //Backoffice/Outages
        GetBackoffideOutagesTest.class,
        PostBackoffideOutagesTest.class,
        PutBackoffideOutagesTest.class,
        DelBackoffideOutagesTest.class,

//***************FASE 02***************

//Feature - Cartão de Crédito
        GetContaPagamentoPosPagoTest.class,
        GetContaPagamentoPosPagoIDTest.class,
        GetLimiteCartaoCreditoRequest.class,
//Feature - Contas
        GetContaTest.class,
        GetSaldoTest.class,
        GetLimiteTest.class,
        GetIdentificacaoContaTest.class
})
public class AllTests {
}
