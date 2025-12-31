package br.com.projetoa3.bancodedados.interfacedb;

import br.com.projetoa3.modelo.records.PresenceList;
import javafx.beans.property.BooleanProperty;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Predicate;

public interface IPresenceDb extends IDatabase{
    void insertPresence(Long id, LocalDate data, boolean presente, String turma, String professor);
    Map<LocalDate, Map<Long, PresenceList>> listPresence();
    void updatePresence(Long id, LocalDate data, boolean presente, String professor);
}
