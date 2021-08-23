package br.com.realize.tests.base.massaOrbi.pojoOrbi;

import java.util.List;

public class BodyConta {

    private int commercialOriginId;
    private int income;
    private int mailingAddressId;
    private String personConductorId;
    private int personDockId;
    private int points;
    private String personId;
    private List products;

    public int getCommercialOriginId() {
        return commercialOriginId;
    }

    public void setCommercialOriginId(int commercialOriginId) {
        this.commercialOriginId = commercialOriginId;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(int mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }

    public String getPersonConductorId() {
        return personConductorId;
    }

    public void setPersonConductorId(String personConductorId) {
        this.personConductorId = personConductorId;
    }

    public int getPersonDockId() {
        return personDockId;
    }

    public void setPersonDockId(int personDockId) {
        this.personDockId = personDockId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

}
