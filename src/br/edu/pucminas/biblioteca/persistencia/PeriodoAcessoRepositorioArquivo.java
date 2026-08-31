package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.CalendarioAcademico;
import br.edu.pucminas.biblioteca.modelo.PeriodoAcesso;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Grava os periodos de acesso do semestre em dados/periodos.txt. */
public class PeriodoAcessoRepositorioArquivo {

    private static final String ARQUIVO = "periodos.txt";
    private static final int CAMPOS = 3;

    public void salvar(CalendarioAcademico calendario) throws IOException {
        List<String> linhas = new ArrayList<>();
        for (PeriodoAcesso periodo : calendario.getPeriodos()) {
            linhas.add(ArquivoDados.juntar(
                    periodo.getInicio().toString(),
                    periodo.getFim().toString(),
                    String.valueOf(periodo.estaEncerrado())));
        }
        ArquivoDados.gravarLinhas(ARQUIVO, linhas);
    }

    public CalendarioAcademico carregar() throws IOException {
        CalendarioAcademico calendario = new CalendarioAcademico();
        for (String linha : ArquivoDados.lerLinhas(ARQUIVO)) {
            String[] campos = ArquivoDados.separar(linha, CAMPOS);
            PeriodoAcesso periodo = new PeriodoAcesso(
                    LocalDate.parse(campos[0]), LocalDate.parse(campos[1]));
            periodo.restaurarEncerrado(Boolean.parseBoolean(campos[2]));
            calendario.restaurarPeriodo(periodo);
        }
        return calendario;
    }
}
