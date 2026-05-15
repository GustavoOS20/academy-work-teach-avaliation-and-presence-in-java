package br.com.projetoa3.src.modelo.interfaces;

import br.com.projetoa3.src.modelo.records.ClassSchool;

import java.util.Map;

public interface IClass {
    void addClass(String numero, ClassSchool classSch);
    Map<String, ClassSchool> getClassSco();
}
