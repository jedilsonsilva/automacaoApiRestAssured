package br.com.realize.tests.fase02.Consentimento.pojo;

import java.util.List;

public class BodyData {

    private BodyBusinessEntity businessEntity;
    private String expirationDateTime;
    private BodyLoggedUser loggedUser;
    private List permissions;
    private String transactionFromDateTime;
    private String transactionToDateTime;

    public BodyBusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(BodyBusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public BodyLoggedUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(BodyLoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public List getPermissions() {
        return permissions;
    }

    public void setPermissions(List permissions) {
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
