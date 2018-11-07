package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProdutoModel;
import dao.ProdutoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {

    @FXML
    private JFXTextField txtFiltrar;

    @FXML
    private TableView<ProdutoModel> tableProduto;

    @FXML
    private TableColumn<ProdutoModel, Integer> colunaCodProd;

    @FXML
    private TableColumn<ProdutoModel, String> colunaNomeProd;

    @FXML
    private TableColumn<ProdutoModel, Double> colunaValorProd;

    @FXML
    private TableColumn<ProdutoModel, String> colunaDescProd;

    @FXML
    private JFXButton btnNovo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnCancelar;

    ProdutoDAO pd = new ProdutoDAO();
    ProdutoModel pm = new ProdutoModel();
    ArrayList<ProdutoModel> pmArray =  new ArrayList<>();
    ObservableList<ProdutoModel> listaProduto = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pmArray = pd.readAllProdutos();
        preencherTabela(pmArray);
    }

    public void cancelar() throws IOException {
        Main.sceneChange("sceneHome");
    }

    public void newProduto() throws IOException {
        Stage stage = new Stage();
        Parent fxmlNewProduto = FXMLLoader.load(getClass().getResource("../view/ProdutoCadView.fxml"));
        Scene sceneNewProduto = new Scene(fxmlNewProduto);
        stage.setScene(sceneNewProduto);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovo.getScene().getWindow());
        stage.show();
    }

    public void preencherTabela(ArrayList<ProdutoModel> pmArray) {

        pmArray.forEach((pm) -> {
            listaProduto.add(new ProdutoModel(pm.getCodProd(), pm.getNomeProd(), pm.getvUnitProd(), pm.getDescProd()));
        });

        colunaCodProd.setCellValueFactory(new PropertyValueFactory<>("codProd"));
        colunaNomeProd.setCellValueFactory(new PropertyValueFactory<>("nomeProd"));
        colunaValorProd.setCellValueFactory(new PropertyValueFactory<>("vUnitProd"));
        colunaDescProd.setCellValueFactory(new PropertyValueFactory<>("descProd"));
        tableProduto.setItems(listaProduto);

    }
}
