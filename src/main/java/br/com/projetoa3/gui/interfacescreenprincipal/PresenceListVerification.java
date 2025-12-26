package br.com.projetoa3.gui.interfacescreenprincipal;

import br.com.projetoa3.bancodedados.PresenceDbServiceDb;
import br.com.projetoa3.bancodedados.consurmers.ConsumeDbPresence;
import br.com.projetoa3.bancodedados.interfacedb.IPresenceDb;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.ListaPresenca;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.records.Notes;
import br.com.projetoa3.modelo.records.Student;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PresenceListVerification {

    public static void carregarPresencas(LocalDate data, ListView<String> listaDePresenca ) {
        ObservableList<String> presencasFiltradas = FXCollections.observableArrayList();
        IPresenceDb iPresenceDb = new PresenceDbServiceDb();
        ConsumeDbPresence consumeDbPresence = new ConsumeDbPresence(iPresenceDb);
        listaDePresenca.refresh();
        Map<Long, BooleanProperty> presencaData = ListaPresenca.getPresencas()
                .computeIfAbsent(data, d -> new HashMap<>());
        Alunos.getListaObservable().forEach(aluno -> {
            BooleanProperty prop = presencaData.computeIfAbsent(aluno.ra(), ra -> new SimpleBooleanProperty(false));
            prop.addListener((obs, oldVal, newVal) -> {
                consumeDbPresence.insertPresence(aluno.ra(), data, newVal);
            });
        });

        Alunos.getListaObservable().forEach(aluno -> {
            consumeDbPresence.createConsume();
            for (Map.Entry<Long, BooleanProperty> entry : presencaData.entrySet()) {
                Long ra = entry.getKey();
                BooleanProperty presente = entry.getValue();
                consumeDbPresence.insertPresence(ra, data, presente.get());
            }
        });

        for (Student aluno : Alunos.getListaObservable()) {
            if (aluno.professor().equals(Professor.getRaLogado())) {
                presencasFiltradas.add(aluno.nome() + " | RA: " + aluno.ra() + " | Turma: " + aluno.turma());
            }
        }
        listaDePresenca.setItems(presencasFiltradas);
        listaDePresenca.refresh();
        listaDePresenca.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(item);
                    String[] partes = item.split("\\|");
                    String raStr = partes[1].replace("RA:", "").trim();
                    Long ra = Long.parseLong(raStr);
                    checkBox.selectedProperty().unbind();
                    checkBox.selectedProperty().bindBidirectional(presencaData.get(ra));
                    setGraphic(checkBox);
                }
            }
        });
    }
}
