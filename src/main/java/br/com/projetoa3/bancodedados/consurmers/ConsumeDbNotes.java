package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.INotesDb;
import br.com.projetoa3.modelo.records.Notes;

import java.util.Map;

public class ConsumeDbNotes {
    INotesDb serviceNotes;

    public ConsumeDbNotes(INotesDb serviceNotes){
        this.serviceNotes = serviceNotes;
    }

    public void createConsume(){
        this.serviceNotes.createTable();
    }

    public void insertConsume(String ra, int A1, int A2, int A3, int soma, String status ){
        this.serviceNotes.insert(ra, A1, A2, A3, soma, status);
    }

    public Map<String, Notes> listConsume(){
        return this.serviceNotes.listNotes();
    }

    public void deleteConsume(String ra){
        this.serviceNotes.delete(ra);
    }

}
