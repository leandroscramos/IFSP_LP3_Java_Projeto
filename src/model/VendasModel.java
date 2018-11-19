package model;

import java.util.List;

public class VendasModel {

    private Integer codVenda;
    private PessoaModel psm;
    private String obsVenda;
    private String pagVenda;
    private Double subTotal;

    public VendasModel(){

    }

    public VendasModel(Integer codVenda, PessoaModel psm, String obsVenda, String pagVenda, Double subTotal) {
        this.codVenda = codVenda;
        this.psm = psm;
        this.obsVenda = obsVenda;
        this.pagVenda = pagVenda;
        this.subTotal = subTotal;
    }

    public Integer getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Integer codVenda) {
        this.codVenda = codVenda;
    }

    public PessoaModel getPsm() {
        return psm;
    }

    public void setPsm(PessoaModel psm) {
        this.psm = psm;
    }

    public String getObsVenda() {
        return obsVenda;
    }

    public void setObsVenda(String obsVenda) {
        this.obsVenda = obsVenda;
    }

    public String getPagVenda() {
        return pagVenda;
    }

    public void setPagVenda(String pagVenda) {
        this.pagVenda = pagVenda;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
