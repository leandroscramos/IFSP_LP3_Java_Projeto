package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import model.pessoaModel;
import dao.pessoaDAO;

import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import java.util.Date;

public class pessoaCadController {

    @FXML
    private TextField txtCPF, txtNome, txtSexo, txtEmail, txtCelular;

    @FXML
    private DatePicker txtDataNasc;

    @FXML
    private JFXCheckBox cbFuncionario;

    @FXML
    private JFXButton btnInserir, btnCancelar;
    /*
    pessoaDAO pessoadao = new pessoaDAO();
    pessoaModel pessoamodel = new pessoaModel(txtCPF.getText(), txtNome.getText(), txtSexo.getText(), txtDataNasc.getValue(), cbFuncionario.getText(), txtEmail.getText(), txtCelular.getText());

    */
    public void inserirPessoa(){
        /*
        pessoaModel pessoamodel = new pessoaModel(
                txtCPF.getText(),
                txtNome.getText(),
                txtSexo.getText(),
                txtDataNasc.getValue(),
                cbFuncionario.getText(),
                txtEmail.getText(),
                txtCelular.getText()
        );

        pessoadao.createPessoa(pessoamodel);

        //limparCampos();
        /*

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensagem de confirmação: ");
        alert.setContentText("Nova Sala foi cadastrada com sucesso!!!");
        alert.showAndWait();

        //st.close();

        flag="inserir";
        appLogin.showEditarAluno(am, flag);
        tbAlunos.getItems().clear();//Limpa os dados da tabela
        amArray = ad.readAllAlunos();
        preencherTabela(amArray);//Preenche tabela com dados do banco
        */
    }




}
