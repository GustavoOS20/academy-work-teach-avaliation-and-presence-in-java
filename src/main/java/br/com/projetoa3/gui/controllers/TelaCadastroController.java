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
        IStudent iStudent = new Alunos();
        ConsumeStudent consumeStudent = new ConsumeStudent(iStudent);
        IDBStudent idbStudent = new StudentServiceDb();
        ConsumerDbStudent consumerDbStudent = new ConsumerDbStudent(idbStudent);
        AlertsClass alert = new AlertsClass();
        long raLong = Long.parseLong(cadastrarRAId1.getText().trim());
        ValidationsRegister.validationRegisterStudents(cadastrarNomeId, cadastrarRAId1, comboBoxTurma);
        consumeStudent.consumeList().values().forEach(student -> {
            if (student.ra() == raLong && student.professor().equals(Professor.getRaLogado()) && student.turma().equals(comboBoxTurma.getValue())) {
                alert.alertInformation("Erro no cadastro de alunos", "RA já cadastrado. Por favor, insira um RA diferente.", "Cadastro de alunos");
            }
        });
        consumeStudent.consumeAddStu(new Student(cadastrarNomeId.getText().trim(), raLong, comboBoxTurma.getValue(), Professor.getRaLogado()));
        consumeStudent.consumeList().values().forEach(student -> {
            consumerDbStudent.insertConsume(student.ra(), student.nome(), student.turma(), student.professor());
            String turmaa = student.turma();
            alert.alertInformation(
                    "Aluno cadastrado",
                    "Clique em OK para continuar.",
                    "Aluno cadastrado com sucesso!\\nNome: " + cadastrarNomeId.getText() + "\nRA: " + cadastrarRAId1.getText() + "\nTurma: " + turmaa);
        });
        cadastrarNomeId.clear();
        cadastrarRAId1.clear();
    }

    private void atualizarTurmasPorProfessor() {
        ObservableList<String> turmasFiltPorProfessor = FXCollections.observableArrayList();
        Turmas.getTurmasObservable().forEach(turms -> {
                if (turms.professor().equals(Professor.getRaLogado())) {
                    turmasFiltPorProfessor.add(turms.nome());
                }
        });
        comboBoxTurma.getItems().clear();
        comboBoxTurma.getItems().addAll(turmasFiltPorProfessor);
    }
}