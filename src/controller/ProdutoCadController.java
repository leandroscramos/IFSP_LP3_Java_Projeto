package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ProdutoModel;
import dao.ProdutoDAO;
import controller.ProdutoController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdutoCadController implements Initializable{

    @FXML
    private TextField txtCodProd;

    @FXML
    private TextField txtNomeProd;

    @FXML
    private ComboBox txtCategoria;

    @FXML
    private TextField txtEstoque;

    @FXML
    private TextField txtValorProd;

    @FXML
    private TextField txtDescProd;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private Label labelTitulo;

    ProdutoModel pm = new ProdutoModel();
    ProdutoDAO pd = new ProdutoDAO();
    private Stage st;
    String flag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtCategoria.getItems().add("PRODUTO");
        txtCategoria.getItems().add("SERVIÇO");
    }


    //Funcao chamada no ProdutoController
    public void exibirDados(ProdutoModel pm){
        txtCodProd.setText(String.valueOf(pm.getCodProd()));
        txtNomeProd.setText(pm.getNomeProd());
        txtCategoria.setValue(String.valueOf(pm.getCategoria()));
        txtValorProd.setText(String.valueOf(pm.getvUnitProd()));
        txtEstoque.setText(String.valueOf(pm.getEstoque()));
        txtDescProd.setText(pm.getDescProd());
    }

    public void cadastrarProduto(ActionEvent e) throws IOException {

        if(flag.equals("insert")) {

            pm.setNomeProd(txtNomeProd.getText());
            pm.setCategoria(txtCategoria.getSelectionModel().getSelectedItem().toString());
            pm.setvUnitProd(Double.parseDouble(txtValorProd.getText()));
            pm.setEstoque(Integer.parseInt(txtEstoque.getText()));
            pm.setDescProd(txtDescProd.getText());
            pd.createProduto(pm);

            limparCampos();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Mensagem de confirmação: ");
            alert.setContentText("Produto foi inserido com sucesso!!!");
            alert.showAndWait();

            Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
            stageAtual.close();
        }

        if(flag.equals("update")) {
            pm.setCodProd(Integer.parseInt(txtCodProd.getText()));
            pm.setNomeProd(txtNomeProd.getText());
            pm.setCategoria(txtCategoria.getSelectionModel().getSelectedItem().toString());
            pm.setvUnitProd(Double.parseDouble(txtValorProd.getText()));
            pm.setEstoque(Integer.parseInt(txtEstoque.getText()));
            pm.setDescProd(txtDescProd.getText());
            pd.updateProduto(pm);

            limparCampos();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Mensagem de confirmação: ");
            alert.setContentText("Produto foi atualizado com sucesso!!!");
            alert.showAndWait();

            Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
            stageAtual.close();
        }
    }

    public void setFlag(String flag){
        this.flag = flag;
    }

    public void cancelar() {
        Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
        stageAtual.close();
    }

    public void limparCampos(){
        txtNomeProd.setText("");
        txtValorProd.setText("");
        txtEstoque.setText("");
        txtDescProd.setText("");
    }

}
