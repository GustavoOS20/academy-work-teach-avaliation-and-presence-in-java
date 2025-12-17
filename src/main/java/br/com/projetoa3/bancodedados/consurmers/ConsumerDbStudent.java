package br.com.projetoa3.bancodedados.consurmers;

import br.com.projetoa3.bancodedados.ServiceDBStudent;

public class ConsumerDbStudent {
    ServiceDBStudent serviceDBStudent;
    public ConsumerDbStudent(ServiceDBStudent serviceDBStudent){
        this.serviceDBStudent = serviceDBStudent;
    }

    public void createConsume(){
        this.serviceDBStudent.createTable("Student");
    }

    public void insertConsume(){
        this.serviceDBStudent.insert();
    }
}
