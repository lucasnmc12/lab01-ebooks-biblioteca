package br.edu.pucminas.biblioteca.modelo;

/**
 * Base para quem acessa o sistema. E abstrata porque nao existe usuario
 * generico: todo usuario e um aluno ou um bibliotecario.
 *
 * Modelagem sob responsabilidade de Pedro Resende (Sprint 2).
 */
public abstract class Usuario {

    private String id;
    private String nome;
    private String senha;

    public Usuario(String id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean autenticarUsuario(String senhaDigitada) {
        // TODO: implementar na Sprint 3
        return false;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
