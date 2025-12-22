package br.com.projetoa3.modelo.records;

public record Notes(int notaA1, int notaA2, int notaA3) {
    public double getMedia() {
        return (notaA1 + notaA2 + notaA3) / 3;
    }

    public String getStatus() {
        return (getMedia() >= 7) ? "Aprovado" : "Reprovado";
    }
}
