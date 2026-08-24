package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;

/**
 * Registro de que um aluno adicionou um eBook a estante durante um
 * periodo de acesso. E a evidencia usada tanto para a renovacao do
 * catalogo quanto para as estatisticas de uso.
 */
public class AdicaoEbook {

    private LocalDate data;
    private Ebook ebook;
    private Aluno aluno;
    private PeriodoAcesso periodo;

    public AdicaoEbook(LocalDate data, Ebook ebook, Aluno aluno, PeriodoAcesso periodo) {
        this.data = data;
        this.ebook = ebook;
        this.aluno = aluno;
        this.periodo = periodo;
    }

    public LocalDate getData() {
        return data;
    }

    public Ebook getEbook() {
        return ebook;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public PeriodoAcesso getPeriodo() {
        return periodo;
    }
}
