package dao;

import connection.ConnectionFactory;
import model.AgendaModel;
import model.PessoaModel;
import model.ProdutoModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<AgendaModel> selectAllAgendas(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<AgendaModel> amArray = new ArrayList<>();
        AgendaModel amObjeto;
        ProdutoModel pm;
        PessoaModel psm;

        try{
            stmt = con.prepareStatement("select * from agenda");
            rs = stmt.executeQuery();

            while(rs.next()){
                amObjeto = new AgendaModel();
                pm = new ProdutoModel();
                psm = new PessoaModel();
                amObjeto.setData("data");
                amObjeto.setHora("hora");

                pm.setNomeProd(rs.getString("produto"));
                amObjeto.setPm(pm);

                psm.setNome(rs.getString("cliente"));
                amObjeto.setPsm(psm);

                amArray.add(amObjeto);
            }

        }catch(SQLException ex){
            System.out.println("Erro ao buscar a agenda: "+ex.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return amArray;
    }

}
