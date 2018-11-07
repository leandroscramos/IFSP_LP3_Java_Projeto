package dao;

import connection.ConnectionFactory;
import model.ProdutoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {


    public void createProduto(ProdutoModel pm){
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
    /*
    public List<ProdutoModel> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutoModel> produtos = new ArrayList<>();

        try {

            stmt = con.prepareStatement("select * from produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setCodProd(rs.getInt("codigo"));
                produto.setNomeProd(rs.getString("nome"));
                produto.setvUnitProd(rs.getDouble("valor"));
                produto.setDescProd(rs.getString("descricao"));
                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    */

    public ArrayList<ProdutoModel> readAllProdutos(){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ProdutoModel> pmArray =  new ArrayList<>();
        ProdutoModel pmObjeto;

        try{

            stmt = con.prepareStatement("select codigo, nome, valor, descricao from produto");
            rs = stmt.executeQuery();

            while(rs.next()){
                pmObjeto = new ProdutoModel();
                pmObjeto.setCodProd(rs.getInt("codigo"));
                pmObjeto.setNomeProd(rs.getString("nome"));
                pmObjeto.setvUnitProd(rs.getDouble("valor"));
                pmObjeto.setDescProd(rs.getString("descricao"));
                pmArray.add(pmObjeto);
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
