package br.com.projetoa3.gui.main;
import br.com.projetoa3.bancodedados.*;
import br.com.projetoa3.bancodedados.consurmers.ConsumerDbStudent;
import br.com.projetoa3.modelo.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class mainFx extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root,460 , 510);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Projeto A3 - Lista de Notas e Presenças");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        ProfessorCrud professorCrud = new ProfessorCrud();
        professorCrud.criarTabelaProfessores();
        Professor.setProfessorLista(professorCrud.listarProfessores());

        TurmaCrud turmasCrud = new TurmaCrud();
        turmasCrud.criarTabelaTurmas();
        Turmas.setTurmas(turmasCrud.listarTurmas());
        for (Map.Entry<String, Turmas> entry : Turmas.getTurmas().entrySet()) {
            Turmas.getTurmasObservable().add(entry.getValue());
        }

        ServiceDBStudent serviceDBStudent = new AlunosCrud();
        ConsumerDbStudent consumerDbStudent = new ConsumerDbStudent(serviceDBStudent);
        consumerDbStudent.createConsume();
        Alunos.setLista(alunosCrud.listarAlunos());
        for (Map.Entry<String, Alunos> entry2 : Alunos.getLista().entrySet()) {
            Alunos.getListaObservable().add(entry2.getValue());
        }

        NotasCrud notasCrud = new NotasCrud();
        notasCrud.criarTabelaNotas();
        Notas.setNotasPorAluno(notasCrud.listarNotas());
        for (Map.Entry<String, Notas> entry3 : Notas.getNotasPorAluno().entrySet()) {
            Notas.getNotasObservable().add(entry3.getValue());
        }

        PresencaCrud presencaCrud = new PresencaCrud();
        presencaCrud.criarTabelas();
        ListaPresenca.setPresencas(presencaCrud.listarPresencas());
        launch(args);
    }
}
