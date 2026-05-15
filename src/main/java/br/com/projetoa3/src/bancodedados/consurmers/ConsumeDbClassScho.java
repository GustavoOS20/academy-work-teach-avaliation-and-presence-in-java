package br.com.projetoa3.src.bancodedados.consurmers;

import br.com.projetoa3.src.bancodedados.interfacedb.IClassSchoolDb;
import br.com.projetoa3.src.modelo.records.ClassSchool;

import java.util.Map;

public class ConsumeDbClassScho {
    IClassSchoolDb serviceClass;
    public ConsumeDbClassScho(IClassSchoolDb serviceClass){
        this.serviceClass = serviceClass;
    }

    public void createConsume(){
        this.serviceClass.createTable();
    }

    public void insertConsume(String numero, String nomeTurma, String professor){
        this.serviceClass.insertClass(numero, nomeTurma, professor);
    }

    public Map<String, ClassSchool> listConsume(){
       return this.serviceClass.listClass();
    }

    public void deleteConsumer(String id){
        this.serviceClass.delete(id);
    }
}