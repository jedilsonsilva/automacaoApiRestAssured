package br.com.realize.tests.fase03.Pagamentos.ConsentimentoPagamento.pojo.ConsentimentoPagamento;

import java.util.List;

public class bodyDataPagamento {

    private bodyLoggedUserPagamento loggedUser;
    private bodyBusinessEntityPagamento businessEntity;
    private bodyCreditor creditor;
    private bodyPayment payment;
    private bodyDebtorAccount debtorAccount;
    private String expirationDateTime;

    private List<String> permissions;
    private String transactionFromDateTime;
    private String transactionToDateTime;

    public bodyLoggedUserPagamento getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(bodyLoggedUserPagamento loggedUser) {
        this.loggedUser = loggedUser;
    }

    public bodyBusinessEntityPagamento getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(bodyBusinessEntityPagamento businessEntity) {
        this.businessEntity = businessEntity;
    }

    public bodyCreditor getCreditor() {
        return creditor;
    }

    public void setCreditor(bodyCreditor creditor) {
        this.creditor = creditor;
    }

    public bodyPayment getPayment() {
        return payment;
    }

    public void setPayment(bodyPayment payment) {
        this.payment = payment;
    }

    public bodyDebtorAccount getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(bodyDebtorAccount debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

}
