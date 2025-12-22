package br.com.projetoa3.bancodedados.interfacedb;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.records.Student;

import java.util.Map;

public interface IDBStudent extends IDatabase {
    void insert(Long a, String b, String c, String d);
    Map<String, Student>listTables();
}
