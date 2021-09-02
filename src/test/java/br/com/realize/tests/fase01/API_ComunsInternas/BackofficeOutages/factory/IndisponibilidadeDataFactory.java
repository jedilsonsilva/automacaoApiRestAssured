package br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.factory;

import br.com.realize.tests.fase01.API_ComunsInternas.BackofficeOutages.pojo.Indisponibilidade;
import br.com.realize.utils.DataUtils;
import com.github.javafaker.Faker;

import java.util.Locale;

public class IndisponibilidadeDataFactory {

    static Faker fake = new Faker(new Locale("pt-br"));
    static int qtdDias = fake.number().numberBetween(01, 100);
    static int qtdHoras = fake.number().numberBetween(01, 23);
    static String explanation = fake.backToTheFuture().character();
    static String unavailableEndpoints = fake.expression(fake.toString());
    static String unavailableEndpointsContract = fake.expression(fake.ancient().toString());
    static String unavailableEndpointsMenosUm = fake.expression(fake.aviation().airport());
    static String unavailableEndpointsIsPartialTrue = fake.expression(fake.ancient().toString());


    public static Indisponibilidade dadosIndisponibilidade(){

        Indisponibilidade indisponibilidade = new Indisponibilidade();
        indisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        indisponibilidade.setExplanation(explanation);
        indisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(1));
        indisponibilidade.setPartial("true");
        indisponibilidade.setUnavailableEndpoints(unavailableEndpoints);
        indisponibilidade.setIsPartial("false");
        return indisponibilidade;
    }
    public static Indisponibilidade dadosIndisponibilidadeContrato(){

        Indisponibilidade indisponibilidade = new Indisponibilidade();
        indisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        indisponibilidade.setExplanation(explanation);
        indisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(1));
        indisponibilidade.setPartial("true");
        indisponibilidade.setUnavailableEndpoints(unavailableEndpointsContract);
        indisponibilidade.setIsPartial("false");
        return indisponibilidade;
    }
    public static Indisponibilidade dadosIndisponibilidadeinserirOutageInferiorDataHoraAtual(){

        Indisponibilidade indisponibilidade = new Indisponibilidade();
        indisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        indisponibilidade.setExplanation(explanation);
        indisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(-1));
        indisponibilidade.setPartial("true");
        indisponibilidade.setUnavailableEndpoints(unavailableEndpointsMenosUm);
        indisponibilidade.setIsPartial("false");
        return indisponibilidade;

    }
    public static Indisponibilidade dadosIndisponibilidadeInserirIsPartialTrue(){

        Indisponibilidade indisponibilidade = new Indisponibilidade();
        indisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        indisponibilidade.setExplanation(explanation);
        indisponibilidade.setOutageTime(DataUtils.getDataIndisponibilidade(1));
        indisponibilidade.setPartial("true");
        indisponibilidade.setUnavailableEndpoints("");
        indisponibilidade.setIsPartial("true");
        return indisponibilidade;
    }
    public static Indisponibilidade dadosIndisponibilidadeInserirCampoObrigatorioNaoPreenchido(){

        Indisponibilidade indisponibilidade = new Indisponibilidade();
        indisponibilidade.setDuration("P"+qtdDias+"DT"+qtdHoras+"H");
        indisponibilidade.setExplanation(explanation);
        indisponibilidade.setOutageTime("2021-06-05T06:10:40Z");
        indisponibilidade.setPartial("true");
        indisponibilidade.setUnavailableEndpoints("");
        indisponibilidade.setIsPartial("true");
        return indisponibilidade;
    }
}
