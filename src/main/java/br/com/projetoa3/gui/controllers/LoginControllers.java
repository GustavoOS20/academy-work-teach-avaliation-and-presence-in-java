package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.gui.alerts.AlertsClass;
import br.com.projetoa3.gui.fxmlloader.FxmlLoader;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.records.Teach;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllers implements Initializable {

    @FXML
    private TextField LoginUsuario;

    @FXML
    private PasswordField LoginSenha;

    @FXML
    private Button botaoEntrar;

    @FXML
    private Button botaoCadastrar;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        botaoEntrar.setOnAction(event -> {
            VerificarLogin();
        });
    }

    public void VerificarLogin() {
        AlertsClass alerts = new AlertsClass();
         boolean loginValido = false;

        for (Teach teach : Professor.getProfessorLista().values()) {
            if (teach.email().equals(LoginUsuario.getText()) && teach.senha().equals(LoginSenha.getText())) {
                loginValido= true;
                Professor.setNomeLogado(teach.nome()) ;
                Professor.setRaLogado(teach.ra());
                break;
            }
        }

        if (loginValido) {
            try {
                FxmlLoader fxloader = new FxmlLoader();
                fxloader.fxmlLoaderPrincipal(botaoEntrar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(LoginUsuario.getText().isEmpty() || LoginSenha.getText().isEmpty()){
            alerts.alertInformation("Login", "Por favor, preencha todos os campos.", "Login de usuários");
        } else {
            alerts.alertInformation("Login", "Usuário ou senha incorretos.", "Login de usuários");
        }

    }

    public void AbrirCadastrar() throws IOException {
        FxmlLoader fxloader = new FxmlLoader();
        fxloader.fxmlSingUp(botaoCadastrar);
    }

    public void AbrirLogin() throws IOException {
        FxmlLoader fxloader = new FxmlLoader();


    }
}
