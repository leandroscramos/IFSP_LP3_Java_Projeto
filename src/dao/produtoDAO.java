package dao;

import connection.ConnectionFactory;
import model.produtoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
