package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import connection.ConnectionFactory;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProdutoModel;
import dao.ProdutoDAO;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

//import static javafx.scene.control.cell.ChoiceBoxTableCell.forTableColumn;

public class ProdutoController implements Initializable {

    @FXML
    private JFXTextField search;

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

    @FXML
    private JFXButton btnReportProdutos, btnReportServicos;

    ProdutoDAO pd = new ProdutoDAO();
    ProdutoModel pm = new ProdutoModel();
    ArrayList<ProdutoModel> pmArray =  new ArrayList<>();
    ObservableList<ProdutoModel> listaProduto = FXCollections.observableArrayList();
    FilteredList filter = new FilteredList(listaProduto, e->true);
    String flag;

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pmArray = pd.readAllProdutos();
        preencherTabela(pmArray);
    }

    @FXML
    private void search (KeyEvent event){

        search.textProperty().addListener((ov, oldValue, newValue) -> {
            search.setText(newValue.toUpperCase());
        });

        search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super ProdutoModel>) (ProdutoModel pm)->{

            if (newValue.isEmpty() || newValue==null){
                return true;
            }
            else if (pm.getNomeProd().toUpperCase().contains(newValue)){
                return true;
            }
            return false;

            });

        });

        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tableProduto.comparatorProperty());
        tableProduto.setItems(sort);
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
        pm.setVUnitProd(tableProduto.getSelectionModel().getSelectedItem().getVUnitProd());
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
        pm.setVUnitProd(tableProduto.getSelectionModel().getSelectedItem().getVUnitProd());
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

    public void preencherTabela(ArrayList<ProdutoModel> pmArray) {

        pmArray.forEach((pm) -> {
            listaProduto.add(new ProdutoModel(pm.getCodProd(), pm.getNomeProd(), pm.getCategoria(), pm.getVUnitProd(), pm.getEstoque(), pm.getDescProd()));
        });
        colunaCodProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, Integer>("codProd"));
        colunaNomeProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nomeProd"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("categoria"));
        colunaValorProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, Double>("vUnitProd"));
        colunaEstoque.setCellValueFactory(new PropertyValueFactory<ProdutoModel, Integer>("estoque"));
        colunaDescProd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("descProd"));
        tableProduto.setItems(listaProduto);
    }

    public void reportProdutos() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirma impressão");
        alert.setHeaderText(null);
        alert.setContentText("Confirma impressão do relatório de Produtos?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                JasperPrint print = JasperFillManager.fillReport("E:\\Dropbox\\[ifsp]\\4º Semetre\\LP3\\Projeto\\Fase 4\\ProjetoSisGS\\src\\reports\\reportProdutos.jasper",null, con);
                JasperViewer.viewReport(print,false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            alert.close();
        }


    }

    public void reportServicos() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirma impressão");
        alert.setHeaderText(null);
        alert.setContentText("Confirma impressão do relatório de Serviços?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                JasperPrint print = JasperFillManager.fillReport("E:\\Dropbox\\[ifsp]\\4º Semetre\\LP3\\Projeto\\Fase 4\\ProjetoSisGS\\src\\reports\\reportServicos.jasper",null, con);
                JasperViewer.viewReport(print,false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            alert.close();
        }
    }

    public void cancelar() throws Exception {
        Main.sceneChange("sceneHome");
    }
}
