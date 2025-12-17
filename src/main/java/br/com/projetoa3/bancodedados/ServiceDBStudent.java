package br.com.projetoa3.bancodedados;

import br.com.projetoa3.modelo.Alunos;

import java.util.Map;

public interface ServiceDBStudent extends ServiceDatabase{
    Map<String, Alunos> listTables();

}
