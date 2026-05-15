package br.com.projetoa3.src.bancodedados.interfacedb;

import br.com.projetoa3.src.modelo.records.PresenceList;

import java.time.LocalDate;
import java.util.Map;

public interface IPresenceDb extends IDatabase{
    void insertPresence(Long id, LocalDate data, boolean presente, String turma, String professor);
    Map<LocalDate, Map<Long, PresenceList>> listPresence();
    void updatePresence(Long id, LocalDate data, boolean presente, String professor);
}
