package br.com.projetoa3.gui.validations;

import br.com.projetoa3.gui.alerts.AlertsClass;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.consumersmodel.ConsumeStudent;
import br.com.projetoa3.modelo.interfaces.IStudent;

public class ValidationsRemove {
    public static void validationsRa(String ra){
        IStudent iStudent = new Alunos();
        ConsumeStudent consumeStudent = new ConsumeStudent(iStudent);
        AlertsClass alertsClass = new AlertsClass();
        if(!ra.matches("\\d+") || ra.length() != 10){
            alertsClass.alertInformation("Verificação de RA","Por favor, digite o RA usando 10 números.", "Erro na verificação" );
            throw new RuntimeException("Por favor, digite o RA usando 10 números.");
        }

        if(!consumeStudent.consumeList().containsKey(ra)){
            alertsClass.alertInformation("Falha ao remover aluno", "Aluno não encontrado.","Erro na remover aluno.");
            throw new RuntimeException("Aluno não encontrado.");
        }
    }
}
