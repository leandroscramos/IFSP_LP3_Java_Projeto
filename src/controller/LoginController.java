package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import model.LoginModel;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnCancel;

    public void login() throws Exception {
        LoginModel login = new LoginModel(txtLogin.getText(), txtPassword.getText());
        try {

            System.out.println(login.getUser()+" "+login.getPassword());
            if(login.getUser().equals("root") && login.getPassword().equals("root")){
                Main.sceneChange("sceneHome");
            }
        } catch (Exception e){
            System.out.println("Falha no login");
        }




    }

    public void cancel() {
        Stage stageAtual = (Stage) btnCancel.getScene().getWindow();
        stageAtual.close();
    }
}
