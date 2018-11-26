package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import connection.ConnectionFactory;
import dao.PessoaDAO;
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
import model.PessoaModel;
import model.ProdutoModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

import static application.Main.sceneChange;

public class PessoaController implements Initializable{

    @FXML
    private JFXTextField search;

    @FXML
    private TableView<PessoaModel> tablePessoa;

    @FXML
    private TableColumn<PessoaModel, String> colunaCPF;

    @FXML
    private TableColumn<PessoaModel, String> colunaNomePessoa;

    @FXML
    private TableColumn<PessoaModel, String> colunaSexo;

    @FXML
    private TableColumn<PessoaModel, Date> colunaDataNasc;

    @FXML
    private TableColumn<PessoaModel, String> colunaEmail;

    @FXML
    private TableColumn<PessoaModel, String> colunaCelular;

    @FXML
    private JFXButton btnNovo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnReportClientes;

    PessoaDAO psd = new PessoaDAO();
    PessoaModel psm = new PessoaModel();
    ArrayList<PessoaModel> psmArray = new ArrayList<>();
    ObservableList<PessoaModel> listaPessoa = FXCollections.observableArrayList();
    FilteredList filter = new FilteredList(listaPessoa, e->true);
    String flag;

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        psmArray = psd.readAllPessoa();
        preencherTabela(psmArray);
    }

    @FXML
    private void search (KeyEvent event){

        search.textProperty().addListener((ov, oldValue, newValue) -> {
            search.setText(newValue.toUpperCase());
        });
        search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super PessoaModel>) (PessoaModel psm)->{

                if (newValue.isEmpty() || newValue==null){
                    return true;
                }
                else if (psm.getNome().toUpperCase().contains(newValue)){
                    return true;
                }
                return false;

            });

        });

        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tablePessoa.comparatorProperty());
        tablePessoa.setItems(sort);
    }


    public void newPessoa() throws IOException {

        flag = "insert";

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProdutoCadController.class.getResource("../view/PessoaCadView.fxml"));
        Parent parent = (Parent) loader.load();
        Scene sceneNewPessoa = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(sceneNewPessoa);
        PessoaCadController pscc = loader.getController();

        pscc.setFlag(flag);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovo.getScene().getWindow());
        stage.showAndWait();

        tablePessoa.getItems().clear();
        psmArray = psd.readAllPessoa();
        preencherTabela(psmArray);
    }

    public void updatePessoa() throws IOException {

        flag = "update";

        psm.setCpf(tablePessoa.getSelectionModel().getSelectedItem().getCpf());
        psm.setNome(tablePessoa.getSelectionModel().getSelectedItem().getNome());
        psm.setSexo(tablePessoa.getSelectionModel().getSelectedItem().getSexo());
        psm.setData_nasc(tablePessoa.getSelectionModel().getSelectedItem().getData_nasc());
        psm.setEmail(tablePessoa.getSelectionModel().getSelectedItem().getEmail());
        psm.setCelular(tablePessoa.getSelectionModel().getSelectedItem().getCelular());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProdutoCadController.class.getResource("../view/PessoaCadView.fxml"));
        Parent parent = (Parent) loader.load();
        Scene sceneNewPessoa = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(sceneNewPessoa);
        PessoaCadController pscc = loader.getController();

        pscc.exibirDados(psm);
        pscc.disableTxtCPF();
        pscc.setFlag(flag);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovo.getScene().getWindow());
        stage.showAndWait();

        tablePessoa.getItems().clear();
        psmArray = psd.readAllPessoa();
        preencherTabela(psmArray);

    }

    public void deletarPessoa(){

        psm.setCpf(tablePessoa.getSelectionModel().getSelectedItem().getCpf());
        psm.setNome(tablePessoa.getSelectionModel().getSelectedItem().getNome());
        psm.setSexo(tablePessoa.getSelectionModel().getSelectedItem().getSexo());
        psm.setData_nasc(tablePessoa.getSelectionModel().getSelectedItem().getData_nasc());
        psm.setEmail(tablePessoa.getSelectionModel().getSelectedItem().getEmail());
        psm.setCelular(tablePessoa.getSelectionModel().getSelectedItem().getCelular());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir o registro do cliente selecionado? Essa ação não poderá ser desfeita!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            psd.deletePessoa(psm);
            tablePessoa.getItems().clear();
            psmArray = psd.readAllPessoa();
            preencherTabela(psmArray);
        } else {
            alert.close();
        }

    }

    public void voltar() throws Exception {
        Main.sceneChange("sceneHome");
    }
    public void preencherTabela(ArrayList<PessoaModel> pmArray) {

        psmArray.forEach((psm) -> {
            listaPessoa.add(new PessoaModel(psm.getCpf(), psm.getNome(), psm.getSexo(), psm.getData_nasc(), psm.getEmail(), psm.getCelular()));
        });

        colunaCPF.setCellValueFactory(new PropertyValueFactory<PessoaModel, String>("cpf"));
        colunaNomePessoa.setCellValueFactory(new PropertyValueFactory<PessoaModel, String>("nome"));
        colunaSexo.setCellValueFactory(new PropertyValueFactory<PessoaModel, String>("sexo"));
        colunaDataNasc.setCellValueFactory(new PropertyValueFactory<PessoaModel, Date>("data_nasc"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<PessoaModel, String>("email"));
        colunaCelular.setCellValueFactory(new PropertyValueFactory<PessoaModel, String>("celular"));
        tablePessoa.setItems(listaPessoa);

    }

    public void reportClientes() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirma impressão");
        alert.setHeaderText(null);
        alert.setContentText("Confirma impressão do relatório de Clientes?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                JasperPrint print = JasperFillManager.fillReport("E:\\Dropbox\\[ifsp]\\4º Semetre\\LP3\\Projeto\\Fase 4\\ProjetoSisGS\\src\\reports\\reportClientes.jasper",null, con);
                JasperViewer.viewReport(print,false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            alert.close();
        }
    }

}
