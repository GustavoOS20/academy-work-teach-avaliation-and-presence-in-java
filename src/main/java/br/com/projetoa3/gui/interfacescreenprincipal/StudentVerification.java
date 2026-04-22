package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class StudentVerification {
    public static void atualizarAluno(ListView<String> listaAlunosId){
        ObservableList<String> alunosFiltPorProfessor = FXCollections.observableArrayList();
        Alunos.getListaObservable().stream().filter(aluno -> aluno.professor().equals(Professor.getRaLogado()))
                .forEach(aluno -> alunosFiltPorProfessor.add(aluno.toString()));
        listaAlunosId.setItems(alunosFiltPorProfessor);
    }
}
