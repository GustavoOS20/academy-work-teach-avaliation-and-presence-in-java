package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Turmas;
import br.com.projetoa3.modelo.records.ClassSchool;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ClassVerification {
    @FXML
    public static void filtrarAlunosPorTurma(String turma, ListView<String> listaAlunosId, ListView<String> listaDePresenca) {
        ObservableList<String> alunosFiltrados = FXCollections.observableArrayList();
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.turma().equals(turma)) {
                alunosFiltrados.add(aluno.toString());
            }
        }
        listaAlunosId.setItems(alunosFiltrados);
        listaDePresenca.setItems(alunosFiltrados);
    }
}
