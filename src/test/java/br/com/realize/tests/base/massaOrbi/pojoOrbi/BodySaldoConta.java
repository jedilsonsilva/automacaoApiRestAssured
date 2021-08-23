package br.com.realize.tests.base.massaOrbi.pojoOrbi;

public class BodySaldoConta {

    private int idTipoAjuste;
    private String dataAjuste;
    private int valorAjuste;
    private int identificadorExterno;
    private int idTransacaoOriginal;
    private int idEstabelecimento;
    private boolean flagAtendimento;
    private String mensagemAtendimento;
    private String descricaoEstabelecimentoExterno;
    private int idConta;

    public int getIdTipoAjuste() {
        return idTipoAjuste;
    }

    public void setIdTipoAjuste(int idTipoAjuste) {
        this.idTipoAjuste = idTipoAjuste;
    }

    public String getDataAjuste() {
        return dataAjuste;
    }

    public void setDataAjuste(String dataAjuste) {
        this.dataAjuste = dataAjuste;
    }

    public int getValorAjuste() {
        return valorAjuste;
    }

    public void setValorAjuste(int valorAjuste) {
        this.valorAjuste = valorAjuste;
    }

    public int getIdentificadorExterno() {
        return identificadorExterno;
    }

    public void setIdentificadorExterno(int identificadorExterno) {
        this.identificadorExterno = identificadorExterno;
    }

    public int getIdTransacaoOriginal() {
        return idTransacaoOriginal;
    }

    public void setIdTransacaoOriginal(int idTransacaoOriginal) {
        this.idTransacaoOriginal = idTransacaoOriginal;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public boolean getFlagAtendimento() {
        return flagAtendimento;
    }

    public void setFlagAtendimento(boolean flagAtendimento) {
        this.flagAtendimento = flagAtendimento;
    }

    public String getMensagemAtendimento() {
        return mensagemAtendimento;
    }

    public void setMensagemAtendimento(String mensagemAtendimento) {
        this.mensagemAtendimento = mensagemAtendimento;
    }

    public String getDescricaoEstabelecimentoExterno() {
        return descricaoEstabelecimentoExterno;
    }

    public void setDescricaoEstabelecimentoExterno(String descricaoEstabelecimentoExterno) {
        this.descricaoEstabelecimentoExterno = descricaoEstabelecimentoExterno;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}