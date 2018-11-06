package dao;

import connection.ConnectionFactory;
import model.produtoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class produtoDAO {


    public void createProduto(produtoModel pm){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into produto (nome, valor, descricao) values (?, ?, ?)");
            stmt.setString(1, pm.getNomeProd());
            stmt.setDouble(2, pm.getvUnitProd());
            stmt.setString(3, pm.getDescProd());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<produtoModel> readAllProdutos(){
        ArrayList<produtoModel> pmArray =  new ArrayList<>();
        produtoModel pmObjeto;

        Connection con = ConnectionFactory.getConnection();
        ConnectionFactory.executeSql("select codigo, nome, valor, descricao from produto");
        try{

            while(con.rs.next()){
                pmObjeto = new produtoModel();
                pmObjeto.setCodProd(con.rs.getInt con.rs.getInt("id"));
                pmObjeto.setNome(cntn.rs.getString("nome"));
                pmObjeto.setCelular(cntn.rs.getString("celular"));
                pmObjeto.setEmail(cntn.rs.getString("email"));
                amArray.add(amObjeto);
            }
            return amArray;
        }catch(SQLException ex){
            System.out.println("Erro ao buscar todos alunos: "+ex.getMessage());
            return null;
        }
    }
}
