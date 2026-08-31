package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Ebook;
import br.edu.pucminas.biblioteca.modelo.Estante;
import br.edu.pucminas.biblioteca.modelo.PeriodoAcesso;
import java.util.List;

/** Opcoes disponiveis para o aluno depois do login (UC02, UC03 e UC04). */
public class MenuAluno {

    private final Console console;
    private final Biblioteca biblioteca;
    private final Aluno aluno;

    public MenuAluno(Console console, Biblioteca biblioteca, Aluno aluno) {
        this.console = console;
        this.biblioteca = biblioteca;
        this.aluno = aluno;
    }

    public void exibir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("--- Estante de " + aluno.getNome() + " ---");
            System.out.println("1. Adicionar eBook a estante");
            System.out.println("2. Remover eBook da estante");
            System.out.println("3. Consultar minha estante");
            System.out.println("4. Ver catalogo do semestre");
            System.out.println("5. Sair da conta");
            try {
                switch (console.lerOpcao(1, 5)) {
                    case 1 -> adicionar();
                    case 2 -> remover();
                    case 3 -> consultar();
                    case 4 -> verCatalogo();
                    default -> continuar = false;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Nao foi possivel concluir a acao: " + e.getMessage());
            }
        }
    }

    /**
     * UC02. A janela do semestre e checada aqui, na camada de aplicacao,
     * porque e uma condicao de fluxo: a estante em si nao sabe em que
     * momento do calendario esta sendo alterada.
     */
    private void adicionar() {
        PeriodoAcesso periodo = periodoAbertoOuAviso();
        if (periodo == null) {
            return;
        }
        List<Ebook> disponiveis = biblioteca.getCatalogo().listarEbooks();
        disponiveis.removeIf(ebook -> aluno.getEstantePessoal().contem(ebook));
        if (disponiveis.isEmpty()) {
            System.out.println("Nao ha titulos novos no catalogo para adicionar.");
            return;
        }
        Ebook escolhido = console.escolher("Titulos disponiveis:", disponiveis);
        aluno.adicionarEbookNaEstante(escolhido);
        biblioteca.getRegistro().registrarAdicao(aluno, escolhido, periodo);
        System.out.println("\"" + escolhido.getTitulo() + "\" entrou na estante como leitura "
                + (aluno.ehLeituraObrigatoria(escolhido) ? "obrigatoria." : "livre."));
    }

    /** UC03. */
    private void remover() {
        if (periodoAbertoOuAviso() == null) {
            return;
        }
        List<Ebook> naEstante = aluno.consultarEstante();
        if (naEstante.isEmpty()) {
            System.out.println("A estante esta vazia.");
            return;
        }
        Ebook escolhido = console.escolher("eBooks na sua estante:", naEstante);
        aluno.removerEbookDaEstante(escolhido);
        System.out.println("\"" + escolhido.getTitulo() + "\" saiu da estante.");
    }

    /** UC04. */
    private void consultar() {
        Estante estante = aluno.getEstantePessoal();
        System.out.println("Leitura obrigatoria (" + estante.getEbooksObrigatorios().size()
                + " de " + Estante.LIMITE_OBRIGATORIOS + "):");
        imprimir(estante.getEbooksObrigatorios());
        System.out.println("Leitura livre (" + estante.getEbooksLivres().size()
                + " de " + Estante.LIMITE_LIVRES + "):");
        imprimir(estante.getEbooksLivres());
    }

    private void verCatalogo() {
        System.out.println("Catalogo do semestre:");
        for (Ebook ebook : biblioteca.getCatalogo().listarEbooks()) {
            System.out.println("  " + ebook + " - "
                    + (ebook.estaDisponivel() ? "disponivel" : "sem licenca livre"));
        }
    }

    private void imprimir(List<Ebook> ebooks) {
        if (ebooks.isEmpty()) {
            System.out.println("  (nenhum)");
            return;
        }
        for (Ebook ebook : ebooks) {
            System.out.println("  " + ebook);
        }
    }

    private PeriodoAcesso periodoAbertoOuAviso() {
        PeriodoAcesso periodo = biblioteca.getCalendario().periodoAberto();
        if (periodo == null) {
            System.out.println("Fora do periodo de acesso: a estante so pode ser alterada "
                    + "durante a janela definida pela biblioteca.");
        }
        return periodo;
    }
}
