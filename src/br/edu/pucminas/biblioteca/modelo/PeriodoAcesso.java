package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;

/** Janela do semestre em que o aluno pode alterar a propria estante. */
public class PeriodoAcesso {

    private LocalDate inicio;
    private LocalDate fim;

    public PeriodoAcesso(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null || !fim.isAfter(inicio)) {
            throw new IllegalArgumentException("A data de fim deve ser posterior a data de inicio");
        }
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean estaAberto(LocalDate data) {
        return !data.isBefore(inicio) && !data.isAfter(fim);
    }

    public void encerrar() {
        // TODO: implementar na Sprint 3
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFim() {
        return fim;
    }
}
