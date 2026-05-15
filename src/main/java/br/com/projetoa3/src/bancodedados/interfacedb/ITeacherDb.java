package br.com.projetoa3.src.bancodedados.interfacedb;


import br.com.projetoa3.src.modelo.records.Teach;

import java.util.Map;

public interface ITeacherDb extends IDatabase {
    void insert(String ra, String nome, String email, String senha);
    Map<String, Teach> listTeachers();
}
