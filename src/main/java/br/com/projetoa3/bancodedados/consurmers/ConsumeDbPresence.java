package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.interfacedb.IPresenceDb;
import br.com.projetoa3.modelo.records.PresenceList;

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

    public void insertPresence(Long id, LocalDate data, boolean presente, String turma, String professor){
        this.servicePresence.insertPresence(id, data, presente, turma, professor);
    }

    public Map<LocalDate, Map<Long, PresenceList>> listConsume(){
        return this.servicePresence.listPresence();
    }

    public void updatePresence(Long id, LocalDate data, boolean presente, String professor){
        this.servicePresence.updatePresence(id, data, presente, professor);
    }

    public void deleteConsumer(String id){
        this.servicePresence.delete(id);
    }
}
