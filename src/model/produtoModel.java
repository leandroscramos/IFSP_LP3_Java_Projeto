package model;

public class produtoModel {

    private Integer codProd;
    private String nomeProd;
    private Double vUnitProd;
    private String descProd;

    public produtoModel(){

    }

    public produtoModel(Integer codProd, String nomeProd, Double vUnitProd, String descProd) {
        this.codProd = codProd;
        this.nomeProd = nomeProd;
        this.vUnitProd = vUnitProd;
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

    public Double getvUnitProd() {
        return vUnitProd;
    }

    public void setvUnitProd(Double vUnitProd) {
        this.vUnitProd = vUnitProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }
}
