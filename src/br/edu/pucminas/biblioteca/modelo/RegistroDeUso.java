package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Historico de adicoes de eBooks as estantes. Como e ele que detem
 * esse historico, e daqui que saem tanto a contagem de alunos que
 * define a renovacao quanto a lista de alunos com um titulo.
 */
public class RegistroDeUso {

    private List<AdicaoEbook> adicoes = new ArrayList<>();
    private SistemaEstatisticasUso estatisticas;

    public RegistroDeUso(SistemaEstatisticasUso estatisticas) {
        this.estatisticas = estatisticas;
    }

    /**
     * Guarda a adicao e notifica o sistema externo de estatisticas de uso,
     * conforme a descricao do sistema.
     */
    public void registrarAdicao(Aluno aluno, Ebook ebook, PeriodoAcesso periodo) {
        adicoes.add(new AdicaoEbook(LocalDate.now(), ebook, aluno, periodo));
        if (estatisticas != null) {
            estatisticas.registrarAdicao(aluno, ebook);
        }
    }

    /** Reconstroi uma adicao lida do arquivo, sem notificar as estatisticas. */
    public void restaurarAdicao(AdicaoEbook adicao) {
        adicoes.add(adicao);
    }

    /**
     * Quantos alunos diferentes adicionaram o titulo no periodo. E este
     * numero que decide a renovacao da licenca no semestre seguinte.
     */
    public int contarAlunosDistintos(Ebook ebook, PeriodoAcesso periodo) {
        Set<String> matriculas = new LinkedHashSet<>();
        for (AdicaoEbook adicao : adicoes) {
            if (adicao.getEbook().equals(ebook) && adicao.getPeriodo() == periodo) {
                matriculas.add(adicao.getAluno().getMatricula());
            }
        }
        return matriculas.size();
    }

    public List<Aluno> listarAlunosComEbook(Ebook ebook) {
        List<Aluno> alunos = new ArrayList<>();
        for (AdicaoEbook adicao : adicoes) {
            Aluno aluno = adicao.getAluno();
            if (adicao.getEbook().equals(ebook)
                    && aluno.getEstantePessoal().contem(ebook)
                    && !alunos.contains(aluno)) {
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public List<AdicaoEbook> getAdicoes() {
        return new ArrayList<>(adicoes);
    }
}
