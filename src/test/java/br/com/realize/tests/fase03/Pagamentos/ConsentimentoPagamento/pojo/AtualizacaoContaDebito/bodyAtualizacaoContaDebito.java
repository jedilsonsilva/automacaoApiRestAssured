package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.AtualizacaoContaDebito;

public class bodyAtualizacaoContaDebito {

    private String ispb;
    private String issuer;
    private String number;
    private String type;

    public String getIspb() {
        return ispb;
    }

    public void setIspb(String ispb) {
        this.ispb = ispb;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
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
}
