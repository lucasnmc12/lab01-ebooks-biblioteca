package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Catalogo;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Grava o conteudo das estantes em dados/estantes.txt, uma linha por
 * eBook: matricula, identificador do titulo e o tipo de leitura.
 *
 * Ao carregar, cada titulo restaurado ocupa novamente uma licenca de uso,
 * o que reconstroi a contagem de acessos simultaneos sem precisar grava-la.
 */
public class EstanteRepositorioArquivo {

    private static final String ARQUIVO = "estantes.txt";
    private static final int CAMPOS = 3;

    public void salvar(List<Aluno> alunos) throws IOException {
        List<String> linhas = new ArrayList<>();
        for (Aluno aluno : alunos) {
            for (Ebook ebook : aluno.getEstantePessoal().getEbooksObrigatorios()) {
                linhas.add(ArquivoDados.juntar(aluno.getMatricula(), ebook.getId(), "OBRIGATORIO"));
            }
            for (Ebook ebook : aluno.getEstantePessoal().getEbooksLivres()) {
                linhas.add(ArquivoDados.juntar(aluno.getMatricula(), ebook.getId(), "LIVRE"));
            }
        }
        ArquivoDados.gravarLinhas(ARQUIVO, linhas);
    }

    public void carregar(List<Aluno> alunos, Catalogo catalogo) throws IOException {
        for (String linha : ArquivoDados.lerLinhas(ARQUIVO)) {
            String[] campos = ArquivoDados.separar(linha, CAMPOS);
            Aluno aluno = buscarPorMatricula(alunos, campos[0]);
            Ebook ebook = catalogo.buscarPorId(campos[1]);
            if (aluno == null || ebook == null) {
                // titulo removido do catalogo ou aluno desligado: a linha e ignorada
                continue;
            }
            if ("OBRIGATORIO".equals(campos[2])) {
                aluno.getEstantePessoal().adicionarEbookObrigatorio(ebook);
            } else {
                aluno.getEstantePessoal().adicionarEbookLivre(ebook);
            }
            ebook.getLicenca().adicionarAcesso();
        }
    }

    private Aluno buscarPorMatricula(List<Aluno> alunos, String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
}
