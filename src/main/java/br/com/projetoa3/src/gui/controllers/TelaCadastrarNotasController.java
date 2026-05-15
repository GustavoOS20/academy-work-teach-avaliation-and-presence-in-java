package br.com.projetoa3.src.gui.controllers;

import br.com.projetoa3.src.bancodedados.NotesDbServiceDb;
import br.com.projetoa3.src.bancodedados.consurmers.ConsumeDbNotes;
import br.com.projetoa3.src.bancodedados.interfacedb.INotesDb;
import br.com.projetoa3.src.gui.alerts.AlertsClass;
import br.com.projetoa3.src.gui.validations.ValidationsAddNotes;
import br.com.projetoa3.src.modelo.consumersmodel.ConsumeNotes;
import br.com.projetoa3.src.modelo.interfaces.INotes;
import br.com.projetoa3.src.modelo.records.Notes;
import br.com.projetoa3.src.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import br.com.projetoa3.src.modelo.Notas;
import br.com.projetoa3.src.modelo.Alunos;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TelaCadastrarNotasController implements Initializable {
    private Student alunoSelecionado;

    @FXML
    private Button confirmarCadastro;

    @FXML
    private ListView<Student> ListaNomesNotas;

    @FXML
    private TextField cadastrarNotaA1;

    @FXML
    private TextField cadastrarNotaA2;

    @FXML
    private TextField cadastrarNotaA3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaNomesNotas.setItems(Alunos.getListaObservable());

        ListaNomesNotas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                alunoSelecionado = newVal;
                exibirNotasDoAluno(newVal);
            }
        });

        confirmarCadastro.setOnAction(event -> {
            if (alunoSelecionado != null) {
                cadastrarNotas();
            } else {
                System.out.println("Selecione um aluno da lista.");
            }
        });
    }

    @FXML
    public void cadastrarNotas() {
        try {
            ListaNomesNotas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    alunoSelecionado = newVal;

                    exibirNotasDoAluno(newVal);
                }
            });
            AlertsClass alertsClass = new AlertsClass();
            INotes iNotes = new Notas();
            ConsumeNotes consumeNotes = new ConsumeNotes(iNotes);
            INotesDb iNotesDb = new NotesDbServiceDb();
            ConsumeDbNotes consumeDbNotes = new ConsumeDbNotes(iNotesDb);
            int notaA1 = Integer.parseInt(cadastrarNotaA1.getText());
            int notaA2 = Integer.parseInt(cadastrarNotaA2.getText());
            int notaA3 = Integer.parseInt(cadastrarNotaA3.getText());

            ValidationsAddNotes.validationsAddNotes(notaA1, notaA2, notaA3);
            ValidationsAddNotes.validationFields(cadastrarNotaA1, cadastrarNotaA2, cadastrarNotaA3);
            Notes novaNota = new Notes(notaA1, notaA2, notaA3);
            int soma = novaNota.notaA1() + novaNota.notaA2() + novaNota.notaA3();
            String raStr = String.valueOf(alunoSelecionado.ra());
            String chaveTurma = raStr + "-" + alunoSelecionado.turma();
            consumeNotes.consumeAddNotes(raStr, novaNota);
            for(Map.Entry<String, Notes> entry : consumeDbNotes.listConsume().entrySet()) {
                if (entry.getKey().equals(chaveTurma)) {
                    Notes nota = entry.getValue();
                    iNotesDb.updateNotes(chaveTurma, nota.notaA1(), nota.notaA2(), nota.notaA3(), (nota.notaA1() + nota.notaA2() + nota.notaA3()), nota.getStatus());
                    Notas.getNotasPorAluno().remove(chaveTurma);
                    Notas.setNotasPorAluno(consumeDbNotes.listConsume());
                    ObservableList<Notes> notasObs = FXCollections.observableArrayList(Notas.getNotasPorAluno().values());
                    Notas.setNotasObservable(notasObs);
                    break;
                }else{
                    consumeDbNotes.insertConsume(chaveTurma, novaNota.notaA1(), novaNota.notaA2(), novaNota.notaA3(), soma, novaNota.getStatus());
                }
            }
            cadastrarNotaA1.clear();
            cadastrarNotaA2.clear();
            cadastrarNotaA3.clear();
            Notas.setNotasPorAluno(consumeDbNotes.listConsume());
            ObservableList<Notes> notasObs = FXCollections.observableArrayList(Notas.getNotasPorAluno().values());
            Notas.setNotasObservable(notasObs);

            exibirNotasDoAluno(alunoSelecionado);

            alertsClass.alertInformation("Cadastro de notas", "Notas cadastradas com sucesso para o aluno: " + alunoSelecionado.nome(), "Cadastro concluído com sucesso");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Erro: Insira apenas números válidos.");
        }
    }

    @FXML
    private void exibirNotasDoAluno(Student aluno) {
        Notes notas = Notas.getNotaPorAluno(aluno.ra()+"-"+aluno.turma());
        if (notas != null) {
            cadastrarNotaA1.setText(String.valueOf(notas.notaA1()));
            cadastrarNotaA2.setText(String.valueOf(notas.notaA2()));
            cadastrarNotaA3.setText(String.valueOf(notas.notaA3()));
        } else {
            cadastrarNotaA1.clear();
            cadastrarNotaA2.clear();
            cadastrarNotaA3.clear();
        }
    }
}