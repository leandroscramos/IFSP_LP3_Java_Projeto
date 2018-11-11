package dao;

import connection.ConnectionFactory;
import model.PessoaModel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO {

    Connection con = new ConnectionFactory().getConnection();
    PreparedStatement stmt = null;

    public void createPessoa(PessoaModel psm){

        try {
            stmt = con.prepareStatement("insert into pessoa (cpf, nome, sexo, datanasc, email, celular) values (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, psm.getCpf());
            stmt.setString(2, psm.getNome().toUpperCase());
            stmt.setString(3, psm.getSexo().toUpperCase());
            stmt.setString(4, psm.getData_nasc());
            stmt.setString(5, psm.getEmail().toUpperCase());
            stmt.setString(6, psm.getCelular());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void updatePessoa(PessoaModel psm){

        try {
            stmt = con.prepareStatement("update pessoa set nome=?, sexo=?, datanasc=?, email=?, celular=? where cpf=?");
            stmt.setString(1, psm.getNome().toUpperCase());
            stmt.setString(2, psm.getSexo().toUpperCase());
            stmt.setString(3, psm.getData_nasc());
            stmt.setString(4, psm.getEmail().toUpperCase());
            stmt.setString(5, psm.getCelular());
            stmt.setString(6, psm.getCpf());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void deletePessoa(PessoaModel psm){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("delete from pessoa where cpf=?");
            stmt.setString(1, psm.getCpf());
            stmt.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<PessoaModel> readAllPessoa(){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<PessoaModel> pmArray =  new ArrayList<>();
        PessoaModel psmObjeto;

        try{
            stmt = con.prepareStatement("select * from pessoa");
            rs = stmt.executeQuery();

            while(rs.next()){
                psmObjeto = new PessoaModel();
                psmObjeto.setCpf(rs.getString("cpf"));
                psmObjeto.setNome(rs.getString("nome"));
                psmObjeto.setSexo(rs.getString("sexo"));
                psmObjeto.setData_nasc(rs.getString("datanasc"));
                psmObjeto.setEmail(rs.getString("email"));
                psmObjeto.setCelular(rs.getString("celular"));
                pmArray.add(psmObjeto);
            }

        }catch(SQLException ex){
            System.out.println("Erro ao buscar todos alunos: "+ex.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pmArray;
    }
}
