package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.TeacherServiceDb;
import br.com.projetoa3.gui.validations.ValidationTeacher;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.records.Teach;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CadastroProfessorControllers implements Initializable {

    @FXML
    private Button BotaoCadastrar;

    @FXML
    private TextField Email;

    @FXML
    private TextField Nome;

    @FXML
    private TextField RA;

    @FXML
    private PasswordField Senha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BotaoCadastrar.setOnAction(event -> {
            salvarDados();
        });

    }
    @FXML
    protected void salvarDados() {
        TeacherServiceDb crud = new TeacherServiceDb();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Teach teach = new Teach(Nome.getText(), RA.getText(), Email.getText(), Senha.getText());
        ValidationTeacher.validationFieldTeacher(teach);
        ValidationTeacher.validationsRaTeacher(teach);

        } else if (Professor.getProfessorLista().containsKey(professor.getRa())) {
            alert.setContentText("Professor já cadastrado com este RA.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Erro no cadastro");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        } else if(Professor.getProfessorLista().containsKey(professor.getEmail())){
            alert.setContentText("Professor já cadastrado com este email.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Erro no cadastro");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();

    }else if (professor.validarEmail() && professor.validarSenha()) {
            professor.adicionarProfessor(professor);
            for(Map.Entry<String, Professor> entry : Professor.getProfessorLista().entrySet()) {
                crud.inserirProfessor(entry.getKey(), entry.getValue().getNome(), entry.getValue().getEmail(), entry.getValue().getSenha());
            }
            alert.setContentText("Clique em OK para ir a tela de login.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Professor cadastrado com sucesso!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            Stage currentStage = (Stage) BotaoCadastrar.getScene().getWindow();
            currentStage.close();
        } else {
            alert.setContentText("Email ou senha inválidos. A senha deve ter pelo menos 6 caracteres.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        }

        }
    }