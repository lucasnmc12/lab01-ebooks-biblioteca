package br.edu.pucminas.biblioteca.modelo;

/**
 * Fachada do sistema externo de estatisticas de uso. O sistema de gestao
 * apenas o notifica a cada eBook adicionado a uma estante; a consulta das
 * estatisticas acontece no proprio sistema externo.
 *
 * No prototipo a notificacao e apenas registrada no console, o suficiente
 * para demonstrar que a integracao acontece no momento certo.
 */
public class SistemaEstatisticasUso {

    public void registrarAdicao(Aluno aluno, Ebook ebook) {
        System.out.println("[ESTATISTICAS] " + aluno.getMatricula()
                + " adicionou \"" + ebook.getTitulo() + "\"");
    }
}
