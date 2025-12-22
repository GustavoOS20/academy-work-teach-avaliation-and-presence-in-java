package br.com.projetoa3.modelo.consumersmodel;

import br.com.projetoa3.modelo.interfaces.IStudent;
import br.com.projetoa3.modelo.records.Student;

import java.util.Map;

public class ConsumeStudent {
    IStudent iStudent;
    public ConsumeStudent(IStudent iStudent){
        this.iStudent = iStudent;
    }

    public void consumeAddStu(Student student){
        this.iStudent.addStudents(student);
    }

    public void consumeRemove(String ra){
        this.iStudent.removeStudents(ra);
    }

    public Map<String, Student> consumeList(){
        return this.iStudent.getList();
    }

    public void consumeSet(Map<String, Student> list){
        this.iStudent.setList(list);
    }
}
