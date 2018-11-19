package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
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

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VendasController implements Initializable {

    @FXML
    private ComboBox cbProdutos, cbClientes, cbPagamento;

    @FXML
    private TextField txtQtde, txtVUnit, txtVTotal, txtTotalFinal, txtObs;

    @FXML
    private JFXButton btnIncluirItem, btnExcluirItem, btnConfirmar;

    @FXML
    private TableView tabelaProdutos;

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

    //private List<ListProdutoModel> lista = new ArrayList();
    //private ObservableList<ListProdutoModel> ObservableLista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencheCombos();
        //btnIncluirItem.setOnAction(event -> btnIncluirItemClicked());
        //btnExcluirItem.setOnAction(event -> btnExcluirItemClicked());
    }

    /*
    public void btnIncluirItemClicked() {

        ListProdutoModel produtos = new ListProdutoModel();
        List<ListProdutoModel> listProd = new ArrayList<ListProdutoModel>();

        produtos.setQtde(Integer.parseInt(txtQtde.getText()));
        pmArray.forEach((pmObj) -> {
            if (cbProdutos.getValue().equals(pmObj.getNomeProd())) {
                produtos.setPm(pmObj);
            }
        });
        produtos.setvUnitario(Double.parseDouble(txtVUnit.getText()));
        produtos.setvTotal(Double.parseDouble(txtVTotal.getText()));

        listProd.addAll(produtos);

        //ObservableLista = FXCollections.observableArrayList(produtos);

        colunaQtde.setCellValueFactory(new PropertyValueFactory<ListProdutoModel, Integer>("qtde"));
        colunaProduto.setCellValueFactory((param)-> new SimpleStringProperty(param.getValue().getPm().getNomeProd()));
        colunaVUnitario.setCellValueFactory(new PropertyValueFactory<ListProdutoModel, Double>("vUnitario"));
        colunaVTotal.setCellValueFactory(new PropertyValueFactory<ListProdutoModel, Double>("vTotal"));


        tabelaProdutos.setItems(listProd);

    }
    */

    public void cadastrarVenda(ActionEvent e) throws IOException {

        psmArray.forEach((psmObj) -> {
            if (cbClientes.getValue().equals(psmObj.getNome())) {
                vm.setPsm(psmObj);
            }
        });

        vm.setObsVenda(txtObs.getText());
        vm.setPagVenda(cbPagamento.getValue().toString());
        vm.setSubTotal(Double.parseDouble(txtTotalFinal.getText()));
        vd.createVenda(vm);

        limparCampos();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensagem de confirmação: ");
        alert.setContentText("Venda realizada com sucesso!!!");
        alert.showAndWait();
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
    }

}
