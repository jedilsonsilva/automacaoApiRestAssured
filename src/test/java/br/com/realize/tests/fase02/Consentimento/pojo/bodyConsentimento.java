package br.com.realize.tests.fase02.Consentimento.pojo;

import java.util.List;

public class bodyConsentimento {

    private bodyBusinessEntity businessEntity;
    private String expirationDateTime;
    private bodyLoggedUser loggedUser;
    private List<String> permissions;
    private String transactionFromDateTime;
    private String transactionToDateTime;

    public bodyBusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(bodyBusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public bodyLoggedUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(bodyLoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getTransactionFromDateTime() {
        return transactionFromDateTime;
    }

    public void setTransactionFromDateTime(String transactionFromDateTime) {
        this.transactionFromDateTime = transactionFromDateTime;
    }

    public String getTransactionToDateTime() {
        return transactionToDateTime;
    }

    public void setTransactionToDateTime(String transactionToDateTime) {
        this.transactionToDateTime = transactionToDateTime;
    }
}
