package controller;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.AgendaDAO;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.Optional;
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
    private TableView<AgendaModel> tableAgenda;

    @FXML
    private TableColumn<AgendaModel, Integer> colunaId;

    @FXML
    private TableColumn<AgendaModel, Date> colunaDia;

    @FXML
    private TableColumn<AgendaModel, Time> colunaHora;

    @FXML
    private TableColumn<AgendaModel, String> colunaCliente;

    @FXML
    private TableColumn<AgendaModel, String> colunaServico;

    AgendaModel am = new AgendaModel();
    AgendaDAO ad = new AgendaDAO();
    ProdutoModel pm = new ProdutoModel();
    ProdutoDAO pd = new ProdutoDAO();
    PessoaModel psm = new PessoaModel();
    PessoaDAO psd = new PessoaDAO();
    ArrayList<ProdutoModel> pmArray = new ArrayList<>();
    ArrayList<PessoaModel> psmArray = new ArrayList<>();
    ArrayList<AgendaModel> amArray =  new ArrayList<>();
    ObservableList<AgendaModel> listaAgenda = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        amArray = ad.selectAllAgendas();
        preencherTabela(amArray);
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

        tableAgenda.getItems().clear();
        amArray = ad.selectAllAgendas();
        preencherTabela(amArray);
    }

    public void excluirAgendamento(){

        am.setId(tableAgenda.getSelectionModel().getSelectedItem().getId());
        am.setData(tableAgenda.getSelectionModel().getSelectedItem().getData());
        am.setHora(tableAgenda.getSelectionModel().getSelectedItem().getHora());
        am.setPm(tableAgenda.getSelectionModel().getSelectedItem().getPm());
        am.setPsm(tableAgenda.getSelectionModel().getSelectedItem().getPsm());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Você realmente deseja excluir o agendamento selecionado? Essa ação não poderá ser desfeita!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            ad.deleteAgenda(am);
            tableAgenda.getItems().clear();
            amArray = ad.selectAllAgendas();
            preencherTabela(amArray);
        } else {
            alert.close();
        }
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

    public void novoCliente() throws Exception {
        String flag = "insert";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProdutoCadController.class.getResource("../view/PessoaCadView.fxml"));
        Parent parent = (Parent) loader.load();
        Scene sceneNewPessoa = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(sceneNewPessoa);
        PessoaCadController pscc = loader.getController();
        pscc.setFlag(flag);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnNovoCliente.getScene().getWindow());
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
        cbServico.getItems().clear();
        pmArray = pd.readAllProdutos();
        for (ProdutoModel pm: pmArray){
            cbServico.getItems().add(pm.getNomeProd());
        }
        cbCliente.getItems().clear();
        psmArray = psd.readAllPessoa();
        for (PessoaModel psm: psmArray){
            cbCliente.getItems().add(psm.getNome());
        }
    }

    public void preencherTabela(ArrayList<AgendaModel> pmArray) {

        amArray.forEach((am) -> {
            listaAgenda.add(new AgendaModel(am.getId(), am.getData(), am.getHora(), am.getPm(), am.getPsm()));
        });

        colunaId.setCellValueFactory(new PropertyValueFactory<AgendaModel, Integer>("id"));
        colunaDia.setCellValueFactory(new PropertyValueFactory<AgendaModel, Date>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<AgendaModel, Time>("hora"));
        colunaServico.setCellValueFactory((param)-> new SimpleStringProperty(param.getValue().getPm().getNomeProd()));
        colunaCliente.setCellValueFactory((param)-> new SimpleStringProperty(param.getValue().getPsm().getNome()));
        tableAgenda.setItems(listaAgenda);

    }
}
