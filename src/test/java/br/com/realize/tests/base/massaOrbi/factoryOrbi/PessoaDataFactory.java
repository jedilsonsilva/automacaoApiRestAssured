package br.com.realize.tests.base.massaOrbi.factoryOrbi;

import br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo.*;
import br.com.realize.utils.geradorCpfCnpjRG;
import com.github.javafaker.Faker;
import java.util.Locale;

public class PessoaDataFactory {

    public static BodyPessoa dadosPessoa(){

        Faker fake = new Faker(new Locale("pt-br"));
        String cidade = fake.address().cityName();
        long numberCasa = fake.number().randomNumber();
        String uf = fake.options().option("RO", "AC", "AM", "RR", "PA", "AP", "TO", "MA", "PI", "CE", "RN", "PB", "PE", "AL", "SE", "BA", "MG", "ES", "RJ", "SP", "PR", "SC", "RS", "MS", "MT", "GO", "DF");
        String endereco = String.valueOf(fake.address());
        String rua = fake.address().streetAddress();
        String email = fake.internet().emailAddress();
        String nomePai = fake.name().fullName();
        String nomeMae = fake.name().fullName();
        String nome = fake.name().fullName();

        BodyPessoa bodyPessoa = new BodyPessoa();
        BodyDadosPessoa bodyDadosPessoa = new BodyDadosPessoa();
        BodyAddress bodyAddress = new BodyAddress();
        BodyDeviceIdentification bodyDeviceIdentification = new BodyDeviceIdentification();
        BodyPhone bodyPhone = new BodyPhone();

        bodyPessoa.setPerson(bodyDadosPessoa);
        bodyDadosPessoa.setAddress(bodyAddress);
        bodyAddress.setCity(cidade);
        bodyAddress.setComplement("");
        bodyAddress.setCountry("Brasil");
        bodyAddress.setFederativeUnit(uf);
        bodyAddress.setNeighborhood(endereco);
        bodyAddress.setNumber((int) numberCasa);
        bodyAddress.setReferencePoint("Esquina");
        bodyAddress.setStreet(rua);
        bodyAddress.setType("COMPANY");
        bodyAddress.setZipCode("92110245");
        bodyDadosPessoa.setBirthDate("1994-03-17");
        bodyDadosPessoa.setCpfCnpj(geradorCpfCnpjRG.geraCPF());
        bodyDadosPessoa.setDeviceIdentification(bodyDeviceIdentification);
        bodyDeviceIdentification.setFingerprint("Teste");
        bodyDadosPessoa.setEmail(email);
        bodyDadosPessoa.setFatherName(nomePai);
        bodyDadosPessoa.setIncomeValue(0);
        bodyDadosPessoa.setMotherName(nomeMae);
        bodyDadosPessoa.setName(nome);
        bodyDadosPessoa.setPhone(bodyPhone);
        bodyPhone.setAreaCode("054");
        bodyPhone.setNumber("996140592");
        bodyPhone.setType("CELLPHONE");
        bodyPessoa.setProducts("BANK_ACCOUNT");
        return bodyPessoa;
    }
}
