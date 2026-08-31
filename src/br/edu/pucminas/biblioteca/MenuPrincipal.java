package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.Usuario;
import java.io.IOException;
import java.util.Scanner;

/**
 * Ponto de entrada do prototipo. Carrega os dados dos arquivos, faz o
 * login (UC01) e encaminha para o menu do perfil autenticado.
 */
public class MenuPrincipal {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try {
            biblioteca.carregar();
        } catch (IOException e) {
            System.out.println("Nao foi possivel ler os dados salvos: " + e.getMessage());
            return;
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Os arquivos de dados estao inconsistentes: " + e.getMessage());
            return;
        }

        if (biblioteca.vazia()) {
            DadosIniciais.popular(biblioteca);
        }

        try (Scanner leitor = new Scanner(System.in)) {
            Console console = new Console(leitor);
            boolean continuar = true;
            while (continuar) {
                System.out.println();
                System.out.println("=== Sistema de Gestao de eBooks ===");
                System.out.println("1. Entrar");
                System.out.println("2. Sair do sistema");
                if (console.lerOpcao(1, 2) == 1) {
                    entrar(console, biblioteca);
                } else {
                    continuar = false;
                }
            }
        }

        try {
            biblioteca.salvar();
            System.out.println("Dados salvos. Ate logo.");
        } catch (IOException e) {
            System.out.println("Nao foi possivel salvar os dados: " + e.getMessage());
        }
    }

    /** UC01: o login e exigido antes de qualquer outra funcionalidade. */
    private static void entrar(Console console, Biblioteca biblioteca) {
        String id = console.lerTexto("Usuario: ");
        String senha = console.lerTexto("Senha: ");
        Usuario usuario = biblioteca.autenticar(id, senha);
        if (usuario == null) {
            System.out.println("Usuario ou senha invalidos.");
            return;
        }
        if (usuario instanceof Aluno aluno) {
            new MenuAluno(console, biblioteca, aluno).exibir();
        } else if (usuario instanceof Bibliotecario bibliotecario) {
            new MenuBibliotecario(console, biblioteca, bibliotecario).exibir();
        }
    }
}
