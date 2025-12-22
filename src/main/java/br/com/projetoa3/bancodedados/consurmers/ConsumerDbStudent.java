package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.IDBStudent;

public class ConsumerDbStudent {
    IDBStudent serviceDBStudent;
    public ConsumerDbStudent(IDBStudent serviceDBStudent){
        this.serviceDBStudent = serviceDBStudent;
    }

    public void createConsume(){
        this.serviceDBStudent.createTable();
    }

    public void insertConsume(Long ra, String nome, String turmaId, String professor_ra){
        this.serviceDBStudent.insert(ra, nome, turmaId, professor_ra);
    }

    public void listConsumer(){
        this.serviceDBStudent.listTables();
    }

    public void delete(String ra){
        this.serviceDBStudent.delete(ra);
    }
}
