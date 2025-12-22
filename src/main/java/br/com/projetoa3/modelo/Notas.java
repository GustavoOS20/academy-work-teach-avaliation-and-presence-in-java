package br.com.projetoa3.modelo;

import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.INotes;
import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.Notes;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;
import java.util.HashMap;

public class Notas implements INotes {
    private final static Map<String, Notes> notasPorAluno = new HashMap<>();
    private static ObservableList<Notes> notasObservable = FXCollections.observableArrayList();

    public static void setNotasObservable(ObservableList<Notes> notasObs) {
        notasObservable = notasObs;
    }

    public static void setNotasPorAluno(Map<String, Notes> notas) {
        notasPorAluno.putAll(notas);
    }

    public static ObservableList<Notes> getNotasObservable() {
        return notasObservable;
    }

    @Override
    public void addNotes(String ra, Notes note) {
        IStudent iStudent = new Alunos();
        ConsumeStudent consumeStudent = new ConsumeStudent(iStudent);
        Map< String, Student> listaAlunos = consumeStudent.consumeList();
        for(Map.Entry<String, Student> entry : listaAlunos.entrySet()) {
            if (entry.getKey().equals(ra)) {
                Student aluno = entry.getValue();
                String chaveTurma =  aluno.ra() +"-"+aluno.turma();
                notasObservable.add(note);
                notasPorAluno.put(chaveTurma, note);
        }

        }
    }

    public static Notes getNotaPorAluno(String ra) {
        return notasPorAluno.get(ra);
    }

    public static Map<String, Notes> getNotasPorAluno() {
        return notasPorAluno;
    }
}
