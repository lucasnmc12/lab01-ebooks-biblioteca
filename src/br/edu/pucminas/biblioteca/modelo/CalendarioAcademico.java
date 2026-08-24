package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Reune os periodos de acesso do semestre. Concentrar os periodos aqui
 * e o que permite garantir que nunca haja dois abertos ao mesmo tempo.
 */
public class CalendarioAcademico {

    private List<PeriodoAcesso> periodos = new ArrayList<>();

    public PeriodoAcesso abrirPeriodo(LocalDate inicio, LocalDate fim) {
        // TODO: implementar na Sprint 3
        return null;
    }

    public void encerrarPeriodoAberto() {
        // TODO: implementar na Sprint 3
    }

    public PeriodoAcesso periodoAberto() {
        // TODO: implementar na Sprint 3
        return null;
    }

    public boolean existePeriodoAberto(LocalDate data) {
        // TODO: implementar na Sprint 3
        return false;
    }

    public List<PeriodoAcesso> getPeriodos() {
        return periodos;
    }
}
