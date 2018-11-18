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

    public void createAgenda (AgendaModel am) {
        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

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

    public void updateAgenda (AgendaModel am) {
        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;

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

    public void deleteAgenda (AgendaModel am){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("delete from agenda where id=?");
            stmt.setInt(1, am.getId());
            stmt.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<AgendaModel> selectAllAgendas(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<AgendaModel> amArray = new ArrayList<>();
        AgendaModel amObjeto;
        ProdutoModel pm = new ProdutoModel();
        ProdutoDAO pd = new ProdutoDAO();
        PessoaModel psm = new PessoaModel();
        PessoaDAO psd = new PessoaDAO();

        ArrayList<ProdutoModel> pmArray = new ArrayList<>();
        ArrayList<PessoaModel> psmArray = new ArrayList<>();

        try{
            stmt = con.prepareStatement("select a.id, a.data, a.hora, pe.nome, pr.nome \n" +
                    "from agenda a\n" +
                    "join pessoa pe\n" +
                    "on a.cliente = pe.cpf\n" +
                    "join produto pr \n" +
                    "on a.servico = pr.codigo\n" +
                    "order by a.data");
            rs = stmt.executeQuery();

            while(rs.next()){
                amObjeto = new AgendaModel();
                pm = new ProdutoModel();
                psm = new PessoaModel();

                amObjeto.setId(rs.getInt("id"));
                amObjeto.setData(rs.getString("data"));
                amObjeto.setHora(rs.getString("hora"));

                psm.setNome(rs.getString("pe.nome"));
                amObjeto.setPsm(psm);

                pm.setNomeProd(rs.getString("pr.nome"));
                amObjeto.setPm(pm);

                amArray.add(amObjeto);
            }

        }catch(SQLException ex){
            System.out.println("Erro ao buscar a agenda: "+ex.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return amArray;
        }

    }

}
