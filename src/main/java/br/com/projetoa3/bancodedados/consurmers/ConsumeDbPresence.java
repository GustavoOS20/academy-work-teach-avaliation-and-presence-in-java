package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.IPresenceDb;
import javafx.beans.property.BooleanProperty;

import java.time.LocalDate;
import java.util.Map;

public class ConsumeDbPresence {
    IPresenceDb servicePresence;
    public ConsumeDbPresence(IPresenceDb servicePresence){
        this.servicePresence = servicePresence;
    }

    public void createConsume(){
        this.servicePresence.createTable();
    }

    public void insertPresence(Long id, LocalDate data, boolean presente){
        this.servicePresence.insertPresence(id, data, presente);
    }

    public Map<LocalDate, Map<Long, BooleanProperty>> listConsume(){
        return this.servicePresence.listPresence();
    }

    public void updatePresence(Long id, LocalDate data, boolean presente){
        this.servicePresence.updatePresence(id, data, presente);
    }

    public void deleteConsumer(String id){
        this.servicePresence.delete(id);
    }
}
