package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProdutoModel;
import dao.ProdutoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.ChoiceBoxTableCell.forTableColumn;

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
    private TableColumn<ProdutoModel, String> colunaCategoria;

    @FXML
    private TableColumn<ProdutoModel, Integer> colunaEstoque;

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
    String flag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pmArray = pd.readAllProdutos();
        preencherTabela(pmArray);
    }

    public void newProduto() throws IOException {

        flag = "insert";

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProdutoCadController.class.getResource("../view/ProdutoCadView.fxml"));
        Parent parent = (Parent) loader.load();
        Scene sceneNewProduto = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(sceneNewProduto);
        ProdutoCadController pdc = loader.getController();

        pdc.setFlag(flag);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovo.getScene().getWindow());
        stage.showAndWait();

        tableProduto.getItems().clear();
        pmArray = pd.readAllProdutos();
        preencherTabela(pmArray);

    }

    public void updateProduto() throws IOException {

        flag = "update";

        pm.setCodProd(tableProduto.getSelectionModel().getSelectedItem().getCodProd());
        pm.setNomeProd(tableProduto.getSelectionModel().getSelectedItem().getNomeProd());
        pm.setCategoria(tableProduto.getSelectionModel().getSelectedItem().getCategoria());
        pm.setvUnitProd(tableProduto.getSelectionModel().getSelectedItem().getvUnitProd());
        pm.setEstoque(tableProduto.getSelectionModel().getSelectedItem().getEstoque());
        pm.setDescProd(tableProduto.getSelectionModel().getSelectedItem().getDescProd());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProdutoCadController.class.getResource("../view/ProdutoCadView.fxml"));
        Parent parent = (Parent) loader.load();
        Scene sceneNewProduto = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(sceneNewProduto);
        ProdutoCadController pdc = loader.getController();

        pdc.exibirDados(pm);
        pdc.setFlag(flag);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnEditar.getScene().getWindow());
        stage.showAndWait();

        tableProduto.getItems().clear();
        pmArray = pd.readAllProdutos();
        preencherTabela(pmArray);

    }

    public void deletarProduto(){

        pm.setCodProd(tableProduto.getSelectionModel().getSelectedItem().getCodProd());
        pm.setNomeProd(tableProduto.getSelectionModel().getSelectedItem().getNomeProd());
        pm.setvUnitProd(tableProduto.getSelectionModel().getSelectedItem().getvUnitProd());
        pm.setDescProd(tableProduto.getSelectionModel().getSelectedItem().getDescProd());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir o registro do produto selecionado? Essa ação não poderá ser desfeita!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            pd.deleteProduto(pm);
            tableProduto.getItems().clear();
            pmArray = pd.readAllProdutos();
            preencherTabela(pmArray);
        } else {
            alert.close();
        }
    }

    public void cancelar() throws Exception {
        Main.sceneChange("sceneHome");
    }

    public void preencherTabela(ArrayList<ProdutoModel> pmArray) {

        pmArray.forEach((pm) -> {
            listaProduto.add(new ProdutoModel(pm.getCodProd(), pm.getNomeProd(), pm.getCategoria(), pm.getvUnitProd(), pm.getEstoque(), pm.getDescProd()));
        });
        colunaCodProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, Integer>("codProd"));
        colunaNomeProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nomeProd"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("categoria"));
        colunaValorProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, Double>("vUnitProd"));
        colunaEstoque.setCellValueFactory(new PropertyValueFactory<ProdutoModel, Integer>("estoque"));
        colunaDescProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("descProd"));
        tableProduto.setItems(listaProduto);

    }


}
