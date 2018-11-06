package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.io.IOException;

public class agendaController {

    @FXML
    private JFXButton btnCancelar;

    public void cancelar() throws IOException {
        Main.sceneChange("sceneHome");
    }
}
