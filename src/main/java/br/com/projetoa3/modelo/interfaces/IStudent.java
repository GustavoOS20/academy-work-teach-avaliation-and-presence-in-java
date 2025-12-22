package br.com.projetoa3.modelo.interfaces;

import br.com.projetoa3.modelo.records.Student;

import java.util.Map;

public interface IStudent {
   void addStudents(Student student);
   void removeStudents(String ra);
   Map<String, Student> getList();
   void setList(Map<String, Student> listaAlunos);

}
