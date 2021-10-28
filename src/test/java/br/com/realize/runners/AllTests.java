package br.com.realize.runners;

import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests.DelBackofficeOutagesTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests.GetBackofficeOutagesTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests.PostBackofficeOutagesTest;
import br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.tests.PutBackofficeOutagesTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoEletronico.tests.GetAtendimentoEletronicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.AtendimentoTelefonico.tests.GetAtendimentoTelefonicoTest;
import br.com.realize.tests.fase01.CanaisAtendimento.CorrespondentesBancarios.tests.GetCorrespondentesBancariosTest;
import br.com.realize.tests.fase01.CanaisAtendimento.DependenciasProprias.tests.GetDependenciasPropriasTest;
import br.com.realize.tests.fase01.ProdutosServicos.CartaoCreditoPessoaNatural.tests.GetCartaoCreditoPessoaNaturalTest;
import br.com.realize.tests.fase01.ProdutosServicos.ContaPessoaNatural.tests.GetContaPessoaNaturalTest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaJuridica.tests.GetEmprestimoPessoaJuridicaTest;
import br.com.realize.tests.fase01.ProdutosServicos.EmprestimoPessoaNatural.tests.GetEmprestimoPessoaNaturalTest;
import br.com.realize.tests.fase02.CartaoCredito.ApiPrivateConta.tests.GetPrivateContaCartaoCompraCreditoTest;
import br.com.realize.tests.fase02.CartaoCredito.ContasPagamentoPosPago.tests.GetContaPagamentoPosPagoTest;
import br.com.realize.tests.fase02.CartaoCredito.ContaIdentificadaPorCreditCardAccountId.tests.GetContaIdentificadaPorCreditCardAccountIdTest;
import br.com.realize.tests.fase02.CartaoCredito.LimitesCreditoContaPosPago.requests.GetLimiteCartaoCreditoRequest;
import br.com.realize.tests.fase02.Contas.IdentificacaoConta.tests.GetIdentificacaoContaTest;
import br.com.realize.tests.fase02.Contas.Limites.tests.GetLimiteTest;
import br.com.realize.tests.fase02.Contas.ListaContas.tests.GetContaTest;
import br.com.realize.tests.fase02.Contas.Saldos.tests.GetSaldoTest;
import br.com.realize.tests.fase02.DadosCadastrais.IdentificacaoPessoaNatural.tests.GetIdentificacaoPessoaNaturalTest;
import br.com.realize.tests.fase02.DadosCadastrais.QualificacaoPessoaNatural.tests.GetQualificacaoPessoaNaturalTest;
import br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.tests.*;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.tests.GetPixPagamentoTest;
import br.com.realize.tests.fase03.pagamentos.pixPagamento.tests.PostPixPagamentoTest;
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
        GetBackofficeOutagesTest.class,
        PostBackofficeOutagesTest.class,
        PutBackofficeOutagesTest.class,
        DelBackofficeOutagesTest.class,

//***************FASE 02***************

//Feature - Cartão de Crédito
        GetContaPagamentoPosPagoTest.class,
        GetContaIdentificadaPorCreditCardAccountIdTest.class,
        GetLimiteCartaoCreditoRequest.class,
        GetPrivateContaCartaoCompraCreditoTest.class,
//Feature - Contas
        GetContaTest.class,
        GetSaldoTest.class,
        GetLimiteTest.class,
        GetIdentificacaoContaTest.class,
//Feature - Dados Cadastrais
        GetIdentificacaoPessoaNaturalTest.class,
        GetQualificacaoPessoaNaturalTest.class,

//***************FASE 03***************

//Feature - Consentimento Pagamento Pix
        GetConsentimentoPagamentoTest.class,
        PostAtualizacaoContaDebitoPagamentoPixTest.class,
        PostAutorizarConsentimentoPagamentoPixTest.class,
        PostConsentimentoPagamentoTest.class,
        PostRejeitarConsentimentoPagamentoTest.class,
//Feature - Pagamento Pix
        GetPixPagamentoTest.class,
        PostPixPagamentoTest.class
})
public class AllTests {
}
