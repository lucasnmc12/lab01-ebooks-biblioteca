package br.edu.pucminas.biblioteca.modelo;

/**
 * Licenca de uso de um eBook. Controla quantos alunos podem acessar
 * o titulo ao mesmo tempo, respeitando o teto de 60 acessos.
 */
public class Licenca {

    public static final int LIMITE_MAXIMO_ACESSOS = 60;

    private int limiteAcessosSimultaneos;
    private int acessosAtivos;

    public Licenca(int limiteAcessosSimultaneos) {
        validarLimite(limiteAcessosSimultaneos);
        this.limiteAcessosSimultaneos = limiteAcessosSimultaneos;
        this.acessosAtivos = 0;
    }

    public boolean estaDisponivel() {
        return acessosAtivos < limiteAcessosSimultaneos;
    }

    /**
     * Ocupa uma licenca. Devolve false quando o titulo ja atingiu o
     * limite de acessos simultaneos.
     */
    public boolean adicionarAcesso() {
        if (!estaDisponivel()) {
            return false;
        }
        acessosAtivos++;
        return true;
    }

    public void liberarAcesso() {
        if (acessosAtivos > 0) {
            acessosAtivos--;
        }
    }

    public void definirLimiteAcessos(int limite) {
        validarLimite(limite);
        if (limite < acessosAtivos) {
            throw new IllegalArgumentException(
                    "O novo limite (" + limite + ") e menor que os "
                            + acessosAtivos + " acessos em uso");
        }
        this.limiteAcessosSimultaneos = limite;
    }

    private static void validarLimite(int limite) {
        if (limite <= 0 || limite > LIMITE_MAXIMO_ACESSOS) {
            throw new IllegalArgumentException(
                    "O limite de acessos simultaneos deve estar entre 1 e " + LIMITE_MAXIMO_ACESSOS);
        }
    }

    public int getLimiteAcessosSimultaneos() {
        return limiteAcessosSimultaneos;
    }

    public int getAcessosAtivos() {
        return acessosAtivos;
    }
}
