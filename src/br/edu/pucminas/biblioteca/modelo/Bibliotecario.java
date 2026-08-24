package br.edu.pucminas.biblioteca.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Ebook cadastrarEbook(String titulo, String editora,
                                FormatoArquivo formato, Categoria categoria) {
        // TODO: implementar na Sprint 3
        return null;
    }

    public void definirLicenca(Ebook ebook, int limite) {
        // TODO: implementar na Sprint 3
    }

    public List<Aluno> consultarAlunosComEbook(Ebook ebook) {
        // TODO: implementar na Sprint 3, delegando ao RegistroDeUso
        return new ArrayList<>();
    }

    public PeriodoAcesso abrirPeriodoAcesso(LocalDate inicio, LocalDate fim) {
        // TODO: implementar na Sprint 3, delegando ao CalendarioAcademico
        return null;
    }

    public void encerrarPeriodoAcesso(PeriodoAcesso periodo) {
        // TODO: implementar na Sprint 3
    }

    public String getRegistroFuncional() {
        return registroFuncional;
    }
}
