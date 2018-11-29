package dao;

import connection.ConnectionFactory;
import javafx.collections.ObservableList;
import model.ListProdutoModel;
import model.VendasModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendasDAO {


    VendasModel vm = new VendasModel();
    ListProdutoModel lm = new ListProdutoModel();

    public void createVenda(VendasModel vm, ObservableList<ListProdutoModel> listaProdutos) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;

        try {
            stmt1 = con.prepareStatement("insert into vendas (cliente, observacao, pagamento, valorTotal) values (?, ?, ?, ?)");
            stmt1.setString(1, vm.getPsm().getCpf().toString());
            stmt1.setString(2, vm.getObsVenda().toUpperCase());
            stmt1.setString(3, vm.getPagVenda());
            stmt1.setDouble(4, vm.getSubTotal());
            stmt1.executeUpdate();

            stmt2 = con.prepareStatement("select max(codigo) as codigo from vendas");
            ResultSet rs = null;
            Integer codigo = null;
            rs = stmt2.executeQuery();

            if (rs != null && rs.next()) {
                codigo = rs.getInt("codigo");
            }

            for (ListProdutoModel lp : listaProdutos) {
                stmt3 = con.prepareStatement("insert into vendasprodutos (codigovenda, codigoProduto, qtde) values (" + codigo + ", ?, ?)");
                stmt3.setInt(1, lp.getCodigo());
                stmt3.setInt(2, lp.getQtde());
                stmt3.executeUpdate();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt1);
            ConnectionFactory.closeConnection(con, stmt2);
            ConnectionFactory.closeConnection(con, stmt3);
        }
    }

}
