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
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.realize.suites.Healthcheck.class)
@Suite.SuiteClasses({
        //CANAIS DE ATENDIMENTO
        GetAtendimentoEletronicoTest.class,
        GetAtendimentoTelefonicoTest.class,
        GetCorrespondentesBancariosTest.class,
        GetDependenciasPropriasTest.class,
        //PRODUTOS E SERVIÇOS
        GetCartaoCreditoPessoaNaturalTest.class,
        GetEmprestimoPessoaJuridicaTest.class,
        GetEmprestimoPessoaNaturalTest.class,
        GetContaPessoaNaturalTest.class,
        //API'S COMUNS E INTERNAS
        GetBackofficeOutagesTest.class,
        PostBackofficeOutagesTest.class,
        PutBackofficeOutagesTest.class,
        DelBackofficeOutagesTest.class

})
public class fase01 {
}
