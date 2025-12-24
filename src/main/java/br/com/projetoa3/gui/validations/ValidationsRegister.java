package br.com.projetoa3.gui.validations;

import br.com.projetoa3.gui.alerts.AlertsClass;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ValidationsRegister {
    public static void validationRegisterStudents( TextField cadastrarNomeID, TextField cadastraRa, ComboBox<String> comboBoxTurma){
        AlertsClass alertClass = new AlertsClass();
        if(cadastrarNomeID.getText().isEmpty() || cadastraRa.getText().isEmpty() || comboBoxTurma.getValue() == null){
            alertClass.alertInformation(
                    "Cadastro de Aluno",
                    "Por favor, preencha todos os campos.",
                    "Confirmação de cadastro");
            throw new RuntimeException("Por favor, preencha todos os campos.");
        }

        if(cadastraRa.getText().length() != 10){
            alertClass.alertInformation(
                    "Cadastro de Aluno",
                    "Ra invalido. Deve ter 10 dígitos.",
                    "Confirmação de cadastro");
            throw new RuntimeException("Ra invalido. Deve ter 10 dígitos.");
        }
    }
}
