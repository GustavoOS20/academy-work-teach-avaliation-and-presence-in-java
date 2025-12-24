package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.ITeacherDb;
import br.com.projetoa3.modelo.records.Teach;

import java.util.Map;

public class ConsumeDbTeacher {
    ITeacherDb serviceTeacher;
    public ConsumeDbTeacher(ITeacherDb serviceTeacher){
        this.serviceTeacher = serviceTeacher;
    }

    public void createConsume(){
        this.serviceTeacher.createTable();
    }

    public void insertConsume(String ra, String nome, String email, String senha){
        this.serviceTeacher.insert(ra, nome, email, senha);
    }

    public Map<String, Teach> listConsume(){
        return this.serviceTeacher.listTeachers();
    }

    public void deleteConsume(String ra){
        this.serviceTeacher.delete(ra);
    }
}
