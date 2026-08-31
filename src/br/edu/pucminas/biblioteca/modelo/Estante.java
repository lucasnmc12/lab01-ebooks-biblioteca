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
        validarNaoRepetido(ebook);
        if (ebooksObrigatorios.size() >= LIMITE_OBRIGATORIOS) {
            throw new IllegalStateException(
                    "A estante ja tem os " + LIMITE_OBRIGATORIOS + " eBooks de leitura obrigatoria");
        }
        ebooksObrigatorios.add(ebook);
    }

    public void adicionarEbookLivre(Ebook ebook) {
        validarNaoRepetido(ebook);
        if (ebooksLivres.size() >= LIMITE_LIVRES) {
            throw new IllegalStateException(
                    "A estante ja tem os " + LIMITE_LIVRES + " eBooks de leitura livre");
        }
        ebooksLivres.add(ebook);
    }

    public void removerEbook(Ebook ebook) {
        boolean removido = ebooksObrigatorios.remove(ebook) || ebooksLivres.remove(ebook);
        if (!removido) {
            throw new IllegalStateException("O eBook \"" + ebook.getTitulo() + "\" nao esta na estante");
        }
    }

    public List<Ebook> listarEbooksNaEstante() {
        List<Ebook> todos = new ArrayList<>(ebooksObrigatorios);
        todos.addAll(ebooksLivres);
        return todos;
    }

    public int contarEbooks() {
        return ebooksObrigatorios.size() + ebooksLivres.size();
    }

    public boolean contem(Ebook ebook) {
        return ebooksObrigatorios.contains(ebook) || ebooksLivres.contains(ebook);
    }

    public List<Ebook> getEbooksObrigatorios() {
        return new ArrayList<>(ebooksObrigatorios);
    }

    public List<Ebook> getEbooksLivres() {
        return new ArrayList<>(ebooksLivres);
    }

    private void validarNaoRepetido(Ebook ebook) {
        if (contem(ebook)) {
            throw new IllegalStateException(
                    "O eBook \"" + ebook.getTitulo() + "\" ja esta na estante");
        }
    }
}
