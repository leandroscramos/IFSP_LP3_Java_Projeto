package model;

import java.sql.Time;
import java.util.Date;

public class AgendaModel {

    private Integer codAg;
    private Date dataNewAg;
    private Time horaNewAg;
    private String servico;
    private String cliente;

    public AgendaModel(){

    }

    public AgendaModel(Integer codAg, Date dataNewAg, Time horaNewAg, String servico, String cliente) {
        this.codAg = codAg;
        this.dataNewAg = dataNewAg;
        this.horaNewAg = horaNewAg;
        this.servico = servico;
        this.cliente = cliente;
    }

    public Integer getCodAg() {
        return codAg;
    }

    public void setCodAg(Integer codAg) {
        this.codAg = codAg;
    }

    public Date getDataNewAg() {
        return dataNewAg;
    }

    public void setDataNewAg(Date dataNewAg) {
        this.dataNewAg = dataNewAg;
    }

    public Time getHoraNewAg() {
        return horaNewAg;
    }

    public void setHoraNewAg(Time horaNewAg) {
        this.horaNewAg = horaNewAg;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
