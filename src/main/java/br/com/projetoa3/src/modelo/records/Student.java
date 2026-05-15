package br.com.projetoa3.src.modelo.records;

public record Student(String nome, long ra, String turma, String professor) {
    public String toString() {
        return nome + " | RA: " + ra + " | Turma: " + turma + " | Professor: " + professor;
    }
}
