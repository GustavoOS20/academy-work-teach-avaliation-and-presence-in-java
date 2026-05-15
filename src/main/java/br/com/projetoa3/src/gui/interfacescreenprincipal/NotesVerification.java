package br.com.projetoa3.src.gui.interfacescreenprincipal;

import br.com.projetoa3.src.modelo.Notas;
import br.com.projetoa3.src.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.src.modelo.records.Notes;
import javafx.scene.control.ListView;

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
