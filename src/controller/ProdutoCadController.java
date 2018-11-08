package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ProdutoModel;
import dao.ProdutoDAO;
import controller.ProdutoController;

import java.io.IOException;
import java.util.ArrayList;

public class ProdutoCadController {

    @FXML
    private TextField txtCodProd;

    @FXML
    private TextField txtNomeProd;

    @FXML
    private TextField txtValorProd;

    @FXML
    private TextField txtDescProd;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnCancelar;

    private Stage st;

    public void inserirProduto(ActionEvent e) throws IOException {
        ProdutoModel pm = new ProdutoModel();
        ProdutoDAO pd = new ProdutoDAO();
        ArrayList<ProdutoModel> pmArray =  new ArrayList<>();

        pm.setNomeProd(txtNomeProd.getText());
        pm.setvUnitProd(Double.parseDouble(txtValorProd.getText()));
        pm.setDescProd(txtDescProd.getText());
        pd.createProduto(pm);

        limparCampos();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensagem de confirmação: ");
        alert.setContentText("Aluno foi inserido com sucesso!!!");
        alert.showAndWait();

        Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
        stageAtual.close();
    }

    public void cancelar() {
        Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
        stageAtual.close();
    }

    public void limparCampos(){
        txtNomeProd.setText("");
        txtValorProd.setText("");
        txtDescProd.setText("");
    }

}
