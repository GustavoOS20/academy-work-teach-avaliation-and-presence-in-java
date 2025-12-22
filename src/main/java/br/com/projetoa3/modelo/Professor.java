package br.com.projetoa3.modelo;

import br.com.projetoa3.modelo.interfaces.ITeach;
import br.com.projetoa3.modelo.records.Teach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class Professor implements ITeach {
    private static String nomeLogado;
    private static String raLogado;

    private static Map<String, Teach> professorLista = new HashMap<>();
    private static ObservableList<Teach> professorListaObservable = FXCollections.observableArrayList();



    public static void setProfessorLista(Map<String, Teach> professorLista) {
        Professor.professorLista = professorLista;
    }

    public static String getNomeLogado() {
        return nomeLogado;
    }

    public static void setNomeLogado(String nomeLogado) {
        Professor.nomeLogado = nomeLogado;
    }

    public static String getRaLogado() {
        return raLogado;
    }

    public static void setRaLogado(String raLogado) {
        Professor.raLogado = raLogado;
    }

    public static Map<String, Teach> getProfessorLista() {
        return professorLista;
    }

    @Override
    public void addTeach(Teach professor) {
        professorLista.put(professor.ra(), professor);
        professorListaObservable.add(professor);
    }
}
