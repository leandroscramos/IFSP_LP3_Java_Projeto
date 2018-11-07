package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import model.PessoaModel;
import dao.PessoaDAO;

import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import java.util.Date;

public class PessoaCadController {

    @FXML
    private TextField txtCPF, txtNome, txtSexo, txtEmail, txtCelular;

    @FXML
    private DatePicker txtDataNasc;

    @FXML
    private JFXCheckBox cbFuncionario;

    @FXML
    private JFXButton btnInserir, btnCancelar;

}
