package br.com.projetoa3.src.modelo;

import br.com.projetoa3.src.modelo.records.PresenceList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ListaPresenca {
        private static Map<LocalDate, Map<Long, PresenceList>> presencas = new HashMap<>();

    public static Map<LocalDate, Map<Long, PresenceList>> getPresencas() {
        return presencas;
    }

    public static void setPresencas(Map<LocalDate, Map<Long, PresenceList>> presencas) {
        ListaPresenca.presencas = presencas;
    }
}