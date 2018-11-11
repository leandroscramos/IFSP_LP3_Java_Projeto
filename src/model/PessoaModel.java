package model;

import java.util.Date;

public class PessoaModel {
    private String cpf;
    private String nome;
    private String sexo;
    private String data_nasc;
    private String email;
    private String celular;

    public PessoaModel(){

    }

    public PessoaModel(String cpf, String nome, String sexo, String data_nasc, String email, String celular) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
        this.email = email;
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getData_nasc() { return data_nasc; }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
