package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.gui.fxmlloader.FxmlLoader;
import br.com.projetoa3.gui.interfacescreenprincipal.NotesVerification;
import br.com.projetoa3.gui.interfacescreenprincipal.PresenceListVerification;
import br.com.projetoa3.gui.interfacescreenprincipal.StudentVerification;
import br.com.projetoa3.gui.interfacescreenprincipal.TeacherVerification;
import br.com.projetoa3.modelo.*;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.ClassSchool;
import br.com.projetoa3.modelo.records.Notes;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.ListChangeListener;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TelaPrincipalController implements Initializable {
    private static final ObservableList<String> alunosFormatados = FXCollections.observableArrayList();

    private static final ObservableList<String> listaNotas = FXCollections.observableArrayList();
    @FXML
    private Label RAL;

    @FXML
    private Menu trocarTurmaMenu;

    @FXML
    private ListView<String> listaNotasId;

    @FXML
    private DatePicker calendario;

    @FXML
    private ListView<String> listaDePresenca;

    @FXML
    private ListView<String> listaAlunosId;

    @FXML
    private Label nomeL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IStudent iStudent = new Alunos();
        ConsumeStudent consumeStudent = new ConsumeStudent(iStudent);
        listaNotasId.getItems().clear();
        nomeL.setText("Professor: " + Professor.getNomeLogado());
        RAL.setText("RA: " + Professor.getRaLogado());
        calendario.setValue(LocalDate.now());
        listaAlunosId.setItems(alunosFormatados);
        NotesVerification.notesVer(listaAlunosId, listaNotasId, consumeStudent);
        Turmas.getTurmasObservable().addListener((ListChangeListener<ClassSchool>) change -> {
            mostrarTurmas();
        });
        calendario.valueProperty().addListener((obs, oldDate, newDate) -> {
            PresenceListVerification.carregarPresencas(newDate,listaDePresenca);
        });
        PresenceListVerification.carregarPresencas(LocalDate.now(), listaDePresenca);
        for (Notes nota : Notas.getNotasObservable()) {
            listaNotas.add(nota.toString());
        }
        Alunos.getListaObservable().addListener((ListChangeListener<Student>) change -> {
            alunosFormatados.clear();
            TeacherVerification.filtrarPresencaPorProfessor(listaDePresenca);
            StudentVerification.atualizarAluno(listaAlunosId);
        });

        PresenceListVerification.carregarPresencas(calendario.getValue(), listaDePresenca);
    }

    @FXML
    public void abrirTelaCadastro() throws IOException {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Stage stage = new Stage();
        fxmlLoader.abrirTelaCadastro(stage,listaDePresenca);
    }

    @FXML
    public void abrirTelaNotas() throws IOException {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Stage stage = new Stage();
        fxmlLoader.abrirTelaNotas(listaNotasId, listaAlunosId, stage);
    }

    @FXML
    public void abrirTelaRemoverAluno() throws IOException {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Stage stage = new Stage();
        fxmlLoader.abrirTelaRemoverAluno(stage);
    }

    @FXML
    public void adicionarTurma() throws IOException {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Stage stage = new Stage();
        fxmlLoader.adicionarTurma(stage);
    }

    @FXML
    public void sairDaConta() throws IOException {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.sairDaConta(RAL, listaAlunosId);
    }

    @FXML
    public void mostrarTurmas() {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.mostrarTurmas(trocarTurmaMenu, listaAlunosId, listaDePresenca, alunosFormatados);
    }
}

