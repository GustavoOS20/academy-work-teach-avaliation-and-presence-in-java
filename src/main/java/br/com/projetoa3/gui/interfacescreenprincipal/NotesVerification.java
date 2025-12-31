package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.records.Notes;
import br.com.projetoa3.modelo.records.Student;
import javafx.scene.control.ListView;

import java.util.Map;

public class NotesVerification {
    public static void notesVer(ListView<String> listaAlunosId, ListView<String> listaNotasId, ConsumeStudent consumeStudent) {
        listaAlunosId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            listaNotasId.getItems().clear();
            if (newValue != null) {
                try {
                    listaNotasId.refresh();
                    long ra = Long.parseLong(newValue);
                    for (Map.Entry<String, Student> entry : consumeStudent.consumeList().entrySet()) {
                        if (entry.getValue().ra() == ra) {
                            String turma = entry.getValue().turma();

                            Notes nota = Notas.getNotaPorAluno(ra + "-" + turma);
                            if (nota != null) {
                                listaNotasId.getItems().setAll(
                                        "A1: " + nota.notaA1(),
                                        "A2: " + nota.notaA2(),
                                        "A3: " + nota.notaA3(),
                                        "Soma: " + nota.notaA1() + nota.notaA2() + nota.notaA3(),
                                        "Status: " + nota.getStatus()
                                );
                            }
                        } else {
                            listaNotasId.getItems().setAll("Sem notas cadastradas");
                        }
                    }

                } catch (NumberFormatException e) {
                    listaNotasId.getItems().setAll("Erro ao ler RA");
                }
            }
        });
    }
}
