package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.IDBStudent;
import br.com.projetoa3.modelo.records.Student;

import java.util.Map;

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

    public Map<String, Student> listConsumer(){
       return this.serviceDBStudent.listTables();
    }

    public void delete(String ra){
        this.serviceDBStudent.delete(ra);
    }
}
