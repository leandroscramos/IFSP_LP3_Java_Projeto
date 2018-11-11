package model;

import javafx.beans.property.DoubleProperty;

public class ProdutoModel {

    private Integer codProd;
    private String nomeProd;
    private String categoria;
    private Integer estoque;
    private Double vUnitProd;
    private String descProd;

    public ProdutoModel(){

    }

    public ProdutoModel(Integer codProd, String nomeProd, String categoria, Double vUnitProd, Integer estoque, String descProd) {
        this.codProd = codProd;
        this.nomeProd = nomeProd;
        this.categoria = categoria;
        this.vUnitProd = vUnitProd;
        this.estoque = estoque;
        this.descProd = descProd;
    }

    public Integer getCodProd() {
        return codProd;
    }

    public void setCodProd(Integer codProd) {
        this.codProd = codProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public Double getvUnitProd() { return vUnitProd; }

    public void setvUnitProd(Double vUnitProd) {
        this.vUnitProd = vUnitProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
