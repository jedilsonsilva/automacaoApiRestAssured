package br.com.realize.tests.base.massaOrbi.pojoOrbi;


public class BodyCartao {



//BODY DA API DE CRIAÇÃO DE CARTÃO

    private String blocked;
    private int externalAccountId;
    private String validationDate;

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public int getExternalAccountId() {
        return externalAccountId;
    }

    public void setExternalAccountId(int externalAccountId) {
        this.externalAccountId = externalAccountId;
    }

    public String getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(String validationDate) {
        this.validationDate = validationDate;
    }

}



