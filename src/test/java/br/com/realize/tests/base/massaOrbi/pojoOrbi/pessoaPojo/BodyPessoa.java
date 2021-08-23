package br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo;

import br.com.realize.tests.base.massaOrbi.factoryOrbi.PessoaDataFactory;

import java.util.Collections;
import java.util.List;

public class BodyPessoa extends PessoaDataFactory {

    private BodyDadosPessoa person;
    private List products;

    public BodyDadosPessoa getPerson() {
        return person;
    }

    public void setPerson(BodyDadosPessoa person) {
        this.person = person;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = Collections.singletonList(products);
    }

    @Override
    public String toString() {
        return "bodyPessoa{" +
                "person=" + person +
                ", products=" + products +
                '}';
    }
}
