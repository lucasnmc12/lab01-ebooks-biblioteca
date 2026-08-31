package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Grava alunos e bibliotecarios em dados/usuarios.txt.
 *
 * O que vai para o arquivo e o resumo SHA-256 da senha, nunca a senha
 * digitada. Ainda assim, o arquivo guarda dado pessoal e num sistema real
 * exigiria controle de acesso e registro de auditoria.
 */
public class UsuarioRepositorioArquivo {

    private static final String ARQUIVO = "usuarios.txt";
    private static final int CAMPOS = 5;

    public void salvar(List<Usuario> usuarios) throws IOException {
        List<String> linhas = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Aluno aluno) {
                linhas.add(ArquivoDados.juntar("ALUNO", aluno.getId(), aluno.getNome(),
                        aluno.getSenhaHash(), aluno.getMatricula()));
            } else if (usuario instanceof Bibliotecario bibliotecario) {
                linhas.add(ArquivoDados.juntar("BIBLIOTECARIO", bibliotecario.getId(),
                        bibliotecario.getNome(), bibliotecario.getSenhaHash(),
                        bibliotecario.getRegistroFuncional()));
            }
        }
        ArquivoDados.gravarLinhas(ARQUIVO, linhas);
    }

    public List<Usuario> carregar() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        for (String linha : ArquivoDados.lerLinhas(ARQUIVO)) {
            String[] campos = ArquivoDados.separar(linha, CAMPOS);
            if ("ALUNO".equals(campos[0])) {
                usuarios.add(Aluno.comSenhaHash(campos[1], campos[2], campos[3], campos[4]));
            } else {
                usuarios.add(Bibliotecario.comSenhaHash(campos[1], campos[2], campos[3], campos[4]));
            }
        }
        return usuarios;
    }
}
