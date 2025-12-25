package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.PresenceDbServiceDb;
import br.com.projetoa3.bancodedados.consurmers.ConsumeDbPresence;
import br.com.projetoa3.bancodedados.interfacedb.IPresenceDb;
import br.com.projetoa3.gui.fxmlloader.FxmlLoader;
import br.com.projetoa3.modelo.*;
import br.com.projetoa3.modelo.consumersmodel.ConsumeNotes;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.INotes;
import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.ClassSchool;
import br.com.projetoa3.modelo.records.Notes;
import br.com.projetoa3.modelo.records.Student;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    private Stage mainStage;

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
        INotes iNotes = new Notas();
        ConsumeNotes consumeNotes = new ConsumeNotes(iNotes);
        Turmas.getTurmasObservable().addListener((ListChangeListener<ClassSchool>) change -> {
            mostrarTurmas();
        });
        listaNotasId.getItems().clear();
        nomeL.setText("Professor: " + Professor.getNomeLogado());
        RAL.setText("RA: " + Professor.getRaLogado());
        calendario.setValue(LocalDate.now());

        calendario.valueProperty().addListener((obs, oldDate, newDate) -> {
            carregarPresencas(newDate);
        });
        carregarPresencas(LocalDate.now());
        for (Notes nota : Notas.getNotasObservable()) {
            listaNotas.add(nota.toString());
        }

        Alunos.getListaObservable().addListener((ListChangeListener<Student>) change -> {
            alunosFormatados.clear();
        });

        Alunos.getListaObservable().addListener((ListChangeListener<Student>) change2 -> {
            filtrarPresencaPorProfessor();
        });

        Alunos.getListaObservable().addListener((ListChangeListener<Student>) change -> {
            atualizarAluno();
        });

        listaAlunosId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            listaNotasId.getItems().clear();
            if (newValue != null) {
                String[] partes = newValue.split("\\|");
                String raStr = partes[1].replace("RA:", "").trim();

                try {
                    listaNotasId.refresh();
                    long ra = Long.parseLong(raStr);
                    for (Map.Entry<String, Student> entry : consumeStudent.consumeList().entrySet()) {
                        if (entry.getValue().ra() == ra) {
                            String turma = entry.getValue().turma();

                            Notes nota = Notas.getNotaPorAluno(ra + "-" + turma);
                            if (nota != null) {
                                listaNotasId.getItems().setAll(
                                        "A1: " + nota.notaA1(),
                                        "A2: " + nota.notaA2(),
                                        "A3: " + nota.notaA3(),
                                        "Soma: " + nota.notaA1() + nota.notaA2() + nota.notaA3(),
                                        "Status: " + nota.getStatus()
                                );
                            }
                        } else {
                            listaNotasId.getItems().setAll("Sem notas cadastradas");
                        }
                    }

                } catch (NumberFormatException e) {
                    listaNotasId.getItems().setAll("Erro ao ler RA");
                }
            }
        });
        carregarPresencas(calendario.getValue());
    }

    private void carregarPresencas(LocalDate data) {
        ObservableList<String> presencasFiltradas = FXCollections.observableArrayList();
        IPresenceDb iPresenceDb = new PresenceDbServiceDb();
        ConsumeDbPresence consumeDbPresence = new ConsumeDbPresence(iPresenceDb);
        listaDePresenca.refresh();
        Map<Long, BooleanProperty> presencaData = ListaPresenca.getPresencas()
                .computeIfAbsent(data, d -> new HashMap<>());
        Alunos.getListaObservable().forEach(aluno -> {
            BooleanProperty prop = presencaData.computeIfAbsent(aluno.ra(), ra -> new SimpleBooleanProperty(false));
            prop.addListener((obs, oldVal, newVal) -> {
                consumeDbPresence.insertPresence(aluno.ra(), data, newVal);
            });
        });

        Alunos.getListaObservable().forEach(aluno -> {
            consumeDbPresence.createConsume();
            for (Map.Entry<Long, BooleanProperty> entry : presencaData.entrySet()) {
                Long ra = entry.getKey();
                BooleanProperty presente = entry.getValue();
                consumeDbPresence.insertPresence(ra, data, presente.get());
            }
        });

        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.professor().equals(Professor.getRaLogado())) {
                presencasFiltradas.add(aluno.nome() + " | RA: " + aluno.ra() + " | Turma: " + aluno.turma());
            }
        }
        listaDePresenca.setItems(presencasFiltradas);
        listaDePresenca.refresh();
        listaDePresenca.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(item);
                    String[] partes = item.split("\\|");
                    String raStr = partes[1].replace("RA:", "").trim();
                    Long ra = Long.parseLong(raStr);
                    checkBox.selectedProperty().unbind();
                    checkBox.selectedProperty().bindBidirectional(presencaData.get(ra));
                    setGraphic(checkBox);
                }
            }
        });
    }

    @FXML
    private void filtrarAlunosPorTurma(String turma) {
        ObservableList<String> alunosFiltrados = FXCollections.observableArrayList();
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.turma().equals(turma)) {
                alunosFiltrados.add(aluno.toString());
            }
        }
        listaAlunosId.setItems(alunosFiltrados);
        listaDePresenca.setItems(alunosFiltrados);
    }

    @FXML
    public void mostrarTurmas() {
        trocarTurmaMenu.getItems().clear();

        MenuItem todasTurmas = new MenuItem("Todas as turmas");
        todasTurmas.setOnAction(event -> {
            listaAlunosId.setItems(alunosFormatados);
            listaDePresenca.setItems(alunosFormatados);
    });
        trocarTurmaMenu.getItems().add(todasTurmas);

        List<String> turmas = new ArrayList<>(Turmas.getTurmasObservable().stream()
                .map(ClassSchool::nome)
                .distinct()
                .toList());

        for (String turma : turmas) {
            MenuItem item = new MenuItem(turma);
            item.setOnAction(event -> filtrarAlunosPorTurma(turma));
            trocarTurmaMenu.getItems().add(item);
        }
    }
    public void atualizarAluno(){
        ObservableList<String> alunosFiltPorProfessor = FXCollections.observableArrayList();
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.professor().equals(Professor.getRaLogado())) {
                alunosFiltPorProfessor.add(aluno.toString());
            }
        }
        listaAlunosId.setItems(alunosFiltPorProfessor);
    }

    private void filtrarPresencaPorProfessor() {
        ObservableList<String> presencasFiltradas = FXCollections.observableArrayList();
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.professor().equals(Professor.getRaLogado())) {
                presencasFiltradas.add(aluno.nome() + " | RA: " + aluno.ra());
            }
        }
        listaDePresenca.setItems(presencasFiltradas);
        listaDePresenca.refresh();
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
        Stage stage = new Stage();
        fxmlLoader.sairDaConta(RAL, listaAlunosId);
    }
}

