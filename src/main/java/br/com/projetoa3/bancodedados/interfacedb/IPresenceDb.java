package br.com.projetoa3.bancodedados.interfacedb;

import javafx.beans.property.BooleanProperty;

import java.time.LocalDate;
import java.util.Map;

public interface IPresenceDb extends IDatabase{
    void insertPresence(Long id, LocalDate data, boolean presente);
    Map<LocalDate, Map<Long, BooleanProperty>> listPresence();
    void updatePresence(Long id, LocalDate data, boolean presente);
}
