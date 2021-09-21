package br.com.realize.tests.fase03.pagamentos.consentimentoPagamentoPix.pojo.ConsentimentoPagamento;

import java.util.List;

public class BodyDataPagamento {

    private BodyLoggedUserPagamento loggedUser;
    private BodyBusinessEntityPagamento businessEntity;
    private BodyCreditor creditor;
    private BodyPayment payment;
    private BodyDebtorAccount debtorAccount;
    private String expirationDateTime;

    private List<String> permissions;
    private String transactionFromDateTime;
    private String transactionToDateTime;

    public BodyLoggedUserPagamento getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(BodyLoggedUserPagamento loggedUser) {
        this.loggedUser = loggedUser;
    }

    public BodyBusinessEntityPagamento getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(BodyBusinessEntityPagamento businessEntity) {
        this.businessEntity = businessEntity;
    }

    public BodyCreditor getCreditor() {
        return creditor;
    }

    public void setCreditor(BodyCreditor creditor) {
        this.creditor = creditor;
    }

    public BodyPayment getPayment() {
        return payment;
    }

    public void setPayment(BodyPayment payment) {
        this.payment = payment;
    }

    public BodyDebtorAccount getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(BodyDebtorAccount debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

}
