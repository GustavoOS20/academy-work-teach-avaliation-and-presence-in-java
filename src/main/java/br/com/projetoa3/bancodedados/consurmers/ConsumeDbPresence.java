package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.IPresence;

import java.time.LocalDate;

public class ConsumeDbPresence {
    IPresence servicePresence;
    public ConsumeDbPresence(IPresence servicePresence){
        this.servicePresence = servicePresence;
    }

    public void createConsume(){
        this.servicePresence.createTable();
    }

    public void insertPresence(Long id, LocalDate data, boolean presente){
        this.servicePresence.insertPresence(id, data, presente);
    }

    public void listConsume(){
        this.servicePresence.listPresence();
    }

    public void updatePresence(Long id, LocalDate data, boolean presente){
        this.servicePresence.updatePresence(id, data, presente);
    }

    public void deleteConsumer(String id){
        this.servicePresence.delete(id);
    }
}
