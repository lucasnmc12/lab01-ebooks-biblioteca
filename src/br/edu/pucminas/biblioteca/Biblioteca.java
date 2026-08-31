package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.CalendarioAcademico;
import br.edu.pucminas.biblioteca.modelo.Catalogo;
import br.edu.pucminas.biblioteca.modelo.Disciplina;
import br.edu.pucminas.biblioteca.modelo.RegistroDeUso;
import br.edu.pucminas.biblioteca.modelo.SistemaEstatisticasUso;
import br.edu.pucminas.biblioteca.modelo.Usuario;
import br.edu.pucminas.biblioteca.persistencia.DisciplinaRepositorioArquivo;
import br.edu.pucminas.biblioteca.persistencia.EbookRepositorioArquivo;
import br.edu.pucminas.biblioteca.persistencia.EstanteRepositorioArquivo;
import br.edu.pucminas.biblioteca.persistencia.PeriodoAcessoRepositorioArquivo;
import br.edu.pucminas.biblioteca.persistencia.RegistroDeUsoRepositorioArquivo;
import br.edu.pucminas.biblioteca.persistencia.UsuarioRepositorioArquivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Monta o sistema a partir dos arquivos e o grava de volta. E a camada
 * de aplicacao: ela conhece todas as partes, mas nao decide nada.
 *
 * Isso e o que a distingue da SistemaGestaoEbooks removida na Sprint 2:
 * ali havia regra de negocio (a renovacao do catalogo) junto das listas;
 * aqui so existe montagem e gravacao, e cada regra continua na classe de
 * dominio que detem os dados dela.
 */
public class Biblioteca {

    private final EbookRepositorioArquivo ebookRepositorio = new EbookRepositorioArquivo();
    private final UsuarioRepositorioArquivo usuarioRepositorio = new UsuarioRepositorioArquivo();
    private final EstanteRepositorioArquivo estanteRepositorio = new EstanteRepositorioArquivo();
    private final PeriodoAcessoRepositorioArquivo periodoRepositorio =
            new PeriodoAcessoRepositorioArquivo();
    private final RegistroDeUsoRepositorioArquivo registroRepositorio =
            new RegistroDeUsoRepositorioArquivo();
    private final DisciplinaRepositorioArquivo disciplinaRepositorio =
            new DisciplinaRepositorioArquivo();

    private final SistemaEstatisticasUso estatisticas = new SistemaEstatisticasUso();

    private Catalogo catalogo;
    private CalendarioAcademico calendario;
    private RegistroDeUso registro;
    private List<Usuario> usuarios;
    private List<Disciplina> disciplinas;

    /** Le todos os arquivos e reconstroi as ligacoes entre os objetos. */
    public void carregar() throws IOException {
        catalogo = ebookRepositorio.carregar();
        usuarios = usuarioRepositorio.carregar();
        calendario = periodoRepositorio.carregar();
        disciplinas = disciplinaRepositorio.carregar(alunos(), catalogo);
        estanteRepositorio.carregar(alunos(), catalogo);
        registro = registroRepositorio.carregar(alunos(), catalogo, calendario, estatisticas);
    }

    public void salvar() throws IOException {
        ebookRepositorio.salvar(catalogo);
        usuarioRepositorio.salvar(usuarios);
        estanteRepositorio.salvar(alunos());
        periodoRepositorio.salvar(calendario);
        registroRepositorio.salvar(registro, calendario);
        disciplinaRepositorio.salvar(disciplinas, alunos());
    }

    /** Devolve o usuario quando o identificador existe e a senha confere. */
    public Usuario autenticar(String id, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(id) && usuario.autenticarUsuario(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Aluno> alunos() {
        List<Aluno> encontrados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Aluno aluno) {
                encontrados.add(aluno);
            }
        }
        return encontrados;
    }

    public boolean vazia() {
        return usuarios.isEmpty();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public CalendarioAcademico getCalendario() {
        return calendario;
    }

    public RegistroDeUso getRegistro() {
        return registro;
    }

    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Bibliotecario primeiroBibliotecario() {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Bibliotecario bibliotecario) {
                return bibliotecario;
            }
        }
        return null;
    }
}
