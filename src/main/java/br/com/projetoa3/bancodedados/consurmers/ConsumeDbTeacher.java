package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.ITeacher;

public class ConsumeDbTeacher {
    ITeacher serviceTeacher;
    public ConsumeDbTeacher(ITeacher serviceTeacher){
        this.serviceTeacher = serviceTeacher;
    }

    public void createConsume(){
        this.serviceTeacher.createTable();
    }

    public void insertConsume(String ra, String nome, String email, String senha){
        this.serviceTeacher.insert(ra, nome, email, senha);
    }

    public void listConsume(){
        this.serviceTeacher.listTeachers();
    }

    public void deleteConsume(String ra){
        this.serviceTeacher.delete(ra);
    }
}
