package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Disciplina cursada pelo aluno, que indica os eBooks de leitura
 * obrigatoria.
 *
 * Modelagem sob responsabilidade de Pedro Resende (Sprint 2).
 */
public class Disciplina {

    private String id;
    private String nome;
    private List<Ebook> ebooksIndicados = new ArrayList<>();

    public Disciplina(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionarEbookIndicado(Ebook ebook) {
        if (!ebooksIndicados.contains(ebook)) {
            ebooksIndicados.add(ebook);
        }
    }

    public void removerEbookIndicado(Ebook ebook) {
        ebooksIndicados.remove(ebook);
    }

    public boolean indica(Ebook ebook) {
        return ebooksIndicados.contains(ebook);
    }

    public List<Ebook> getEbooksIndicados() {
        return new ArrayList<>(ebooksIndicados);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
