package br.com.projetoa3.src.gui.controllers;

import br.com.projetoa3.src.bancodedados.ClassSchServiceDbDb;
import br.com.projetoa3.src.bancodedados.consurmers.ConsumeDbClassScho;
import br.com.projetoa3.src.bancodedados.interfacedb.IClassSchoolDb;
import br.com.projetoa3.src.gui.alerts.AlertsClass;
import br.com.projetoa3.src.gui.validations.ValidationsClassSchool;
import br.com.projetoa3.src.modelo.Professor;
import br.com.projetoa3.src.modelo.Turmas;
import br.com.projetoa3.src.modelo.consumersmodel.ConsumeClass;
import br.com.projetoa3.src.modelo.interfaces.IClass;
import br.com.projetoa3.src.modelo.records.ClassSchool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class TelaAdicionarTurmaController implements Initializable {

    @FXML
    private TextField TurmaDigitada;

    @FXML
    private Button cadastrarTurma;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cadastrarTurma.setOnAction(event -> {
            String idAleatorio = UUID.randomUUID().toString();
            ValidationsClassSchool.validationsClass(TurmaDigitada);
            IClass iClass = new Turmas();
            ClassSchool classSchool = new ClassSchool(TurmaDigitada.getText(), Professor.getRaLogado());
            ConsumeClass consumeClass = new ConsumeClass(iClass);
            IClassSchoolDb iClassSchoolDb = new ClassSchServiceDbDb();
            ConsumeDbClassScho consumeDbClassScho = new ConsumeDbClassScho(iClassSchoolDb);
            consumeClass.consumeAddClass(idAleatorio, classSchool);
            consumeDbClassScho.insertConsume(idAleatorio, classSchool.nome(), classSchool.professor());
            AlertsClass alertsClass = new AlertsClass();
            alertsClass.alertInformation("Cadastro de turmas", "ID: " + idAleatorio + "\nNome da Turma: " + TurmaDigitada.getText(), "Turma cadastrada com sucesso!");
            });
        }
    }