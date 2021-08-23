package br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo;

public class BodyDadosPessoa {

    private BodyAddress address;
    private String birthDate;
    private String cpfCnpj;
    private BodyDeviceIdentification deviceIdentification;
    private String email;
    private String fatherName;
    private int incomeValue;
    private String motherName;
    private String name;
    private BodyPhone phone;

    public BodyAddress getAddress() {
        return address;
    }

    public void setAddress(BodyAddress address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public BodyDeviceIdentification getDeviceIdentification() {
        return deviceIdentification;
    }

    public void setDeviceIdentification(BodyDeviceIdentification deviceIdentification) {
        this.deviceIdentification = deviceIdentification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public int getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(int incomeValue) {
        this.incomeValue = incomeValue;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BodyPhone getPhone() {
        return phone;
    }

    public void setPhone(BodyPhone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "bodyDadosPessoa{" +
                "address=" + address +
                ", birthDate='" + birthDate + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", deviceIdentification=" + deviceIdentification +
                ", email='" + email + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", incomeValue=" + incomeValue +
                ", motherName='" + motherName + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
