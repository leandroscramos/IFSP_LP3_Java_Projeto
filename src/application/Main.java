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

        palco.setTitle("SisGS - Sistema de Gerenciamento de Salões de Beleza");
        palco.setScene(sceneLogin);
        palco.show();
    }

    public static void loadHome() throws Exception{
        Parent fxmlHome = FXMLLoader.load(Main.class.getResource("../view/HomeView.fxml"));
        sceneHome = new Scene(fxmlHome);
    }

    public static void loadAgenda() throws Exception{
        Parent fxmlAgenda = FXMLLoader.load(Main.class.getResource("../view/AgendaView.fxml"));
        sceneAgenda = new Scene(fxmlAgenda);
    }

    public static void loadPessoas() throws Exception{
        Parent fxmlPessoas = FXMLLoader.load(Main.class.getResource("../view/PessoaView.fxml"));
        scenePessoas = new Scene(fxmlPessoas);
    }

    public static void loadProdutos() throws Exception{
        Parent fxmlProdutos = FXMLLoader.load(Main.class.getResource("../view/ProdutoView.fxml"));
        sceneProdutos = new Scene(fxmlProdutos);
    }

    public static void loadVendas() throws Exception{
        Parent fxmlVendas = FXMLLoader.load(Main.class.getResource("../view/VendasView.fxml"));
        sceneVendas = new Scene(fxmlVendas);
    }

    public static void sceneChange(String cena) throws Exception {
        switch (cena){
            case "sceneLogin":
                palco.setScene(sceneLogin);
                break;
            case "sceneHome":
                loadHome();
                palco.setScene(sceneHome);
                palco.centerOnScreen();
                break;
            case "sceneAgenda":
                loadAgenda();
                palco.setScene(sceneAgenda);
                break;
            case "scenePessoas":
                loadPessoas();
                palco.setScene(scenePessoas);
                break;
            case "sceneProdutos":
                loadProdutos();
                palco.setScene(sceneProdutos);
                break;
            case "sceneVendas":
                loadVendas();
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
