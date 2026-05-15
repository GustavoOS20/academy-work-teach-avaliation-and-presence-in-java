package br.com.projetoa3.src.gui.validations;

import br.com.projetoa3.src.gui.alerts.AlertsClass;
import br.com.projetoa3.src.modelo.Professor;
import br.com.projetoa3.src.modelo.Turmas;
import br.com.projetoa3.src.modelo.records.ClassSchool;
import javafx.scene.control.TextField;

import java.util.Map;

public class ValidationsClassSchool {
    public static void validationsClass(TextField textField) {
        Turmas turmas = new Turmas();
        AlertsClass alert = new AlertsClass();
        if(textField.getText().isEmpty()){
            alert.alertInformation("Cadastro de Turma", "Por favor, preencha o campo da turma.", "Erro no cadastro");
            throw new RuntimeException("Preencha o campo da turma.");
        }
        for(Map.Entry<String, ClassSchool> entry : turmas.getClassSco().entrySet()) {
            if (textField.getText().equalsIgnoreCase(entry.getValue().nome()) && entry.getValue().professor().equals(Professor.getRaLogado())) {
                alert.alertInformation("Cadastro de Turma", "Turma já cadastrada para este professor.", "Erro no cadastro");
                throw new RuntimeException("Preencha o campo da turma.");
            }
        }
    }
}
