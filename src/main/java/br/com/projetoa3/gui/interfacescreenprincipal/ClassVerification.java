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
    public static void filtrarAlunosPorTurma(String turma, ListView<String> listaAlunosId, ListView<String> listaDePresenca, String professorLogado) {
        ObservableList<String> alunosFilt = FXCollections.observableArrayList();
        ObservableList<String> alunoPrese = FXCollections.observableArrayList();
        Alunos.getListaObservable().forEach(aluno -> {
            if(aluno.turma().equals(turma) && aluno.professor().equals(professorLogado)){
                alunosFiltrados.clear();
                alunoPresense.clear();
                alunosFilt.add(aluno.toString());
                alunoPrese.add(alunoTurma(aluno));
            }
        });
        alunosFiltrados.setAll(alunosFilt);
        alunoPresense.setAll(alunoPrese);
        listaAlunosId.setItems(alunosFiltrados);
        listaDePresenca.setItems(alunoPresense);
    }

    public static void todosAlunos(ListView<String> listaAlunosId, ListView<String> listaDePresenca) {
       ObservableList<String> alunosFilt = FXCollections.observableArrayList();
       ObservableList<String> alunoPrese = FXCollections.observableArrayList();
        Alunos.getListaObservable().forEach(aluno -> {
            alunosFiltrados.clear();
            alunoPresense.clear();
            alunosFilt.add(aluno.toString());
            alunoPrese.add(alunoTurma(aluno));
        });
        alunosFiltrados.setAll(alunosFilt);
        alunoPresense.setAll(alunoPrese);
        listaAlunosId.setItems(alunosFiltrados);
        listaDePresenca.setItems(alunoPresense);
    }
}
