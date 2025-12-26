package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class StudentVerification {
    public static void atualizarAluno(ListView<String> listaAlunosId){
        ObservableList<String> alunosFiltPorProfessor = FXCollections.observableArrayList();
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.professor().equals(Professor.getRaLogado())) {
                alunosFiltPorProfessor.add(aluno.toString());
            }
        }
        listaAlunosId.setItems(alunosFiltPorProfessor);
    }
}
