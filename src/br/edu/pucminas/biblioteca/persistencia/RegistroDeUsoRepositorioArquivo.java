package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.AdicaoEbook;
import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.CalendarioAcademico;
import br.edu.pucminas.biblioteca.modelo.Catalogo;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import br.edu.pucminas.biblioteca.modelo.PeriodoAcesso;
import br.edu.pucminas.biblioteca.modelo.RegistroDeUso;
import br.edu.pucminas.biblioteca.modelo.SistemaEstatisticasUso;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Grava o historico de adicoes em dados/adicoes.txt. O periodo e
 * referenciado pela posicao dele no calendario, que e estavel porque os
 * periodos so sao acrescentados ao fim da lista.
 */
public class RegistroDeUsoRepositorioArquivo {

    private static final String ARQUIVO = "adicoes.txt";
    private static final int CAMPOS = 4;

    public void salvar(RegistroDeUso registro, CalendarioAcademico calendario) throws IOException {
        List<PeriodoAcesso> periodos = calendario.getPeriodos();
        List<String> linhas = new ArrayList<>();
        for (AdicaoEbook adicao : registro.getAdicoes()) {
            linhas.add(ArquivoDados.juntar(
                    adicao.getData().toString(),
                    adicao.getEbook().getId(),
                    adicao.getAluno().getMatricula(),
                    String.valueOf(periodos.indexOf(adicao.getPeriodo()))));
        }
        ArquivoDados.gravarLinhas(ARQUIVO, linhas);
    }

    public RegistroDeUso carregar(List<Aluno> alunos, Catalogo catalogo,
                                  CalendarioAcademico calendario,
                                  SistemaEstatisticasUso estatisticas) throws IOException {
        RegistroDeUso registro = new RegistroDeUso(estatisticas);
        List<PeriodoAcesso> periodos = calendario.getPeriodos();
        for (String linha : ArquivoDados.lerLinhas(ARQUIVO)) {
            String[] campos = ArquivoDados.separar(linha, CAMPOS);
            Ebook ebook = catalogo.buscarPorId(campos[1]);
            Aluno aluno = buscarPorMatricula(alunos, campos[2]);
            int indicePeriodo = Integer.parseInt(campos[3]);
            if (ebook == null || aluno == null
                    || indicePeriodo < 0 || indicePeriodo >= periodos.size()) {
                // registro orfao: titulo, aluno ou periodo nao existe mais
                continue;
            }
            registro.restaurarAdicao(new AdicaoEbook(LocalDate.parse(campos[0]), ebook, aluno,
                    periodos.get(indicePeriodo)));
        }
        return registro;
    }

    private Aluno buscarPorMatricula(List<Aluno> alunos, String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
}
