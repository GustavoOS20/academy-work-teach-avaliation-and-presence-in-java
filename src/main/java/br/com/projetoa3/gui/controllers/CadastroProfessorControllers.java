package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.TeacherServiceDb;
import br.com.projetoa3.bancodedados.consurmers.ConsumeDbTeacher;
import br.com.projetoa3.bancodedados.interfacedb.ITeacherDb;
import br.com.projetoa3.gui.alerts.AlertsClass;
import br.com.projetoa3.gui.validations.ValidationTeacher;
import br.com.projetoa3.gui.validations.ValidationsEmailPassword;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.consumersmodel.ConsumeTeach;
import br.com.projetoa3.modelo.interfaces.ITeach;
import br.com.projetoa3.modelo.records.Teach;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroProfessorControllers implements Initializable {

    @FXML
    private Button BotaoCadastrar;

    @FXML
    private TextField Email;

    @FXML
    private TextField Nome;

    @FXML
    private TextField RA;

    @FXML
    private PasswordField Senha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BotaoCadastrar.setOnAction(event -> {
            if(salvarDados()){
                ITeacherDb teacherDb = new TeacherServiceDb();
                ConsumeDbTeacher consumeDbTeacher = new ConsumeDbTeacher(teacherDb);
                consumeDbTeacher.insertConsume(RA.getText(), Nome.getText(), Email.getText(), Senha.getText());
            }
        });

    }

    @FXML
    protected Boolean salvarDados() {
        AlertsClass alertsClass = new AlertsClass();
        boolean salvar = false;
        Teach teach = new Teach(Nome.getText(), RA.getText(), Email.getText(), Senha.getText());
        ValidationTeacher.validationFieldTeacher(teach);
        ValidationTeacher.validationsRaTeacher(teach);
        ValidationTeacher.validationsEmailRA(teach);
        ValidationsEmailPassword.validationsEmail(teach.email());

        if (ValidationsEmailPassword.validationsPassword(teach.senha())) {
            ITeach teachModel = new Professor();
            ConsumeTeach consumeTeach = new ConsumeTeach(teachModel);
            consumeTeach.consumeAddTeach(teach);
            alertsClass.alertInformationWithButton(
                    "Cadastro de Professor",
                    "Clique em OK para ir a tela de login.",
                    "Professor cadastrado com sucesso!",
                    BotaoCadastrar);
            salvar = true;
        } else {
            alertsClass.alertInformation("Cadastro de Professor", "Senha invalida", "Erro no Cadastro");
        }
        return salvar;
    }
}