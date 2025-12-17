package br.com.projetoa3.modelo.consumersmodel;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.interfaces.ServiceStudent;

public class ConsumeStudent {
    ServiceStudent serviceStudent;
    public ConsumeStudent(ServiceStudent serviceStudent){
        this.serviceStudent = serviceStudent;
    }
    public void consumeAdd(Alunos aluno){
        this.serviceStudent.addStudents(aluno);
    }
}
