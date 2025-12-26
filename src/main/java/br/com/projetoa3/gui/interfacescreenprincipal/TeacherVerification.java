package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class TeacherVerification {

    public static void filtrarPresencaPorProfessor(ListView<String> listaDePresenca) {
        ObservableList<String> presencasFiltradas = FXCollections.observableArrayList();
        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.professor().equals(Professor.getRaLogado())) {
                presencasFiltradas.add(aluno.nome() + " | RA: " + aluno.ra());
            }
        }
        listaDePresenca.setItems(presencasFiltradas);
        listaDePresenca.refresh();
    }
}
