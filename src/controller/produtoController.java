package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class produtoController {

    @FXML
    private JFXTextField txtFiltrar;

    @FXML
    private JFXTreeTableView tableProduto;

    @FXML
    private JFXButton btnNovo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnRemover;

    public void newProduto() throws IOException {

        Stage stage = new Stage();
        Parent fxmlNewProduto = FXMLLoader.load(getClass().getResource("../view/produtoCadView.fxml"));
        Scene sceneNewProduto = new Scene(fxmlNewProduto);
        stage.setScene(sceneNewProduto);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovo.getScene().getWindow());
        stage.show();
    }
}
