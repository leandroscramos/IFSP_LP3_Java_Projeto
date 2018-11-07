package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage palco;
    private static Scene sceneLogin;
    private static Scene sceneHome;
    private static Scene sceneAgenda;
    private static Scene scenePessoas;
    private static Scene sceneProdutos;
    private static Scene sceneVendas;
    //private static Scene sceneRelatorios;


    @Override
    public void start(Stage primaryStage) throws Exception{
        palco = primaryStage;

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
        sceneLogin = new Scene(fxmlLogin);
        Parent fxmlHome = FXMLLoader.load(getClass().getResource("../view/HomeView.fxml"));
        sceneHome = new Scene(fxmlHome);
        Parent fxmlAgenda = FXMLLoader.load(getClass().getResource("../view/AgendaView.fxml"));
        sceneAgenda = new Scene(fxmlAgenda);
        Parent fxmlPessoas = FXMLLoader.load(getClass().getResource("../view/PessoaView.fxml"));
        scenePessoas = new Scene(fxmlPessoas);
        Parent fxmlProdutos = FXMLLoader.load(getClass().getResource("../view/ProdutoView.fxml"));
        sceneProdutos = new Scene(fxmlProdutos);
        Parent fxmlVendas = FXMLLoader.load(getClass().getResource("../view/VendasView.fxml"));
        sceneVendas = new Scene(fxmlVendas);

        palco.setTitle("SisGS - Sistema de Gerenciamento de Salões de Beleza");
        palco.setScene(sceneLogin);
        palco.show();

    }

    public static void sceneChange(String cena) {
        switch (cena){
            case "sceneLogin":
                palco.setScene(sceneLogin);
                break;
            case "sceneHome":
                palco.setScene(sceneHome);
                palco.centerOnScreen();
                break;
            case "sceneAgenda":
                palco.setScene(sceneAgenda);
                break;
            case "scenePessoas":
                palco.setScene(scenePessoas);
                break;
            case "sceneProdutos":
                palco.setScene(sceneProdutos);
                break;
            case "sceneVendas":
                palco.setScene(sceneVendas);
                break;
            /*
            case "sceneRelatorios":
                palco.setScene(sceneRelatorios);
                break;
            */
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
