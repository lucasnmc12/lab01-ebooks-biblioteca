package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Titulos licenciados no semestre. Como e o catalogo que detem a lista
 * de eBooks, e ele quem avalia a renovacao e remove os nao renovados.
 */
public class Catalogo {

    private static final int MINIMO_ALUNOS_RENOVACAO = 3;

    private List<Ebook> ebooks = new ArrayList<>();

    public void adicionarEbook(Ebook ebook) {
        if (ebooks.contains(ebook)) {
            throw new IllegalStateException("O eBook " + ebook.getId() + " ja esta no catalogo");
        }
        ebooks.add(ebook);
    }

    public void removerEbook(Ebook ebook) {
        if (!ebooks.remove(ebook)) {
            throw new IllegalStateException("O eBook " + ebook.getId() + " nao esta no catalogo");
        }
    }

    public List<Ebook> listarEbooks() {
        return new ArrayList<>(ebooks);
    }

    public Ebook buscarPorId(String id) {
        for (Ebook ebook : ebooks) {
            if (ebook.getId().equalsIgnoreCase(id)) {
                return ebook;
            }
        }
        return null;
    }

    public List<Ebook> buscarPorTitulo(String trecho) {
        List<Ebook> encontrados = new ArrayList<>();
        for (Ebook ebook : ebooks) {
            if (ebook.getTitulo().toLowerCase().contains(trecho.toLowerCase())) {
                encontrados.add(ebook);
            }
        }
        return encontrados;
    }

    /**
     * Marca cada eBook do catalogo para renovacao ou remocao, conforme
     * o numero de alunos que o adicionaram durante o periodo.
     */
    public void avaliarRenovacao(PeriodoAcesso periodo, RegistroDeUso registro) {
        if (periodo == null) {
            throw new IllegalArgumentException("E preciso informar o periodo avaliado");
        }
        if (!periodo.estaEncerrado()) {
            throw new IllegalStateException(
                    "A renovacao so pode ser avaliada com o periodo de acesso encerrado");
        }
        for (Ebook ebook : ebooks) {
            int alunos = registro.contarAlunosDistintos(ebook, periodo);
            ebook.marcarRenovacao(alunos >= MINIMO_ALUNOS_RENOVACAO
                    ? StatusRenovacao.RENOVAR
                    : StatusRenovacao.NAO_RENOVAR);
        }
    }

    /** Retira do catalogo os titulos marcados como nao renovados. */
    public List<Ebook> removerNaoRenovados() {
        List<Ebook> removidos = new ArrayList<>();
        for (Ebook ebook : new ArrayList<>(ebooks)) {
            if (ebook.getStatusRenovacao() == StatusRenovacao.NAO_RENOVAR) {
                ebooks.remove(ebook);
                removidos.add(ebook);
            }
        }
        return removidos;
    }

    /** Proximo identificador livre, no formato EB001. */
    public String proximoId() {
        int maior = 0;
        for (Ebook ebook : ebooks) {
            try {
                maior = Math.max(maior, Integer.parseInt(ebook.getId().substring(2)));
            } catch (NumberFormatException | IndexOutOfBoundsException ignorado) {
                // identificador fora do padrao EBnnn nao entra na contagem
            }
        }
        return String.format("EB%03d", maior + 1);
    }

    public static int getMinimoAlunosRenovacao() {
        return MINIMO_ALUNOS_RENOVACAO;
    }
}
