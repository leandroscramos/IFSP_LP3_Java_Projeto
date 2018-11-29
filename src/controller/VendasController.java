package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import connection.ConnectionFactory;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import dao.VendasDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ListProdutoModel;
import model.PessoaModel;
import model.ProdutoModel;
import model.VendasModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class VendasController implements Initializable {

    @FXML
    private ComboBox cbProdutos, cbClientes, cbPagamento;

    @FXML
    private TextField txtQtde, txtTotalFinal, txtObs;

    @FXML
    private JFXButton btnIncluirItem, btnExcluirItem, btnConfirmar, btnReportVendasRealizadas;

    @FXML
    private TableView<ListProdutoModel> tabelaProdutos;

    @FXML
    private TableColumn<ListProdutoModel, Integer> colunaQtde;

    @FXML
    private TableColumn<ListProdutoModel, String> colunaProduto;

    @FXML
    private TableColumn<ListProdutoModel, Double> colunaVUnitario, colunaVTotal;

    ProdutoModel pm = new ProdutoModel();
    ProdutoDAO pd = new ProdutoDAO();
    PessoaModel psm = new PessoaModel();
    PessoaDAO psd = new PessoaDAO();
    VendasModel vm = new VendasModel();
    VendasDAO vd = new VendasDAO();
    ArrayList<ProdutoModel> pmArray = new ArrayList<>();
    ArrayList<PessoaModel> psmArray = new ArrayList<>();
    ArrayList<VendasModel> vmArray = new ArrayList<>();
    ArrayList<ListProdutoModel> lpArray = new ArrayList<>();
    Double valorTotal = 0.0;

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencheCombos();
        colunaQtde.setCellValueFactory(new PropertyValueFactory<ListProdutoModel, Integer>("qtde"));
        colunaProduto.setCellValueFactory((param)-> new SimpleStringProperty(param.getValue().getPm().getNomeProd()));
        colunaVUnitario.setCellValueFactory(new PropertyValueFactory<ListProdutoModel, Double>("vUnitario"));
        colunaVTotal.setCellValueFactory(new PropertyValueFactory<ListProdutoModel, Double>("vTotal"));
    }

    public void incluirProdutoLista() {

        ListProdutoModel produtos = new ListProdutoModel();
        produtos.setQtde(Integer.parseInt(txtQtde.getText()));
        pmArray.forEach((pmObj) -> {
            if (cbProdutos.getValue().equals(pmObj.getNomeProd())) {
                produtos.setPm(pmObj);
                produtos.setCodigo(pmObj.getCodProd());
                produtos.setVUnitario(pmObj.getVUnitProd());
                produtos.setVTotal( (pmObj.getVUnitProd()) * (Integer.parseInt(txtQtde.getText())) );
            }
        });
        tabelaProdutos.getItems().add(produtos);
        valorTotal += produtos.getVTotal();
        txtTotalFinal.setText(Double.toString(valorTotal));

        txtQtde.clear();
        cbProdutos.getItems().clear();
        pmArray = pd.readAllProdutos();
        for (ProdutoModel pm: pmArray){
            cbProdutos.getItems().add(pm.getNomeProd());
        }
    }

    public void excluirProdutoLista() {

        ObservableList<ListProdutoModel> allProducts = tabelaProdutos.getItems();
        ObservableList<ListProdutoModel> selectedProducts = tabelaProdutos.getSelectionModel().getSelectedItems();

        for( ListProdutoModel p : selectedProducts ) {
            valorTotal -= p.getVTotal();
            allProducts.remove(p);
        }
        txtTotalFinal.setText(Double.toString(valorTotal));
    }

    public void cadastrarVenda(ActionEvent e) throws IOException, SQLException {

        psmArray.forEach((psmObj) -> {
            if (cbClientes.getValue().equals(psmObj.getNome())) {
                vm.setPsm(psmObj);
            }
        });
        vm.setObsVenda(txtObs.getText());
        vm.setPagVenda(cbPagamento.getValue().toString());
        vm.setSubTotal(Double.parseDouble(txtTotalFinal.getText()));

        ObservableList<ListProdutoModel> listaProdutos = tabelaProdutos.getItems();

        vd.createVenda(vm, listaProdutos);

        limparCampos();
        valorTotal = 0.0;

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText(null);
        alert2.setTitle("Mensagem de confirmação: ");
        alert2.setContentText("Venda realizada com sucesso!!!");
        alert2.showAndWait();

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement("select max(codigo) as codigo from vendas");
        ResultSet rs = null;
        Integer codigoVenda = null;
        rs = stmt.executeQuery();

        if (rs != null && rs.next()) {
            codigoVenda = rs.getInt("codigo");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirma impressão");
        alert.setHeaderText(null);
        alert.setContentText("Confirma impressão do comprovante da venda?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                HashMap filtro = new HashMap();
                filtro.put("codigoVenda", codigoVenda);
                JasperPrint print = JasperFillManager.fillReport("E:\\Dropbox\\[ifsp]\\4º Semetre\\LP3\\Projeto\\Fase 4\\ProjetoSisGS\\src\\reports\\reportVenda.jasper",filtro, con);
                JasperViewer.viewReport(print,false);
            } catch (Exception event) {
                JOptionPane.showMessageDialog(null, event);
            }
        } else {
            alert.close();
        }
        ConnectionFactory.closeConnection(con, stmt);

    }

    public void cancelar() throws Exception {
        Main.sceneChange("sceneHome");
    }

    public void preencheCombos(){

        cbClientes.getItems().clear();
        psmArray = psd.readAllPessoa();
        for (PessoaModel psm: psmArray){
            cbClientes.getItems().add(psm.getNome());
        }

        cbProdutos.getItems().clear();
        pmArray = pd.readAllProdutos();
        for (ProdutoModel pm: pmArray){
            cbProdutos.getItems().add(pm.getNomeProd());
        }

        cbPagamento.getItems().add("DINHEIRO");
        cbPagamento.getItems().add("CARTÃO DE CRÉDITO");
        cbPagamento.getItems().add("CARTÃO DE DÉBITO");

    }

    public void limparCampos(){

        cbClientes.getSelectionModel().clearSelection();
        txtObs.setText("");
        cbPagamento.getSelectionModel().clearSelection();
        txtTotalFinal.setText("");
        tabelaProdutos.getItems().clear();
    }

    public void reportVendasRealizadas() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirma impressão");
        alert.setHeaderText(null);
        alert.setContentText("Confirma impressão do relatório de vendas?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                JasperPrint print = JasperFillManager.fillReport("E:\\Dropbox\\[ifsp]\\4º Semetre\\LP3\\Projeto\\Fase 4\\ProjetoSisGS\\src\\reports\\reportVendasRealizadas.jasper",null, con);
                JasperViewer.viewReport(print,false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            alert.close();
        }
    }

}
