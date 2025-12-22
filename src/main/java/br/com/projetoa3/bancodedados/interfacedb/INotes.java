package br.com.projetoa3.bancodedados.interfacedb;

import br.com.projetoa3.modelo.records.Notes;

import java.util.Map;

public interface INotes extends IDatabase {
    void insert(Long ra, int A1, int A2, int A3, int soma, String Status);
    Map<Long, Notes> listNotes();
}
