package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.Catalogo;
import br.edu.pucminas.biblioteca.modelo.Categoria;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import br.edu.pucminas.biblioteca.modelo.FormatoArquivo;
import br.edu.pucminas.biblioteca.modelo.Licenca;
import br.edu.pucminas.biblioteca.modelo.PeriodoAcesso;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/** Opcoes do bibliotecario depois do login (UC07 a UC12). */
public class MenuBibliotecario {

    private final Console console;
    private final Biblioteca biblioteca;
    private final Bibliotecario bibliotecario;

    public MenuBibliotecario(Console console, Biblioteca biblioteca, Bibliotecario bibliotecario) {
        this.console = console;
        this.biblioteca = biblioteca;
        this.bibliotecario = bibliotecario;
    }

    public void exibir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("--- Acervo (" + bibliotecario.getNome() + ") ---");
            System.out.println("1. Cadastrar eBook");
            System.out.println("2. Definir licenca de uso de um eBook");
            System.out.println("3. Consultar alunos com um eBook");
            System.out.println("4. Abrir periodo de acesso");
            System.out.println("5. Encerrar periodo de acesso");
            System.out.println("6. Avaliar renovacao do catalogo");
            System.out.println("7. Remover eBooks nao renovados");
            System.out.println("8. Listar catalogo");
            System.out.println("9. Sair da conta");
            try {
                switch (console.lerOpcao(1, 9)) {
                    case 1 -> cadastrar();
                    case 2 -> definirLicenca();
                    case 3 -> consultarAlunos();
                    case 4 -> abrirPeriodo();
                    case 5 -> encerrarPeriodo();
                    case 6 -> avaliarRenovacao();
                    case 7 -> removerNaoRenovados();
                    case 8 -> listarCatalogo();
                    default -> continuar = false;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Nao foi possivel concluir a acao: " + e.getMessage());
            }
        }
    }

    /** UC07 e UC08: o cadastro ja exige a licenca de uso. */
    private void cadastrar() {
        String titulo = console.lerTexto("Titulo: ");
        String editora = console.lerTexto("Editora: ");
        FormatoArquivo formato = console.escolher("Formato do arquivo:",
                Arrays.asList(FormatoArquivo.values()));
        Categoria categoria = console.escolher("Categoria:", Arrays.asList(Categoria.values()));
        int limite = console.lerInteiro(
                "Acessos simultaneos (1 a " + Licenca.LIMITE_MAXIMO_ACESSOS + "): ");
        Ebook ebook = bibliotecario.cadastrarEbook(biblioteca.getCatalogo(), titulo, editora,
                formato, categoria, limite);
        System.out.println("Cadastrado: " + ebook);
    }

    private void definirLicenca() {
        Ebook ebook = escolherEbook();
        if (ebook == null) {
            return;
        }
        int limite = console.lerInteiro(
                "Novo limite (1 a " + Licenca.LIMITE_MAXIMO_ACESSOS + "): ");
        bibliotecario.definirLicenca(ebook, limite);
        System.out.println("Limite de \"" + ebook.getTitulo() + "\" agora e " + limite + ".");
    }

    /** UC09. */
    private void consultarAlunos() {
        Ebook ebook = escolherEbook();
        if (ebook == null) {
            return;
        }
        List<Aluno> alunos = bibliotecario.consultarAlunosComEbook(biblioteca.getRegistro(), ebook);
        System.out.println("Alunos com \"" + ebook.getTitulo() + "\" na estante: " + alunos.size()
                + " (minimo de " + Catalogo.getMinimoAlunosRenovacao() + " para renovar)");
        for (Aluno aluno : alunos) {
            System.out.println("  " + aluno.getMatricula() + " - " + aluno.getNome());
        }
    }

    /** UC10. */
    private void abrirPeriodo() {
        LocalDate inicio = console.lerData("Inicio (AAAA-MM-DD): ");
        LocalDate fim = console.lerData("Fim (AAAA-MM-DD): ");
        PeriodoAcesso periodo = bibliotecario.abrirPeriodoAcesso(
                biblioteca.getCalendario(), inicio, fim);
        System.out.println("Periodo aberto: " + periodo);
    }

    private void encerrarPeriodo() {
        bibliotecario.encerrarPeriodoAcesso(biblioteca.getCalendario());
        System.out.println("Periodo de acesso encerrado.");
    }

    /** UC11. */
    private void avaliarRenovacao() {
        List<PeriodoAcesso> encerrados = biblioteca.getCalendario().getPeriodos();
        encerrados.removeIf(periodo -> !periodo.estaEncerrado());
        if (encerrados.isEmpty()) {
            System.out.println("Nenhum periodo encerrado para avaliar.");
            return;
        }
        PeriodoAcesso periodo = console.escolher("Periodo a avaliar:", encerrados);
        biblioteca.getCatalogo().avaliarRenovacao(periodo, biblioteca.getRegistro());
        System.out.println("Avaliacao concluida:");
        for (Ebook ebook : biblioteca.getCatalogo().listarEbooks()) {
            System.out.println("  " + ebook.getTitulo() + " - "
                    + biblioteca.getRegistro().contarAlunosDistintos(ebook, periodo)
                    + " aluno(s) - " + ebook.getStatusRenovacao());
        }
    }

    /** UC12. */
    private void removerNaoRenovados() {
        List<Ebook> removidos = biblioteca.getCatalogo().removerNaoRenovados();
        if (removidos.isEmpty()) {
            System.out.println("Nenhum titulo marcado como nao renovado.");
            return;
        }
        System.out.println("Removidos do catalogo do proximo semestre:");
        for (Ebook ebook : removidos) {
            System.out.println("  " + ebook.getTitulo());
        }
    }

    private void listarCatalogo() {
        List<Ebook> ebooks = biblioteca.getCatalogo().listarEbooks();
        if (ebooks.isEmpty()) {
            System.out.println("O catalogo esta vazio.");
            return;
        }
        for (Ebook ebook : ebooks) {
            System.out.println("  " + ebook + " - licenca "
                    + ebook.getLicenca().getAcessosAtivos() + "/"
                    + ebook.getLicenca().getLimiteAcessosSimultaneos()
                    + " - " + ebook.getStatusRenovacao());
        }
    }

    private Ebook escolherEbook() {
        List<Ebook> ebooks = biblioteca.getCatalogo().listarEbooks();
        if (ebooks.isEmpty()) {
            System.out.println("O catalogo esta vazio.");
            return null;
        }
        return console.escolher("Escolha o eBook:", ebooks);
    }
}
