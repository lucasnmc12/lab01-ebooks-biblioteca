package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;

/** Janela do semestre em que o aluno pode alterar a propria estante. */
public class PeriodoAcesso {

    private LocalDate inicio;
    private LocalDate fim;
    private boolean encerrado;

    public PeriodoAcesso(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null || !fim.isAfter(inicio)) {
            throw new IllegalArgumentException("A data de fim deve ser posterior a data de inicio");
        }
        this.inicio = inicio;
        this.fim = fim;
        this.encerrado = false;
    }

    public boolean estaAberto(LocalDate data) {
        return !encerrado && !data.isBefore(inicio) && !data.isAfter(fim);
    }

    public void encerrar() {
        this.encerrado = true;
    }

    public boolean estaEncerrado() {
        return encerrado;
    }

    /** Usado pela persistencia para restaurar um periodo ja encerrado. */
    public void restaurarEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    @Override
    public String toString() {
        return inicio + " a " + fim + (encerrado ? " (encerrado)" : " (aberto)");
    }
}
