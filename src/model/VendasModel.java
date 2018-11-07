package model;

import java.util.List;

public class VendasModel {
    private Integer codVenda;
    private String cliente;
    private Integer qtde;
    private String produto;
    private Double vUnitProd;
    private Double vTotalProd;
    private List listaProd;
    private String obsVenda;
    private String pagVenda;
    private Double subTotal;

    public VendasModel(){

    }

    public VendasModel(Integer codVenda, String cliente, Integer qtde, String produto, Double vUnitProd, Double vTotalProd, List listaProd, String obsVenda, String pagVenda, Double subTotal) {
        this.codVenda = codVenda;
        this.cliente = cliente;
        this.qtde = qtde;
        this.produto = produto;
        this.vUnitProd = vUnitProd;
        this.vTotalProd = vTotalProd;
        this.listaProd = listaProd;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getvUnitProd() {
        return vUnitProd;
    }

    public void setvUnitProd(Double vUnitProd) {
        this.vUnitProd = vUnitProd;
    }

    public Double getvTotalProd() {
        return vTotalProd;
    }

    public void setvTotalProd(Double vTotalProd) {
        this.vTotalProd = vTotalProd;
    }

    public List getListaProd() {
        return listaProd;
    }

    public void setListaProd(List listaProd) {
        this.listaProd = listaProd;
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
