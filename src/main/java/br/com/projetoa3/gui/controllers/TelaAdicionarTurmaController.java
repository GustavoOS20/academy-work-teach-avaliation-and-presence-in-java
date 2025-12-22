package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.ClassSchServiceDb;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.Turmas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

public class TelaAdicionarTurmaController implements Initializable {

    @FXML
    private TextField TurmaDigitada;

    @FXML
    private Button cadastrarTurma;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    cadastrarTurma.setOnAction(event -> {
        String idAleatorio = UUID.randomUUID().toString();
        if(TurmaDigitada.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Turma");
            alert.setHeaderText("Erro no cadastro");
            alert.setContentText("Por favor, preencha o campo da turma.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            return;
        }for(Map.Entry<String, Turmas> entry2 : Turmas.getTurmas().entrySet()){
            if(TurmaDigitada.getText().equalsIgnoreCase(entry2.getValue().getTurma()) && entry2.getValue().getProfessor().equals(Professor.getRaLogado())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro de Turma");
                alert.setHeaderText("Erro no cadastro");
                alert.setContentText("Turma já cadastrada para este professor.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }
        }
        Turmas.adicionarTurma(idAleatorio, TurmaDigitada.getText(), Professor.getRaLogado());
        for (Map.Entry<String, Turmas> entry2 : Turmas.getTurmas().entrySet()) {
            ClassSchServiceDb turmas = new ClassSchServiceDb();
            turmas.inserirTurma(entry2.getKey(), entry2.getValue().getTurma(), entry2.getValue().getProfessor());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Turma");
        alert.setHeaderText("Turma cadastrada com sucesso!");
        alert.setContentText("ID: " + idAleatorio + "\nNome da Turma: " + TurmaDigitada.getText());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        alert.showAndWait();
        });

    }

    }