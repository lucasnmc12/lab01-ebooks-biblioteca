package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Aluno da universidade, dono de uma estante pessoal.
 *
 * Modelagem sob responsabilidade de Pedro Resende (Sprint 2).
 */
public class Aluno extends Usuario {

    private String matricula;
    private List<Disciplina> disciplinasEmCurso = new ArrayList<>();
    private Estante estantePessoal;

    public Aluno(String id, String nome, String senha, String matricula) {
        super(id, nome, senha);
        this.matricula = matricula;
        this.estantePessoal = new Estante();
    }

    public void adicionarEbookNaEstante(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void removerEbookDaEstante(Ebook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void consultarEstante() {
        // TODO: implementar na Sprint 3
    }

    public String getMatricula() {
        return matricula;
    }

    public Estante getEstantePessoal() {
        return estantePessoal;
    }

    public List<Disciplina> getDisciplinasEmCurso() {
        return disciplinasEmCurso;
    }
}
