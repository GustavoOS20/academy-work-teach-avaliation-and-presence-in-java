package br.com.projetoa3.bancodedados.interfacedb;

import br.com.projetoa3.modelo.Turmas;
import br.com.projetoa3.modelo.records.ClassSchool;

import java.util.Map;

public interface IClassSchool extends IDatabase{
    void insertClass(String id,String nomeDaTurma, String professores_ra);
    Map<String, ClassSchool> listClass();
}
