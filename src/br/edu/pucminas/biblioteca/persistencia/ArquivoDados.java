package br.edu.pucminas.biblioteca.persistencia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Leitura e escrita dos arquivos de texto usados como persistencia.
 * Concentrar isso aqui evita repetir tratamento de arquivo em cada
 * repositorio e garante que a pasta de dados exista.
 */
public class ArquivoDados {

    public static final String SEPARADOR = ";";

    private static final Path PASTA = Path.of("dados");

    private ArquivoDados() {
    }

    /** Linhas do arquivo, ou lista vazia quando ele ainda nao existe. */
    public static List<String> lerLinhas(String nomeArquivo) throws IOException {
        Path caminho = PASTA.resolve(nomeArquivo);
        if (!Files.exists(caminho)) {
            return new ArrayList<>();
        }
        List<String> linhas = new ArrayList<>();
        for (String linha : Files.readAllLines(caminho, StandardCharsets.UTF_8)) {
            if (!linha.isBlank()) {
                linhas.add(linha);
            }
        }
        return linhas;
    }

    public static void gravarLinhas(String nomeArquivo, List<String> linhas) throws IOException {
        Files.createDirectories(PASTA);
        Files.write(PASTA.resolve(nomeArquivo), linhas, StandardCharsets.UTF_8);
    }

    /**
     * Junta os campos com o separador, recusando valores que o contenham.
     * Sem essa checagem, um titulo com ponto e virgula quebraria a leitura
     * do arquivo de forma silenciosa.
     */
    public static String juntar(String... campos) {
        for (String campo : campos) {
            if (campo != null && campo.contains(SEPARADOR)) {
                throw new IllegalArgumentException(
                        "O caractere '" + SEPARADOR + "' nao pode ser usado nos dados: " + campo);
            }
        }
        return String.join(SEPARADOR, campos);
    }

    public static String[] separar(String linha, int camposEsperados) {
        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != camposEsperados) {
            throw new IllegalStateException(
                    "Linha com " + campos.length + " campos, esperados " + camposEsperados + ": " + linha);
        }
        return campos;
    }
}
