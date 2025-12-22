package br.com.projetoa3.gui.validations;

import br.com.projetoa3.gui.alerts.AlertsClass;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.records.Teach;


public class ValidationTeacher {
    public static void validationFieldTeacher(Teach teach){
        if(teach.nome().isEmpty() || teach.ra().isEmpty() || teach.email().isEmpty() || teach.senha().isEmpty()){
            AlertsClass alertsClass = new AlertsClass();
            alertsClass.alertInformation("Cadastro de Professor","Por favor, preencha todos os campos.", "Campos obrigatórios não preenchidos!");
            throw new RuntimeException("Não pode ter campo vazio");
        }
    }

    public static void validationsRaTeacher(Teach teach){
        if(teach.ra().length() != 10){
            AlertsClass alertsClass = new AlertsClass();
            alertsClass.alertInformation("Cadastro de Professor", "RA inválido. Deve ter 10 dígitos.", "Erro no cadastro");
            throw new RuntimeException("Formato de RA invalido");
        }
    }

    public static void validationsEmailRA(Teach teach){
        if(Professor.getProfessorLista().containsKey(teach.email()) || Professor.getProfessorLista().containsKey(teach.ra())){
            AlertsClass alertsClass = new AlertsClass();
            alertsClass.alertInformation("Cadastro de Professor", "Professor já cadastrado com este email ou RA", "Erro no cadastro");
            throw new RuntimeException("Ra ou email ja cadastrado");

        }
    }
    }
