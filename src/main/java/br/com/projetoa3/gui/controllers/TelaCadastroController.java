package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.AlunosCrud;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.Turmas;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TelaCadastroController implements Initializable {

    @FXML
    private TextField cadastrarNomeId;

    @FXML
    private TextField cadastrarRAId1;

    @FXML
    private ComboBox<String> comboBoxTurma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarTurmasPorProfessor();
        Alunos.getListaObservable().addListener((ListChangeListener<Alunos>) change2 -> {
            atualizarTurmasPorProfessor();
        });
    }

    @FXML
    private void confirmarCadastro() {

        Long raLong = Long.parseLong(cadastrarRAId1.getText().trim());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Aluno");
        alert.setHeaderText("Confirmação de Cadastro");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        if (cadastrarNomeId.getText().isEmpty() || cadastrarRAId1.getText().isEmpty() || comboBoxTurma.getValue() == null) {
            alert.setContentText("Por favor, preencha todos os campos.");
            stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            return;
        } else if (cadastrarRAId1.getText().length() != 10) {
            alert.setContentText("Ra invalido. Deve ter 10 dígitos.");
            stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            return;
        }
        for (Map.Entry<String, Alunos> entry3 : Alunos.getLista().entrySet()) {
            if (entry3.getValue().getRa().equals(raLong) && entry3.getValue().getProfessor().equals(Professor.getRaLogado()) && entry3.getValue().getTurma().equals(comboBoxTurma.getValue())) {
                alert.setContentText("RA já cadastrado. Por favor, insira um RA diferente.");
                stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }
        }
        Alunos.addStudents(new Alunos(cadastrarNomeId.getText().trim(), raLong, comboBoxTurma.getValue(), Professor.getRaLogado()));

        for (Map.Entry<String, Alunos> entry3 : Alunos.getLista().entrySet()) {
            AlunosCrud managerr = new AlunosCrud();
            managerr.inserirAluno(entry3.getValue().getRa(), entry3.getValue().getNome(), entry3.getValue().getTurma(), entry3.getValue().getProfessor());
        }


        alert.setHeaderText("Aluno cadastrado com sucesso!\nNome: " + cadastrarNomeId.getText() + "\nRA: " + cadastrarRAId1.getText() + "\nTurma: " + comboBoxTurma.getValue());
        alert.setContentText("Clique em OK para continuar.");
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        cadastrarNomeId.clear();
        cadastrarRAId1.clear();
        alert.showAndWait();
    }

    private void atualizarTurmasPorProfessor() {
        ObservableList<String> turmasFiltPorProfessor = FXCollections.observableArrayList();
        for (Turmas turma : Turmas.getTurmasObservable()) {
            if (turma.getProfessor().equals(Professor.getRaLogado())) {
                turmasFiltPorProfessor.add(turma.getTurma());
            }
        }
        comboBoxTurma.getItems().clear();
        comboBoxTurma.getItems().addAll(turmasFiltPorProfessor);
    }
}