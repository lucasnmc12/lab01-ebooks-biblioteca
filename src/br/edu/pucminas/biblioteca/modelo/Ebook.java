package br.edu.pucminas.biblioteca.modelo;

import java.util.Objects;

/** Livro digital do acervo, com seus dados bibliograficos e a licenca de uso. */
public class Ebook {

    private String id;
    private String titulo;
    private String editora;
    private FormatoArquivo formato;
    private Categoria categoria;
    private Licenca licenca;
    private StatusRenovacao statusRenovacao;

    public Ebook(String id, String titulo, String editora,
                 FormatoArquivo formato, Categoria categoria, Licenca licenca) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("O eBook precisa de um identificador");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O eBook precisa de um titulo");
        }
        if (editora == null || editora.isBlank()) {
            throw new IllegalArgumentException("O eBook precisa de uma editora");
        }
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.formato = Objects.requireNonNull(formato, "O formato de arquivo e obrigatorio");
        this.categoria = Objects.requireNonNull(categoria, "A categoria e obrigatoria");
        this.licenca = Objects.requireNonNull(licenca, "A licenca de uso e obrigatoria");
        this.statusRenovacao = StatusRenovacao.PENDENTE;
    }

    public boolean estaDisponivel() {
        return licenca.estaDisponivel();
    }

    public void marcarRenovacao(StatusRenovacao status) {
        this.statusRenovacao = Objects.requireNonNull(status);
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public FormatoArquivo getFormato() {
        return formato;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public StatusRenovacao getStatusRenovacao() {
        return statusRenovacao;
    }

    /** Dois eBooks sao o mesmo quando tem o mesmo identificador. */
    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (!(outro instanceof Ebook)) {
            return false;
        }
        return id.equals(((Ebook) outro).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + editora + ", " + formato + ", " + categoria + ")";
    }
}
