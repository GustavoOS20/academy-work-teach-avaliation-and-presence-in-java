package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ClassVerification {
    private static final ObservableList<String> alunosFiltrados = FXCollections.observableArrayList();
    private static final ObservableList<String> alunoPresense = FXCollections.observableArrayList();
    private static String alunoTurma(Student aluno){
        return aluno.nome()  + " | RA: " + aluno.ra() + " | Turma: " + aluno.turma();
    }

    @FXML
    public static void filtrarAlunosPorTurma(String turma, ListView<String> listaAlunosId, ListView<String> listaDePresenca, Menu trocarTurmaMenu) {
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.turma().equals(turma)) {
                alunosFiltrados.clear();
                alunoPresense.clear();
                alunosFiltrados.add(aluno.toString());
                alunoPresense.add(alunoTurma(aluno));
            }
        }
        listaAlunosId.setItems(alunosFiltrados);
        listaDePresenca.setItems(alunoPresense);
    }

    public static void todosAlunos(Menu trocarTurmaMenu, ListView<String> listaAlunosId, ListView<String> listaDePresenca) {
        ObservableList<MenuItem> turmaMenu = FXCollections.observableArrayList();
        MenuItem item = new MenuItem("Todas as turmas");
        turmaMenu.add(item);
        for (Student aluno : Alunos.getListaObservable()) {
            if (trocarTurmaMenu.getItems().equals(turmaMenu)) {
                alunosFiltrados.clear();
                alunoPresense.clear();
                alunosFiltrados.add(aluno.toString());
                alunoPresense.add(alunoTurma(aluno));
            }
        }
        listaAlunosId.setItems(alunosFiltrados);
        listaDePresenca.setItems(alunoPresense);
    }
}
