package br.com.realize.tests.fase03.Pagamentos.PixPagamento.pojo;

public class BodyData {

    private BodyCreditorAccount creditorAccount;
    private String localInstrument;
    private BodyPayment payment;
    private String proxy;
    private String qrCode;
    private String remittanceInformation;

    public BodyCreditorAccount getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(BodyCreditorAccount creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public String getLocalInstrument() {
        return localInstrument;
    }

    public void setLocalInstrument(String localInstrument) {
        this.localInstrument = localInstrument;
    }

    public BodyPayment getPayment() {
        return payment;
    }

    public void setPayment(BodyPayment payment) {
        this.payment = payment;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getRemittanceInformation() {
        return remittanceInformation;
    }

    public void setRemittanceInformation(String remittanceInformation) {
        this.remittanceInformation = remittanceInformation;
    }
}
