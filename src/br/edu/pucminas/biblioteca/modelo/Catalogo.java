package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Titulos licenciados no semestre. Como e o catalogo que detem a lista
 * de eBooks, e ele quem avalia a renovacao e remove os nao renovados.
 */
public class Catalogo {

    private static final int MINIMO_ALUNOS_RENOVACAO = 3;

    private List<Ebook> ebooks = new ArrayList<>();

    public void adicionarEbook(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void removerEbook(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public List<Ebook> listarEbooks() {
        return ebooks;
    }

    /**
     * Marca cada eBook do catalogo para renovacao ou remocao, conforme
     * o numero de alunos que o adicionaram durante o periodo.
     */
    public void avaliarRenovacao(PeriodoAcesso periodo, RegistroDeUso registro) {
        // TODO: implementar na Sprint 3, usando MINIMO_ALUNOS_RENOVACAO
    }

    public void removerNaoRenovados() {
        // TODO: implementar na Sprint 3
    }

    public static int getMinimoAlunosRenovacao() {
        return MINIMO_ALUNOS_RENOVACAO;
    }
}
