package br.com.projetoa3.src.gui.validations;

import br.com.projetoa3.src.bancodedados.NotesDbServiceDb;
import br.com.projetoa3.src.bancodedados.consurmers.ConsumeDbNotes;
import br.com.projetoa3.src.bancodedados.interfacedb.INotesDb;
import br.com.projetoa3.src.gui.alerts.AlertsClass;
import br.com.projetoa3.src.modelo.records.Notes;
import javafx.scene.control.TextField;

import java.util.Map;

public class ValidationsAddNotes {
    public static void validationsAddNotes(int notaA1, int notaA2, int notaA3){
        AlertsClass alertsClass = new AlertsClass();
        if (notaA1 < 0 || notaA2 < 0 || notaA3 < 0 || notaA1 > 30 || notaA2 > 30 || notaA3 > 40) {
            alertsClass.alertInformation(
                    "Erro na Validação",
                    "As notas A1 e A2 devem estar entre 0 e 30, e A3 deve estar entre 0 e 40.",
                    "Validação de Notas");
            throw new RuntimeException("As notas A1 e A2 devem estar entre 0 e 30, e A3 deve estar entre 0 e 40.");
        }
    }

    public static void validationFields(TextField cadastrarNotaA1, TextField cadastrarNotaA2, TextField cadastrarNotaA3){
        if(cadastrarNotaA1.getText().isEmpty() || cadastrarNotaA2.getText().isEmpty() || cadastrarNotaA3.getText().isEmpty()){
            AlertsClass alertsClass = new AlertsClass();
            alertsClass.alertInformation(
                    "Erro na validação",
                    "Por favor, preencha todos os campos de notas.",
                    "Cadastro de Notas");
            throw new RuntimeException("Por favor, preencha os campos de notas.");
        }
    }

    public static boolean verificarSeExisteNota(String ra, String turma){
        boolean existeNota = false;
        INotesDb notesDb = new NotesDbServiceDb();
        ConsumeDbNotes consumeDbNotes = new ConsumeDbNotes(notesDb);
        for(Map.Entry<String, Notes> entry : consumeDbNotes.listConsume().entrySet()) {
            if (entry.getKey().equals(ra + "-" + turma)) {
                existeNota = true;
                break;
            }
        }
        return existeNota;
    }
}
