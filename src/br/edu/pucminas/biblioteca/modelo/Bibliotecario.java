package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;
import java.util.List;

/**
 * Membro da equipe da biblioteca. Administra o catalogo e o calendario
 * de periodos de acesso, mas nao concentra as regras: delega ao
 * Catalogo, ao CalendarioAcademico e ao RegistroDeUso.
 */
public class Bibliotecario extends Usuario {

    private String registroFuncional;

    public Bibliotecario(String id, String nome, String senha, String registroFuncional) {
        super(id, nome, senha);
        this.registroFuncional = registroFuncional;
    }

    /** Recria um bibliotecario lido do arquivo, cuja senha ja esta em forma de hash. */
    public static Bibliotecario comSenhaHash(String id, String nome, String senhaHash,
                                             String registroFuncional) {
        Bibliotecario bibliotecario = new Bibliotecario(id, nome, "", registroFuncional);
        bibliotecario.definirSenhaHash(senhaHash);
        return bibliotecario;
    }

    /** Cadastra o titulo no catalogo ja com a licenca de uso definida. */
    public Ebook cadastrarEbook(Catalogo catalogo, String titulo, String editora,
                                FormatoArquivo formato, Categoria categoria,
                                int limiteAcessosSimultaneos) {
        Ebook ebook = new Ebook(catalogo.proximoId(), titulo, editora, formato, categoria,
                new Licenca(limiteAcessosSimultaneos));
        catalogo.adicionarEbook(ebook);
        return ebook;
    }

    public void definirLicenca(Ebook ebook, int limite) {
        ebook.getLicenca().definirLimiteAcessos(limite);
    }

    public List<Aluno> consultarAlunosComEbook(RegistroDeUso registro, Ebook ebook) {
        return registro.listarAlunosComEbook(ebook);
    }

    public PeriodoAcesso abrirPeriodoAcesso(CalendarioAcademico calendario,
                                            LocalDate inicio, LocalDate fim) {
        return calendario.abrirPeriodo(inicio, fim);
    }

    public void encerrarPeriodoAcesso(CalendarioAcademico calendario) {
        calendario.encerrarPeriodoAberto();
    }

    public String getRegistroFuncional() {
        return registroFuncional;
    }
}
