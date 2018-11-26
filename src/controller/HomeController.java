package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.net.URL;
import java.util.GregorianCalendar;

import java.awt.*;
import java.io.IOException;
import java.util.ResourceBundle;

import static application.Main.sceneChange;

public class HomeController {

    @FXML
    private JFXButton btnAgenda;

    @FXML
    private JFXButton btnShowPessoa;

    @FXML
    private JFXButton btnProdutos;

    @FXML
    private JFXButton btnVendas;

    @FXML
    private JFXButton btnSair;


    public void showAgenda() throws Exception {
        sceneChange("sceneAgenda");
    }

    public void showPessoas() throws Exception {
        sceneChange("scenePessoas");
    }

    public void showProdutos() throws Exception {
        sceneChange("sceneProdutos");
    }

    public void showVendas() throws Exception {
        sceneChange("sceneVendas");
    }

    public void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}


