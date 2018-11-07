package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PessoaController {

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnNewPessoa;

    public void newPessoa() throws IOException {

        Stage stage = new Stage();
        Parent fxmlNewPessoa = FXMLLoader.load(getClass().getResource("../view/PessoaCadView.fxml"));
        Scene sceneNewPessoa = new Scene(fxmlNewPessoa);
        stage.setScene(sceneNewPessoa);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNewPessoa.getScene().getWindow());
        stage.show();
    }

    public void voltar() throws IOException {
        Main.sceneChange("sceneHome");
    }

}
