package br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo;

public class BodyAddress {

    private String city;
    private String complement;
    private String country;
    private String federativeUnit;
    private String neighborhood;
    private int number;
    private String referencePoint;
    private String street;
    private String type;
    private String zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFederativeUnit() {
        return federativeUnit;
    }

    public void setFederativeUnit(String federativeUnit) {
        this.federativeUnit = federativeUnit;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "bodyAddress{" +
                "city='" + city + '\'' +
                ", complement='" + complement + '\'' +
                ", country='" + country + '\'' +
                ", federativeUnit='" + federativeUnit + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", number=" + number +
                ", referencePoint='" + referencePoint + '\'' +
                ", street='" + street + '\'' +
                ", type='" + type + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
