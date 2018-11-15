package controller;

import application.Main;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.PessoaModel;
import model.ProdutoModel;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VendasController implements Initializable {

    @FXML
    private ComboBox cbProdutos, cbClientes, cbPagamento;

    @FXML
    private TextField txtVUnit, txtVTotal, txtTotalFinal;


    ProdutoModel pm = new ProdutoModel();
    ProdutoDAO pd = new ProdutoDAO();
    PessoaModel psm = new PessoaModel();
    PessoaDAO psd = new PessoaDAO();
    ArrayList<ProdutoModel> pmArray = new ArrayList<>();
    ArrayList<PessoaModel> psmArray = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheCombos();
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

}
