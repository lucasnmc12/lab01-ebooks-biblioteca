package br.edu.pucminas.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Leitura de entrada do terminal com validacao. Concentrar isso aqui
 * evita repetir tratamento de entrada invalida em cada menu e garante
 * que o programa nunca quebre por causa do que o usuario digitou.
 */
public class Console {

    private final Scanner leitor;

    public Console(Scanner leitor) {
        this.leitor = leitor;
    }

    public String lerTexto(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String valor = leitor.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("  Esse campo nao pode ficar em branco.");
        }
    }

    public int lerInteiro(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            try {
                return Integer.parseInt(leitor.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Digite um numero valido.");
            }
        }
    }

    public LocalDate lerData(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            try {
                return LocalDate.parse(leitor.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("  Use o formato AAAA-MM-DD, por exemplo 2026-08-31.");
            }
        }
    }

    /** Le uma opcao de menu ja limitada ao intervalo valido. */
    public int lerOpcao(int minimo, int maximo) {
        while (true) {
            int opcao = lerInteiro("Escolha uma opcao: ");
            if (opcao >= minimo && opcao <= maximo) {
                return opcao;
            }
            System.out.println("  Opcao invalida, escolha entre " + minimo + " e " + maximo + ".");
        }
    }

    public <T> T escolher(String rotulo, java.util.List<T> itens) {
        if (itens.isEmpty()) {
            return null;
        }
        System.out.println(rotulo);
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + itens.get(i));
        }
        return itens.get(lerOpcao(1, itens.size()) - 1);
    }
}
