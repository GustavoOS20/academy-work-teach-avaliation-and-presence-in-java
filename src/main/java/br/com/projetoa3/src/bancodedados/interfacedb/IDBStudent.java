package br.com.projetoa3.src.bancodedados.interfacedb;

import br.com.projetoa3.src.modelo.records.Student;

import java.util.Map;

public interface IDBStudent extends IDatabase {
    void insert(Long a, String b, String c, String d);
    Map<String, Student>listTables();
}
