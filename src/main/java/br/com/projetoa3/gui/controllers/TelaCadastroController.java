package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.StudentServiceDb;
import br.com.projetoa3.bancodedados.consurmers.ConsumerDbStudent;
import br.com.projetoa3.bancodedados.interfacedb.IDBStudent;
import br.com.projetoa3.gui.alerts.AlertsClass;
import br.com.projetoa3.gui.validations.ValidationsRegister;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.Turmas;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.ClassSchool;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        Alunos.getListaObservable().addListener((ListChangeListener<Student>) change2 -> {
            atualizarTurmasPorProfessor();
        });
    }

    @FXML
    private void confirmarCadastro() {
        String turma;
        IStudent iStudent = new Alunos();
        ConsumeStudent consumeStudent = new ConsumeStudent(iStudent);
        IDBStudent idbStudent = new StudentServiceDb();
        ConsumerDbStudent consumerDbStudent = new ConsumerDbStudent(idbStudent);
        AlertsClass alert = new AlertsClass();
        long raLong = Long.parseLong(cadastrarRAId1.getText().trim());
        ValidationsRegister.validationRegisterStudents(cadastrarNomeId, cadastrarRAId1, comboBoxTurma);
        for (Map.Entry<String, Student> entry3 : consumeStudent.consumeList().entrySet()) {
            if (entry3.getValue().ra() == raLong && entry3.getValue().professor().equals(Professor.getRaLogado()) && entry3.getValue().turma().equals(comboBoxTurma.getValue())) {
            alert.alertInformation("Erro no cadastro de alunos", "RA já cadastrado. Por favor, insira um RA diferente.", "Cadastro de alunos");
            return;
            }
        }
        consumeStudent.consumeAddStu(new Student(cadastrarNomeId.getText().trim(), raLong, comboBoxTurma.getValue(), Professor.getRaLogado()));

        for (Map.Entry<String, Student> entry3 : consumeStudent.consumeList().entrySet()) {
            consumerDbStudent.insertConsume(entry3.getValue().ra(), entry3.getValue().nome(), entry3.getValue().turma(), entry3.getValue().professor());
            turma = entry3.getValue().turma();
            alert.alertInformation(
                    "Aluno cadastrado",
                    "Clique em OK para continuar.",
                    "Aluno cadastrado com sucesso!\\nNome: " + cadastrarNomeId.getText() + "\nRA: " + cadastrarRAId1.getText() + "\nTurma: " + turma);
        }
        cadastrarNomeId.clear();
        cadastrarRAId1.clear();
    }

    private void atualizarTurmasPorProfessor() {
        ObservableList<String> turmasFiltPorProfessor = FXCollections.observableArrayList();
        for (ClassSchool turma : Turmas.getTurmasObservable()) {
            if (turma.professor().equals(Professor.getRaLogado())) {
                turmasFiltPorProfessor.add(turma.nome());
            }
        }
        comboBoxTurma.getItems().clear();
        comboBoxTurma.getItems().addAll(turmasFiltPorProfessor);
    }
}