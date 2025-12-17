package br.com.projetoa3.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Notas {
    private final static Map<String, Notas> notasPorAluno = new HashMap<>();
    private static ObservableList<Notas> notasObservable = FXCollections.observableArrayList();


    private final int notaA1;
    private final int notaA2;
    private final int notaA3;
    private final int somaNota;
    private final String status;


    public Notas(int notaA1, int notaA2, int notaA3) {
        this.notaA1 = notaA1;
        this.notaA2 = notaA2;
        this.notaA3 = notaA3;
        this.somaNota = notaA1 + notaA2 + notaA3;
        this.status = (somaNota >= 70) ? "Aprovado" : "Reprovado";
    }

    public static void setNotasObservable(ObservableList<Notas> notasObs) {
        notasObservable = notasObs;
    }

    public static void setNotasPorAluno(Map<String, Notas> notas) {
        notasPorAluno.putAll(notas);
    }

    public static ObservableList<Notas> getNotasObservable() {
        return notasObservable;
    }

    public static void adicionarNota(String ra, Notas nota) {
        for(Map.Entry<String, Alunos> entry : Alunos.getLista().entrySet()) {
            if (entry.getKey().equals(ra)) {
                Alunos aluno = entry.getValue();
                String chaveTurma =  aluno.getRa() +"-"+aluno.getTurma();
                notasObservable.add(nota);
                notasPorAluno.put(chaveTurma, nota);
        }

        }
    }

    public static Notas getNotaPorAluno(String ra) {
        return notasPorAluno.get(ra);
    }

    public static Map<String, Notas> getNotasPorAluno() {
        return notasPorAluno;
    }

    public int getNotaA1() {
        return notaA1;
    }

    public int getNotaA2() {
        return notaA2;
    }

    public int getNotaA3() {
        return notaA3;
    }

    public int getSomaNota() {
        return somaNota;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return "Notas{" +
                "notaA1=" + notaA1 +
                ", notaA2=" + notaA2 +
                ", notaA3=" + notaA3 +
                ", somaNota=" + somaNota +
                ", status='" + status + '\'' +
                '}';
    }
}
