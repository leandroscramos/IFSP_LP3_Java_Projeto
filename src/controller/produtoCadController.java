package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.produtoModel;
import dao.produtoDAO;

import java.io.IOException;

public class produtoCadController {

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


    public void inserirProduto(ActionEvent e) throws IOException {

        produtoModel pm = new produtoModel();
        produtoDAO pd = new produtoDAO();

        pm.setNomeProd(txtNomeProd.getText());
        pm.setvUnitProd(Double.parseDouble(txtValorProd.getText()));
        pm.setDescProd(txtDescProd.getText());

        pd.createProduto(pm);
    }

    public void cancelar() {
        Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
        stageAtual.close();
    }

}
