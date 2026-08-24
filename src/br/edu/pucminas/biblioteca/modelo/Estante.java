package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Estante pessoal do aluno: ate 4 eBooks de leitura obrigatoria e
 * mais 2 de leitura livre.
 *
 * Modelagem sob responsabilidade de Pedro Resende (Sprint 2).
 */
public class Estante {

    public static final int LIMITE_OBRIGATORIOS = 4;
    public static final int LIMITE_LIVRES = 2;

    private List<Ebook> ebooksObrigatorios = new ArrayList<>();
    private List<Ebook> ebooksLivres = new ArrayList<>();

    public void adicionarEbookObrigatorio(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void adicionarEbookLivre(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void removerEbook(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public List<Ebook> listarEbooksNaEstante() {
        List<Ebook> todos = new ArrayList<>(ebooksObrigatorios);
        todos.addAll(ebooksLivres);
        return todos;
    }
}
