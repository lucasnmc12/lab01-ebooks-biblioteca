package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Catalogo;
import br.edu.pucminas.biblioteca.modelo.Disciplina;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Grava as disciplinas em dados/disciplinas.txt, uma por linha, no
 * formato id;nome;ebooksIndicados;matriculas. As duas ultimas colunas
 * sao listas internas, separadas por barra vertical, porque uma
 * disciplina indica varios titulos e e cursada por varios alunos.
 */
public class DisciplinaRepositorioArquivo {

    private static final String ARQUIVO = "disciplinas.txt";
    private static final String SUBSEPARADOR = "\\|";
    private static final int CAMPOS = 4;

    public void salvar(List<Disciplina> disciplinas, List<Aluno> alunos) throws IOException {
        List<String> linhas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            List<String> idsEbooks = new ArrayList<>();
            for (Ebook ebook : disciplina.getEbooksIndicados()) {
                idsEbooks.add(ebook.getId());
            }
            List<String> matriculas = new ArrayList<>();
            for (Aluno aluno : alunos) {
                if (aluno.getDisciplinasEmCurso().contains(disciplina)) {
                    matriculas.add(aluno.getMatricula());
                }
            }
            linhas.add(ArquivoDados.juntar(disciplina.getId(), disciplina.getNome(),
                    String.join("|", idsEbooks), String.join("|", matriculas)));
        }
        ArquivoDados.gravarLinhas(ARQUIVO, linhas);
    }

    public List<Disciplina> carregar(List<Aluno> alunos, Catalogo catalogo) throws IOException {
        List<Disciplina> disciplinas = new ArrayList<>();
        for (String linha : ArquivoDados.lerLinhas(ARQUIVO)) {
            String[] campos = ArquivoDados.separar(linha, CAMPOS);
            Disciplina disciplina = new Disciplina(campos[0], campos[1]);
            for (String idEbook : dividir(campos[2])) {
                Ebook ebook = catalogo.buscarPorId(idEbook);
                if (ebook != null) {
                    disciplina.adicionarEbookIndicado(ebook);
                }
            }
            for (String matricula : dividir(campos[3])) {
                for (Aluno aluno : alunos) {
                    if (aluno.getMatricula().equals(matricula)) {
                        aluno.matricularEm(disciplina);
                    }
                }
            }
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }

    private List<String> dividir(String campo) {
        List<String> valores = new ArrayList<>();
        if (campo != null && !campo.isBlank()) {
            for (String valor : campo.split(SUBSEPARADOR, -1)) {
                if (!valor.isBlank()) {
                    valores.add(valor);
                }
            }
        }
        return valores;
    }
}
