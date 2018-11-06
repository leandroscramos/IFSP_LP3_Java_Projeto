package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.skins.JFXTableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.produtoModel;
import dao.produtoDAO;

import javax.swing.text.TableView;
import java.io.IOException;
import java.util.ArrayList;

public class produtoController {

    @FXML
    private JFXTextField txtFiltrar;

    @FXML
    private TableView<produtoModel> tableProduto;

    @FXML
    private JFXButton btnNovo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnRemover;

    produtoDAO pd = new produtoDAO();
    produtoModel pm = new produtoModel();
    ArrayList<produtoModel> pmArray =  new ArrayList<>();
    ObservableList<produtoModel> listaProduto = FXCollections.observableArrayList();

    public void newProduto() throws IOException {
        Stage stage = new Stage();
        Parent fxmlNewProduto = FXMLLoader.load(getClass().getResource("../view/produtoCadView.fxml"));
        Scene sceneNewProduto = new Scene(fxmlNewProduto);
        stage.setScene(sceneNewProduto);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovo.getScene().getWindow());
        stage.show();
    }

    public void preencherTabela(ArrayList<produtoModel> amArray){
        pmArray.forEach((pm)->{
            listaProduto.add(new produtoModel(pm.getCodProd(), pm.getNomeProd(), pm.getvUnitProd(), pm.getDescProd()));
        });

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbAlunos.setItems(listaAlunos);
    }
}
