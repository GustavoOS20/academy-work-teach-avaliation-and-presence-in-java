package br.com.projetoa3.modelo;

import java.util.Map;
import java.util.HashMap;

import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Alunos implements IStudent {
    private static Map<String, Student> lista = new HashMap<>();
    private static ObservableList<Student> listaObservable = FXCollections.observableArrayList();

    @Override
    public void setList(Map<String, Student> listaAlunos) {
        lista = listaAlunos;
    }

    public static void setListaObservable(ObservableList<Student> listaObs) {
        listaObservable = listaObs;
    }

    @Override
    public Map<String, Student> getList() {
        return this.lista;
    }

    public static ObservableList<Student> getListaObservable() {
        return listaObservable;
    }

    @Override
    public void addStudents(Student student) {
        String raString = String.valueOf(student.ra());
        lista.put(raString, student);
        listaObservable.add(student);
    }

    @Override
    public void removeStudents(String ra) {
        Student student = lista.remove(ra);
        if (student != null) {
            listaObservable.remove(student);
        }
    }



}