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
                    String raa = newValue.replaceAll("[^0-9]", "");
                    String raaFormat = raa.length() > 10 ? raa.substring(0, 10) : raa;
                    System.out.println(raaFormat);
                    long ra = Long.parseLong(raaFormat);
                    consumeStudent.consumeList().values().forEach(c -> {
                        if(c.ra() == ra) {
                            String turma = c.turma();
                            Notes nota = Notas.getNotaPorAluno(ra + "-" + turma);
                            if (nota != null) {
                                listaNotasId.getItems().setAll(
                                        "A1: " + nota.notaA1(),
                                        "A2: " + nota.notaA2(),
                                        "A3: " + nota.notaA3(),
                                        "Soma: " + (nota.notaA1() + nota.notaA2() + nota.notaA3()),
                                        "Status: " + nota.getStatus()
                                );
                            } else {
                                listaNotasId.getItems().setAll("Sem notas cadastradas");
                                System.out.println(" - " + nota.notaA1() + " - " + nota.notaA2() + " - " + nota.notaA3());
                            }
                        }
                    });

                } catch (NumberFormatException e) {
                    listaNotasId.getItems().setAll("Erro ao ler RA");
                    throw new RuntimeException();
                }
            }
        });
    }
}
