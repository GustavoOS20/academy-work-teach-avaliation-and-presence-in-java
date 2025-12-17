package br.com.projetoa3.modelo.interfaces;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.records.Student;

import java.util.Map;

public interface ServiceStudent {
   void addStudents(Student student);
   void removeStudents(String ra);
   void setList(Map<String, Student> listaAlunos);
   Map<String, Student> getList();

}
