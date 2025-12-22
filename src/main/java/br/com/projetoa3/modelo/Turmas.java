package br.com.projetoa3.modelo;

import br.com.projetoa3.modelo.interfaces.IClass;
import br.com.projetoa3.modelo.records.ClassSchool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class Turmas implements IClass {
    private static Map<String, ClassSchool> turmas = new HashMap<>();
    private static ObservableList<ClassSchool> turmasObservable = FXCollections.observableArrayList();

    public static void setTurmas(Map<String, ClassSchool> turmas) {
        Turmas.turmas = turmas;
    }

    public static Map<String, ClassSchool> getTurmas() {
        return turmas;
    }

    public static ObservableList<ClassSchool> getTurmasObservable() {
        return turmasObservable;
    }

    @Override
    public void addClass(String numero, ClassSchool classSch) {
        if (turmas.containsKey(numero)) {
            System.out.println("erro");
        } else {
            turmas.put(numero, classSch);
            turmasObservable.add(classSch);
        }
    }
}
