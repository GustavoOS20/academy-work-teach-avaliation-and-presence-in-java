package br.com.projetoa3.bancodedados;

import java.util.Map;

public interface ServiceDatabase {
    void createTable(String tableName);
    void delete(String a);
    void insert(Long a, String b, String c, String d);

}
