package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Historico de adicoes de eBooks as estantes. Como e ele que detem
 * esse historico, e daqui que saem tanto a contagem de alunos que
 * define a renovacao quanto a lista de alunos com um titulo.
 */
public class RegistroDeUso {

    private List<AdicaoEbook> adicoes = new ArrayList<>();

    public void registrarAdicao(Aluno aluno, Ebook ebook, PeriodoAcesso periodo) {
        // TODO: implementar na Sprint 3, incluindo a notificacao das estatisticas de uso
    }

    public int contarAlunosDistintos(Ebook ebook, PeriodoAcesso periodo) {
        // TODO: implementar na Sprint 3
        return 0;
    }

    public List<Aluno> listarAlunosComEbook(Ebook ebook) {
        // TODO: implementar na Sprint 3
        return new ArrayList<>();
    }

    public List<AdicaoEbook> getAdicoes() {
        return adicoes;
    }
}
