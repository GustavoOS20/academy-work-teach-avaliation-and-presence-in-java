package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.INotes;

public class ConsumeDbNotes {
    INotes serviceNotes;

    public ConsumeDbNotes(INotes serviceNotes){
        this.serviceNotes = serviceNotes;
    }

    public void createConsume(){
        this.serviceNotes.createTable();
    }

    public void insertConsume(Long ra, int A1, int A2, int A3, int soma, String status ){
        this.serviceNotes.insert(ra, A1, A2, A3, soma, status);
    }

    public void listConsume(){
        this.serviceNotes.listNotes();
    }

    public void deleteConsume(String ra){
        this.serviceNotes.delete(ra);
    }

}
