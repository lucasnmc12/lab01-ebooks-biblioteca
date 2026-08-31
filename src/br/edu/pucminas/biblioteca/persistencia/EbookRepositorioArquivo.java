package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.Catalogo;
import br.edu.pucminas.biblioteca.modelo.Categoria;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import br.edu.pucminas.biblioteca.modelo.FormatoArquivo;
import br.edu.pucminas.biblioteca.modelo.Licenca;
import br.edu.pucminas.biblioteca.modelo.StatusRenovacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Grava o catalogo em dados/ebooks.txt, um titulo por linha.
 *
 * Os acessos em uso nao sao gravados: eles sao recalculados a partir das
 * estantes ao carregar o sistema, o que evita que arquivo e memoria fiquem
 * com contagens diferentes.
 */
public class EbookRepositorioArquivo {

    private static final String ARQUIVO = "ebooks.txt";
    private static final int CAMPOS = 7;

    public void salvar(Catalogo catalogo) throws IOException {
        List<String> linhas = new ArrayList<>();
        for (Ebook ebook : catalogo.listarEbooks()) {
            linhas.add(ArquivoDados.juntar(
                    ebook.getId(),
                    ebook.getTitulo(),
                    ebook.getEditora(),
                    ebook.getFormato().name(),
                    ebook.getCategoria().name(),
                    String.valueOf(ebook.getLicenca().getLimiteAcessosSimultaneos()),
                    ebook.getStatusRenovacao().name()));
        }
        ArquivoDados.gravarLinhas(ARQUIVO, linhas);
    }

    public Catalogo carregar() throws IOException {
        Catalogo catalogo = new Catalogo();
        for (String linha : ArquivoDados.lerLinhas(ARQUIVO)) {
            String[] campos = ArquivoDados.separar(linha, CAMPOS);
            Ebook ebook = new Ebook(
                    campos[0],
                    campos[1],
                    campos[2],
                    FormatoArquivo.valueOf(campos[3]),
                    Categoria.valueOf(campos[4]),
                    new Licenca(Integer.parseInt(campos[5])));
            ebook.marcarRenovacao(StatusRenovacao.valueOf(campos[6]));
            catalogo.adicionarEbook(ebook);
        }
        return catalogo;
    }
}
