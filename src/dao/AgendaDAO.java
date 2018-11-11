package dao;

import connection.ConnectionFactory;
import model.AgendaModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendaDAO {

    Connection con = new ConnectionFactory().getConnection();
    PreparedStatement stmt = null;

    public void createAgenda (AgendaModel am) {
        try {
            stmt = con.prepareStatement("insert into agenda (data, hora, servico, cliente) values (?, ?, ?, ?)");
            stmt.setString(1, am.getData());
            stmt.setString(2, am.getHora());
            stmt.setInt(3, am.getPm().getCodProd());
            stmt.setString(4, am.getPsm().getCpf());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
