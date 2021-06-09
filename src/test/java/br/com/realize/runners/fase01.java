package br.com.realize.runners;

import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.requests.GetBackoffideOutagesRequest;
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
        GetBackoffideOutagesTest.class,
        PostBackoffideOutagesTest.class,
        PutBackoffideOutagesTest.class,
        DelBackoffideOutagesTest.class

})
public class fase01 {
}
