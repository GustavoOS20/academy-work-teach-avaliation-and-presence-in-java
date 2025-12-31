package br.com.projetoa3.bancodedados.interfacedb;

import br.com.projetoa3.modelo.records.Notes;

import java.sql.SQLException;
import java.util.Map;

public interface INotesDb extends IDatabase {
    void insert(String ra, int A1, int A2, int A3, int soma, String Status);
    Map<String, Notes> listNotes();
    void updateNotes(String idNota, int novaA1, int novaA2, int novaA3, int soma, String Status);
}
