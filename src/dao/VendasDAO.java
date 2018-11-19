package dao;

import connection.ConnectionFactory;
import model.VendasModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendasDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    VendasModel vm = new VendasModel();

    public void createVenda(VendasModel vm){

        try {
            stmt = con.prepareStatement("insert into vendas (cliente, observacao, pagamento, valorTotal) values (?, ?, ?, ?)");
            stmt.setString(1, vm.getPsm().getCpf().toString());
            stmt.setString(2, vm.getObsVenda().toUpperCase());
            stmt.setString(3, vm.getPagVenda());
            stmt.setDouble(4, vm.getSubTotal());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
