package br.com.realize.tests.base.massaOrbi.pojoOrbi.pessoaPojo;

public class BodyDeviceIdentification {

    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public BodyDeviceIdentification setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    @Override
    public String toString() {
        return "bodyDeviceIdentification{" +
                "fingerprint='" + fingerprint + '\'' +
                '}';
    }
}
