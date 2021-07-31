package br.com.realize.payloads;

import br.com.realize.tests.fase02.Consentimento.pojo.*;
import br.com.realize.utils.geradorCpfCnpjRG;
import org.json.simple.JSONObject;

import java.util.Collections;

public class consentimentoPayload {

    String expirationDateTime = "2021-05-21T08:30:00Z";
    String transactionFromDateTime = "2021-01-01T00:00:00Z";
    String transactionToDateTime = "2021-02-01T23:59:59Z";


    public JSONObject consetimento() throws Exception {
        JSONObject jsonObject = new JSONObject();

    br.com.realize.tests.fase02.Consentimento.pojo.bodyConsentimento bodyConsentimento = new bodyConsentimento();
    br.com.realize.tests.fase02.Consentimento.pojo.bodyDocumentCpf bodyDocumentCpf = new bodyDocumentCpf();
    br.com.realize.tests.fase02.Consentimento.pojo.bodyDocumentCnpj bodyDocumentCnpj = new bodyDocumentCnpj();
    br.com.realize.tests.fase02.Consentimento.pojo.bodyBusinessEntity bodyBusinessEntity = new bodyBusinessEntity();
    br.com.realize.tests.fase02.Consentimento.pojo.bodyLoggedUser bodyLoggedUser = new bodyLoggedUser();

        bodyConsentimento.setBusinessEntity(bodyBusinessEntity);
            bodyBusinessEntity.setDocument(bodyDocumentCnpj);
                bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
                bodyDocumentCnpj.setRel("CNPJ");
        bodyConsentimento.setExpirationDateTime(expirationDateTime);
        bodyConsentimento.setLoggedUser(bodyLoggedUser);
            bodyLoggedUser.setDocument(bodyDocumentCpf);
                bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
                bodyDocumentCpf.setRel("CPF");
        bodyConsentimento.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyConsentimento.setTransactionFromDateTime(transactionFromDateTime);
        bodyConsentimento.setTransactionToDateTime(transactionToDateTime);

        return jsonObject;

}}
