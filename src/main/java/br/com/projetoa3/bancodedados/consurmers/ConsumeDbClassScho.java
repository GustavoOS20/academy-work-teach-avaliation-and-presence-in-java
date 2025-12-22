package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.IClassSchool;

public class ConsumeDbClassScho {
    IClassSchool serviceClass;
    public ConsumeDbClassScho(IClassSchool serviceClass){
        this.serviceClass = serviceClass;
    }

    public void createConsume(){
        this.serviceClass.createTable();
    }

    public void insertConsume(String numero, String nomeTurma, String professor){
        this.serviceClass.insertClass(numero, nomeTurma, professor);
    }

    public void listConsume(){
        this.serviceClass.listClass();
    }

    public void deleteConsumer(String id){
        this.serviceClass.delete(id);
    }
}