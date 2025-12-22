package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.StudentServiceDb;
import br.com.projetoa3.bancodedados.NotesDbServiceDb;
import br.com.projetoa3.bancodedados.PresenceDbServiceDb;
import br.com.projetoa3.bancodedados.consurmers.ConsumeDbNotes;
import br.com.projetoa3.bancodedados.consurmers.ConsumeDbPresence;
import br.com.projetoa3.bancodedados.consurmers.ConsumerDbStudent;
import br.com.projetoa3.bancodedados.interfacedb.IDBStudent;
import br.com.projetoa3.bancodedados.interfacedb.INotesDb;
import br.com.projetoa3.bancodedados.interfacedb.IPresenceDb;
import br.com.projetoa3.gui.alerts.AlertsClass;
import br.com.projetoa3.gui.validations.ValidationsRemove;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.IStudent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import br.com.projetoa3.modelo.Alunos;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoverAlunoControllers implements Initializable {

    @FXML
    private Button confirmarRemorcao;

    @FXML
    private TextField removerRA;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmarRemorcao.setOnAction(event -> {
            String ra = removerRA.getText().trim();
            ValidationsRemove.validationsRa(ra);
            IDBStudent student = new StudentServiceDb();
            ConsumerDbStudent consumerDbStudent = new ConsumerDbStudent(student);
            INotesDb notesDb = new NotesDbServiceDb();
            ConsumeDbNotes consumeDbNotes = new ConsumeDbNotes(notesDb);
            IPresenceDb presenceDb = new PresenceDbServiceDb();
            ConsumeDbPresence consumeDbPresence = new ConsumeDbPresence(presenceDb);
            IStudent student1 = new Alunos();
            ConsumeStudent  consumeStudent = new ConsumeStudent(student1);
            consumeStudent.consumeRemove(ra);
            consumerDbStudent.delete(ra);
            consumeDbNotes.deleteConsume(ra);
            consumeDbPresence.deleteConsumer(ra);
            AlertsClass alertsClass = new AlertsClass();
            alertsClass.alertInformation("Aluno removido", "Aluno removido com sucesso", "remover alunos");
        });
    }
}