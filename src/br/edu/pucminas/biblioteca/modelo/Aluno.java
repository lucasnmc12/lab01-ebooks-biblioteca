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

    /** Recria um aluno lido do arquivo, cuja senha ja esta em forma de hash. */
    public static Aluno comSenhaHash(String id, String nome, String senhaHash, String matricula) {
        Aluno aluno = new Aluno(id, nome, "", matricula);
        aluno.definirSenhaHash(senhaHash);
        return aluno;
    }

    /**
     * Adiciona o eBook a estante ocupando uma licenca de uso. O titulo
     * entra como leitura obrigatoria quando alguma disciplina em curso o
     * indica, e como leitura livre caso contrario.
     */
    public void adicionarEbookNaEstante(Ebook ebook) {
        if (!ebook.estaDisponivel()) {
            throw new IllegalStateException(
                    "O eBook \"" + ebook.getTitulo() + "\" atingiu o limite de acessos simultaneos");
        }
        if (ehLeituraObrigatoria(ebook)) {
            estantePessoal.adicionarEbookObrigatorio(ebook);
        } else {
            estantePessoal.adicionarEbookLivre(ebook);
        }
        ebook.getLicenca().adicionarAcesso();
    }

    public void removerEbookDaEstante(Ebook ebook) {
        estantePessoal.removerEbook(ebook);
        ebook.getLicenca().liberarAcesso();
    }

    public List<Ebook> consultarEstante() {
        return estantePessoal.listarEbooksNaEstante();
    }

    /** Verdadeiro quando alguma disciplina em curso indica o titulo. */
    public boolean ehLeituraObrigatoria(Ebook ebook) {
        return disciplinasEmCurso.stream().anyMatch(disciplina -> disciplina.indica(ebook));
    }

    public void matricularEm(Disciplina disciplina) {
        if (!disciplinasEmCurso.contains(disciplina)) {
            disciplinasEmCurso.add(disciplina);
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public Estante getEstantePessoal() {
        return estantePessoal;
    }

    public List<Disciplina> getDisciplinasEmCurso() {
        return new ArrayList<>(disciplinasEmCurso);
    }
}
