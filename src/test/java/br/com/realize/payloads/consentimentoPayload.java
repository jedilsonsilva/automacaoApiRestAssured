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

        BodyData bodyData = new BodyData();
        BodyConsentimento bodyConsentimento = new BodyConsentimento();
        BodyDocumentCpf bodyDocumentCpf = new BodyDocumentCpf();
        BodyDocumentCnpj bodyDocumentCnpj = new BodyDocumentCnpj();
        BodyBusinessEntity bodyBusinessEntity = new BodyBusinessEntity();
        BodyLoggedUser bodyLoggedUser = new BodyLoggedUser();

        bodyConsentimento.setData(bodyData);
        bodyData.setBusinessEntity(bodyBusinessEntity);
        bodyBusinessEntity.setDocument(bodyDocumentCnpj);
        bodyDocumentCnpj.setIdentification(geradorCpfCnpjRG.geraCNPJ());
        bodyDocumentCnpj.setRel("CNPJ");
        bodyData.setExpirationDateTime(expirationDateTime);
        bodyData.setLoggedUser(bodyLoggedUser);
        bodyLoggedUser.setDocument(bodyDocumentCpf);
        bodyDocumentCpf.setIdentification(geradorCpfCnpjRG.geraCPF());
        bodyDocumentCpf.setRel("CPF");
        bodyData.setPermissions(Collections.singletonList("ACCOUNTS_READ"));
        bodyData.setTransactionFromDateTime(transactionFromDateTime);
        bodyData.setTransactionToDateTime(transactionToDateTime);

        return jsonObject;

}}
