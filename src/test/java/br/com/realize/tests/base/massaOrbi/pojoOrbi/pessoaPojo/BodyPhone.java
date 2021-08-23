package br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo;

public class BodyPhone {

    private String areaCode;
    private String number;
    private String type;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "bodyPhone{" +
                "areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
