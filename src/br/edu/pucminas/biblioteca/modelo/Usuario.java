package br.edu.pucminas.biblioteca.modelo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * Base para quem acessa o sistema. E abstrata porque nao existe usuario
 * generico: todo usuario e um aluno ou um bibliotecario.
 *
 * A senha nunca e guardada em texto puro, nem em memoria nem no arquivo
 * de persistencia: o que fica armazenado e o resumo SHA-256 dela.
 *
 * Modelagem sob responsabilidade de Pedro Resende (Sprint 2).
 */
public abstract class Usuario {

    private String id;
    private String nome;
    private String senhaHash;

    public Usuario(String id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senhaHash = gerarHash(senha);
    }

    public boolean autenticarUsuario(String senhaDigitada) {
        if (senhaDigitada == null) {
            return false;
        }
        return senhaHash.equals(gerarHash(senhaDigitada));
    }

    /**
     * Resumo SHA-256 usado para comparar senhas sem guardar o texto puro.
     * Num sistema real o correto seria um algoritmo proprio de senha, com
     * sal e custo configuravel (bcrypt, scrypt ou Argon2); aqui o SHA-256
     * atende ao proposito didatico do prototipo.
     */
    public static String gerarHash(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] resumo = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(resumo);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algoritmo SHA-256 indisponivel nesta JVM", e);
        }
    }

    /** Usado pela persistencia para recriar o usuario a partir do hash gravado. */
    protected void definirSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
