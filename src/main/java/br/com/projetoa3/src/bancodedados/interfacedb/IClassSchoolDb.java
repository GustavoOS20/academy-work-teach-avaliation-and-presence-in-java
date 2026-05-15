package br.com.projetoa3.src.bancodedados.interfacedb;

import br.com.projetoa3.src.modelo.records.ClassSchool;

import java.util.Map;

public interface IClassSchoolDb extends IDatabase{
    void insertClass(String id,String nomeDaTurma, String professores_ra);
    Map<String, ClassSchool> listClass();
}
