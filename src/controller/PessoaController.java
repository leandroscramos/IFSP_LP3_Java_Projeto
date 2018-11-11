package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.PessoaDAO;
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
import model.PessoaModel;
import model.ProdutoModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static application.Main.sceneChange;

public class PessoaController implements Initializable{

    @FXML
    private JFXTextField txtFiltrar;

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

    PessoaDAO psd = new PessoaDAO();
    PessoaModel psm = new PessoaModel();
    ArrayList<PessoaModel> psmArray = new ArrayList<>();
    ObservableList<PessoaModel> listaPessoa = FXCollections.observableArrayList();
    String flag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        psmArray = psd.readAllPessoa();
        preencherTabela(psmArray);
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

}
