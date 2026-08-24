package br.edu.pucminas.biblioteca.modelo;

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
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.formato = formato;
        this.categoria = categoria;
        this.licenca = licenca;
        this.statusRenovacao = StatusRenovacao.PENDENTE;
    }

    public boolean estaDisponivel() {
        return licenca != null && licenca.estaDisponivel();
    }

    public void marcarRenovacao(StatusRenovacao status) {
        this.statusRenovacao = status;
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
}
