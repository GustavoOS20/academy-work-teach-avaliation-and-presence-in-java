package br.com.projetoa3.gui.main;
import br.com.projetoa3.bancodedados.*;
import br.com.projetoa3.bancodedados.consurmers.*;
import br.com.projetoa3.bancodedados.interfacedb.*;
import br.com.projetoa3.gui.fxmlloader.FxmlLoader;
import br.com.projetoa3.modelo.*;
import br.com.projetoa3.modelo.consumersmodel.ConsumeClass;
import br.com.projetoa3.modelo.consumersmodel.ConsumeNotes;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.IClass;
import br.com.projetoa3.modelo.interfaces.INotes;
import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.ClassSchool;
import br.com.projetoa3.modelo.records.Notes;
import br.com.projetoa3.modelo.records.Student;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class mainFx extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FxmlLoader loader = new FxmlLoader();
        loader.fxmlLogin(primaryStage);
    }

    public static void main(String[] args) {
        ITeacherDb iTeacherDb = new TeacherServiceDb();
        ConsumeDbTeacher consumeDbTeacher = new ConsumeDbTeacher(iTeacherDb);
        IClassSchoolDb iClassSchoolDb = new ClassSchServiceDbDb();
        ConsumeDbClassScho consumeDbClassScho = new ConsumeDbClassScho(iClassSchoolDb);
        IClass iclass = new Turmas();
        ConsumeClass consumeClassScho = new ConsumeClass(iclass);
        IDBStudent serviceDBStudent = new StudentServiceDb();
        ConsumerDbStudent consumerDbStudent = new ConsumerDbStudent(serviceDBStudent);
        IStudent iStudent = new Alunos();
        ConsumeStudent consumeStudent = new ConsumeStudent(iStudent);
        INotes iNotes = new Notas();
        ConsumeNotes consumeNotes = new ConsumeNotes(iNotes);
        INotesDb iNotesDb = new NotesDbServiceDb();
        ConsumeDbNotes consumeDbNotes = new ConsumeDbNotes(iNotesDb);
        IPresenceDb iPresenceDb = new PresenceDbServiceDb();
        ConsumeDbPresence consumeDbPresence = new ConsumeDbPresence(iPresenceDb);

        consumeDbTeacher.createConsume();
        Professor.setProfessorLista(consumeDbTeacher.listConsume());


        consumeDbClassScho.createConsume();
        Turmas.setTurmas(consumeDbClassScho.listConsume());
        for (Map.Entry<String, ClassSchool> entry : consumeClassScho.getClassSco().entrySet()) {
            Turmas.getTurmasObservable().add(entry.getValue());
        }

        consumerDbStudent.createConsume();
        consumeStudent.consumeSet(consumerDbStudent.listConsumer());
        for (Map.Entry<String, Student> entry2 : consumeStudent.consumeList().entrySet()) {
            Alunos.getListaObservable().add(entry2.getValue());
        }

        consumeDbNotes.createConsume();
        Notas.setNotasPorAluno(consumeDbNotes.listConsume());
        for (Map.Entry<String, Notes> entry3 : Notas.getNotasPorAluno().entrySet()) {
            Notas.getNotasObservable().add(entry3.getValue());
        }

        PresenceDbServiceDb presenceService = new PresenceDbServiceDb();
        consumeDbPresence.createConsume();
        ListaPresenca.setPresencas(consumeDbPresence.listConsume());
        launch(args);
    }
}
