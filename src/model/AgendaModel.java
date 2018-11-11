package model;

import java.sql.Time;
import java.util.Date;

public class AgendaModel {

    private Integer id;
    private String data;
    private String hora;
    private ProdutoModel pm;
    private PessoaModel psm;

    public AgendaModel(){

    }

    public AgendaModel(Integer id, String data, String hora, ProdutoModel pm, PessoaModel psm) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.pm = pm;
        this.psm = psm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ProdutoModel getPm() {
        return pm;
    }

    public void setPm(ProdutoModel pm) {
        this.pm = pm;
    }

    public PessoaModel getPsm() {
        return psm;
    }

    public void setPsm(PessoaModel psm) {
        this.psm = psm;
    }
}
