package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.AgendaDAO;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PessoaModel;
import model.ProdutoModel;
import model.AgendaModel;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AgendaController implements Initializable{

    @FXML
    private JFXDatePicker txtData, dataPesquisa;

    @FXML
    private JFXTimePicker txtHora;

    @FXML
    private ComboBox cbServico, cbCliente;

    @FXML
    private JFXButton btnNovoServico, btnNovoCliente, btnConfirma, btnCancelar, btnEditar, btnRemover;

    @FXML
    private TableView tableAgenda;

    @FXML
    private TableColumn<AgendaModel, Date> colunaDia;

    @FXML
    private TableColumn<AgendaModel, Time> colunaHora;

    @FXML
    private TableColumn<AgendaModel, PessoaModel> colunaCliente;

    @FXML
    private TableColumn<AgendaModel, ProdutoModel> colunaServico;

    AgendaModel am = new AgendaModel();
    AgendaDAO ad = new AgendaDAO();
    ProdutoModel pm = new ProdutoModel();
    ProdutoDAO pd = new ProdutoDAO();
    PessoaModel psm = new PessoaModel();
    PessoaDAO psd = new PessoaDAO();
    ArrayList<ProdutoModel> pmArray = new ArrayList<>();
    ArrayList<PessoaModel> psmArray = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheCombo();
    }

    public void cadastrarAgendamento(ActionEvent e) throws IOException {

        am.setData(txtData.getValue().toString());
        am.setHora(txtHora.getValue().toString());

        pmArray.forEach((pmObj)->{
            if(cbServico.getValue().equals(pmObj.getNomeProd())){
                am.setPm(pmObj);
            }
        });

        psmArray.forEach((psmObj)->{
            if(cbCliente.getValue().equals(psmObj.getNome())){
                am.setPsm(psmObj);
            }
        });

        ad.createAgenda(am);

        limparCampos();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensagem de confirmação: ");
        alert.setContentText("Agendamento realizado com sucesso!!!");
        alert.showAndWait();
    }

    public void cancelar() throws Exception {
        Main.sceneChange("sceneHome");

    }

    public void novoServico() throws Exception {
        String flag = "insert";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProdutoCadController.class.getResource("../view/ProdutoCadView.fxml"));
        Parent parent = (Parent) loader.load();
        Scene sceneNewProduto = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(sceneNewProduto);
        ProdutoCadController pcc = loader.getController();
        pcc.setFlag(flag);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovoServico.getScene().getWindow());
        stage.showAndWait();
        preencheCombo();

    }

    public void limparCampos(){
        txtData.setValue(LocalDate.now());
        txtHora.setValue(LocalTime.now());
        cbServico.getSelectionModel().clearSelection();
        cbCliente.getSelectionModel().clearSelection();
    }

    public void preencheCombo(){
        cbServico.getSelectionModel().clearSelection();
        cbCliente.getSelectionModel().clearSelection();
        pmArray = pd.readAllProdutos();
        for (ProdutoModel pm: pmArray){
            cbServico.getItems().add(pm.getNomeProd());
        }
        psmArray = psd.readAllPessoa();
        for (PessoaModel psm: psmArray){
            cbCliente.getItems().add(psm.getNome());
        }
        cbServico.getSelectionModel().isEmpty();
    }
}
