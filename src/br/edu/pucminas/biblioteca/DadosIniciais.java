package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.Categoria;
import br.edu.pucminas.biblioteca.modelo.Disciplina;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import br.edu.pucminas.biblioteca.modelo.FormatoArquivo;

/**
 * Popula o sistema na primeira execucao, quando ainda nao ha arquivo de
 * dados. Serve para demonstrar o prototipo sem precisar cadastrar tudo
 * na mao; os dados sao ficticios.
 */
public class DadosIniciais {

    private DadosIniciais() {
    }

    public static void popular(Biblioteca biblioteca) {
        Bibliotecario bibliotecario =
                new Bibliotecario("bib1", "Equipe da Biblioteca", "biblioteca123", "RF-001");
        biblioteca.adicionarUsuario(bibliotecario);

        Aluno ana = new Aluno("ana", "Ana Souza", "aluno123", "2026001");
        Aluno bruno = new Aluno("bruno", "Bruno Lima", "aluno123", "2026002");
        Aluno carla = new Aluno("carla", "Carla Dias", "aluno123", "2026003");
        biblioteca.adicionarUsuario(ana);
        biblioteca.adicionarUsuario(bruno);
        biblioteca.adicionarUsuario(carla);

        Ebook engenharia = bibliotecario.cadastrarEbook(biblioteca.getCatalogo(),
                "Engenharia de Software Moderna", "Editora Universitaria",
                FormatoArquivo.PDF, Categoria.TECNICO, 60);
        Ebook padroes = bibliotecario.cadastrarEbook(biblioteca.getCatalogo(),
                "Padroes de Projeto na Pratica", "Editora Universitaria",
                FormatoArquivo.EPUB, Categoria.TECNICO, 30);
        bibliotecario.cadastrarEbook(biblioteca.getCatalogo(),
                "Contos Reunidos", "Casa Editorial",
                FormatoArquivo.EPUB, Categoria.LITERATURA, 10);
        bibliotecario.cadastrarEbook(biblioteca.getCatalogo(),
                "Revista de Computacao, vol. 12", "Sociedade Academica",
                FormatoArquivo.PDF, Categoria.PERIODICO, 5);

        Disciplina laboratorio = new Disciplina("LDS01", "Laboratorio de Desenvolvimento");
        laboratorio.adicionarEbookIndicado(engenharia);
        laboratorio.adicionarEbookIndicado(padroes);
        biblioteca.adicionarDisciplina(laboratorio);
        ana.matricularEm(laboratorio);
        bruno.matricularEm(laboratorio);
        carla.matricularEm(laboratorio);

        System.out.println("Primeira execucao: dados de exemplo criados.");
        System.out.println("  bibliotecario -> usuario 'bib1', senha 'biblioteca123'");
        System.out.println("  alunos -> 'ana', 'bruno', 'carla', senha 'aluno123'");
    }
}
