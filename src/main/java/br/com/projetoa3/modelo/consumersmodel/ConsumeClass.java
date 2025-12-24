package br.com.projetoa3.modelo.consumersmodel;

import br.com.projetoa3.modelo.interfaces.IClass;
import br.com.projetoa3.modelo.records.ClassSchool;

import java.util.Map;

public class ConsumeClass {
    IClass iClass;
    public ConsumeClass(IClass iClass){
        this.iClass = iClass;
    }

    public void consumeAddClass(String numero, ClassSchool classSch){
        this.iClass.addClass(numero, classSch);
    }

    public Map<String, ClassSchool> getClassSco() {
        return this.iClass.getClassSco();
    }
}
