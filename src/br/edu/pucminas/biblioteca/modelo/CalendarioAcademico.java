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
        if (periodoAberto() != null) {
            throw new IllegalStateException(
                    "Ja existe um periodo de acesso aberto; encerre-o antes de abrir outro");
        }
        PeriodoAcesso periodo = new PeriodoAcesso(inicio, fim);
        periodos.add(periodo);
        return periodo;
    }

    public void encerrarPeriodoAberto() {
        PeriodoAcesso aberto = periodoAberto();
        if (aberto == null) {
            throw new IllegalStateException("Nao ha periodo de acesso aberto");
        }
        aberto.encerrar();
    }

    /** Periodo vigente hoje, ou null quando nao ha nenhum aberto. */
    public PeriodoAcesso periodoAberto() {
        LocalDate hoje = LocalDate.now();
        for (PeriodoAcesso periodo : periodos) {
            if (periodo.estaAberto(hoje)) {
                return periodo;
            }
        }
        return null;
    }

    public boolean existePeriodoAberto(LocalDate data) {
        for (PeriodoAcesso periodo : periodos) {
            if (periodo.estaAberto(data)) {
                return true;
            }
        }
        return false;
    }

    public void restaurarPeriodo(PeriodoAcesso periodo) {
        periodos.add(periodo);
    }

    public List<PeriodoAcesso> getPeriodos() {
        return new ArrayList<>(periodos);
    }
}
