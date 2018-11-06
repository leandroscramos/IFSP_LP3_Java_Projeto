package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;

public class homeController {

    @FXML
    private JFXButton btnAgenda;

    @FXML
    private JFXButton btnShowPessoa;

    @FXML
    private JFXButton btnProdutos;

    @FXML
    private JFXButton btnVendas;

    @FXML
    private JFXButton btnRelatorios;

    @FXML
    private JFXButton btnSair;

    public void showAgenda() throws IOException {
        Main.sceneChange("sceneAgenda");
    }

    public void showPessoas() throws IOException {
        Main.sceneChange("scenePessoas");
    }

    public void showProdutos() throws IOException {
        Main.sceneChange("sceneProdutos");
    }

    public void showVendas() throws IOException {
        Main.sceneChange("sceneVendas");
    }

    public void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}


