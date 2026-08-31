# Roteiro Sprint 3 (Lab01S03)

Sistema de Gestão de eBooks da Biblioteca Universitária

Vencimento: segunda-feira, 10:40. Disponível de 24 Ago, 10:30 até 31 Ago, 10:40.

| Informação | Valor |
| --- | --- |
| Sprint | Lab01S03 |
| Valor | 7,0 pontos |
| Entregáveis | Protótipo funcional (funcionalidades, interface e persistência) |
| Mínimo por integrante | 1 implementação de funcionalidade |

Este roteiro complementa o enunciado do Laboratório 01 (Sistema de Gestão de eBooks da Biblioteca Universitária) e a Rubrica Individual Lab01S03. Consulte esses dois documentos para os critérios completos de avaliação, penalidades e prazos.

Nota de transparência sobre uso de IA: em conformidade com a política de uso responsável de Inteligência Artificial da disciplina, informa-se que este roteiro foi produzido com apoio da ferramenta Claude, da empresa Anthropic, utilizada na geração e revisão de texto, dos diagramas em PlantUML e dos exemplos de código. O conteúdo foi revisado pelo professor antes da disponibilização aos alunos.

## 1. Objetivo da Sprint

Nesta sprint, o grupo corrige os diagramas conforme o feedback da Sprint 2 e implementa o protótipo funcional do sistema: as principais funcionalidades usáveis, com interface e persistência de dados. Cada integrante deve ficar responsável por, no mínimo, 1 implementação de funcionalidade. Ao final desta sprint, o grupo também confirma que cada integrante contribuiu, ao longo de todo o projeto, com pelo menos 1 caso de uso, 1 história de usuário, 1 agregação de classes e 1 implementação de funcionalidade.

A rubrica individual desta sprint distribui os 7,0 pontos assim:

| Critério | Peso |
| --- | --- |
| Funcionalidade implementada pelo integrante | 4,0 |
| Correção das observações das sprints anteriores (parte do integrante) | 1,0 |
| Atualização semanal do documento de contribuição | 2,0 |

## 2. Pré-requisitos

- Sprint 2 concluída: Diagrama de Classes e projeto Java com classes, atributos e stubs criados.
- Feedback da apresentação da Sprint 2 anotado.
- Ambiente Java funcionando (compilação e execução testadas na IDE).

## 3. Conceitos-chave

### 3.1 O que é um protótipo funcional

Um protótipo funcional não precisa ser o sistema completo, mas precisa ser executável de ponta a ponta nas funcionalidades escolhidas: o usuário consegue realizar a ação (por exemplo, adicionar um eBook à estante) e ver o resultado (o eBook aparece na consulta da estante), com os dados persistidos entre execuções.

### 3.2 Persistência em arquivo

O enunciado permite persistência em arquivo (não é necessário banco de dados). Algumas opções, da mais simples à mais estruturada:

| Estratégia | Vantagem | Quando considerar |
| --- | --- | --- |
| Texto simples, uma informação por linha | Simples de implementar e depurar | Protótipos pequenos, poucos campos |
| CSV (valores separados por vírgula) | Fácil de abrir em planilha para conferência | Quando há várias colunas de dados |
| Serialização de objetos Java (`Serializable`) | Grava o objeto Java diretamente | Quando não é preciso ler o arquivo manualmente |

Para este roteiro, usamos texto simples, por ser o mais fácil de entender e depurar.

### 3.3 Interface em linha de comando

Uma interface em linha de comando (CLI) apresenta um menu de opções no terminal e lê a escolha do usuário em um laço (loop), repetindo até que o usuário decida sair.

## 4. Passo a Passo

### Passo 1. Revisar o feedback da Sprint 2

Anotem, em grupo, o que o professor pediu para corrigir nos diagramas ou no projeto estrutural.

### Passo 2. Corrigir os diagramas de casos de uso e de classes

Apliquem as correções nos arquivos `.puml`, gerem novamente as imagens e substituam as anteriores.

```bash
git add docs/diagramas/
git commit -m "fix: corrige diagramas conforme feedback da apresentacao da sprint 2"
git push origin main
```

### Passo 3. Revisar a distribuição de funcionalidades

Cada integrante deve ficar responsável por, no mínimo, 1 funcionalidade. Antes de programar, confiram no board se, somando as três sprints, cada integrante já tem registrado: 1 caso de uso, 1 história de usuário, 1 agregação de classes e agora vai registrar 1 implementação de funcionalidade. Se algum integrante ainda não atingiu o mínimo em alguma sprint anterior, resolvam isso antes de prosseguir.

### Passo 4. Implementar a lógica de negócio da funcionalidade

Substitua o stub criado na Sprint 2 pela implementação completa. O construtor e os demais métodos de `Aluno` permanecem exatamente como criados na Sprint 2; aqui, apenas o corpo do método `adicionarEBook` é preenchido, usando o `contarEBooks()` já implementado na sprint anterior.

```java
public void adicionarEBook(EBook ebook) {
    if (estante.contarEBooks() >= 6) {
        throw new IllegalStateException(
            "Limite de eBooks na estante foi atingido");
    }
    estante.adicionar(ebook);
}
```

### Passo 5. Implementar as regras de negócio do sistema

Releiam a descrição do sistema no enunciado e implementem as regras explícitas, por exemplo o limite de acessos simultâneos por eBook. A classe `Licenca` já foi criada na Sprint 2 com os atributos `limiteAcessosSimultaneos` e `acessosAtivos`; aqui, adicione um construtor que inicialize o limite em 60 (conforme o enunciado) e implemente por completo os métodos `temVagaDisponivel()` e `registrarAcesso()`:

```java
package br.edu.pucminas.biblioteca.modelo;

public class Licenca {
    private int limiteAcessosSimultaneos;
    private int acessosAtivos;

    public Licenca() {
        this.limiteAcessosSimultaneos = 60;
        this.acessosAtivos = 0;
    }

    public boolean temVagaDisponivel() {
        return acessosAtivos < limiteAcessosSimultaneos;
    }

    public void registrarAcesso() {
        if (!temVagaDisponivel()) {
            throw new IllegalStateException(
                "Numero maximo de acessos simultaneos atingido");
        }
        acessosAtivos++;
    }
}
```

### Passo 6. Implementar a persistência em arquivo

Exemplo simples de gravação e leitura de eBooks em um arquivo de texto, um eBook por linha, separando os campos por ponto e vírgula:

```java
package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.EBook;
import java.io.*;
import java.util.*;

public class EBookRepositorioArquivo {
    private static final String ARQUIVO = "dados/ebooks.txt";

    public void salvar(List<EBook> ebooks) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (EBook ebook : ebooks) {
                escritor.println(ebook.getTitulo() + ";" + ebook.getEditora()
                    + ";" + ebook.getFormato() + ";" + ebook.getCategoria());
            }
        }
    }

    public List<EBook> carregar() throws IOException {
        List<EBook> ebooks = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return ebooks;
        }
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(";");
                ebooks.add(new EBook(campos[0], campos[1], campos[2], campos[3]));
            }
        }
        return ebooks;
    }
}
```

Crie a pasta `dados/` na raiz do projeto antes de executar o programa pela primeira vez. Como esse arquivo é gerado em tempo de execução (não é código-fonte), adicione-o ao `.gitignore` para não versionar dados de teste por engano:

```bash
echo "dados/" >> .gitignore
git add .gitignore
git commit -m "chore: ignora pasta de dados persistidos em tempo de execucao"
```

### Passo 7. Implementar a interface de linha de comando

Exemplo de um menu simples que chama as funcionalidades implementadas:

```java
package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.EBook;
import br.edu.pucminas.biblioteca.modelo.Estante;
import br.edu.pucminas.biblioteca.persistencia.EBookRepositorioArquivo;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("1. Adicionar eBook a estante");
            System.out.println("2. Consultar estante");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao;
            try {
                opcao = Integer.parseInt(leitor.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
                continue;
            }
            try {
                switch (opcao) {
                    case 1:
                        // chamar aluno.adicionarEBook(ebook)
                        break;
                    case 2:
                        // chamar estante.listar()
                        break;
                    case 3:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opcao invalida, tente novamente.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Nao foi possivel concluir a acao: " + e.getMessage());
            }
        }
        leitor.close();
    }
}
```

O `try/catch` em torno do `switch` evita que uma regra de negócio violada (como o limite de eBooks da estante, ou o limite de acessos simultâneos da `Licenca`) derrube o programa inteiro. Em vez de um stack trace na tela, o usuário recebe uma mensagem compreensível.

### Passo 8. Integrar login e autenticação

Implemente o método `autenticar` criado como stub na Sprint 2, validando a senha informada contra a senha cadastrada do usuário.

### Passo 9. Testar manualmente cada funcionalidade

Antes de considerar a funcionalidade pronta, teste o fluxo completo pelo menu:

- A ação é executada sem erros no caminho feliz (dados válidos).
- A ação trata corretamente uma entrada inválida (por exemplo, opção de menu inexistente).
- O dado persiste após fechar e reabrir o programa.
- As regras de negócio (limites, mínimos) são respeitadas.

### Passo 10. Revisar tratamento de erros e exceções

Garanta que exceções esperadas (limite atingido, arquivo não encontrado na primeira execução, entrada inválida do usuário) sejam tratadas de forma amigável, sem que o programa trave.

### Passo 11. Atualizar o board e o documento de contribuição da semana

`docs/contribuicoes/nome-do-integrante-1/sprint3.md`

```markdown
## Semana 1
Contribuicao: implementei a funcionalidade "Adicionar eBook a estante",
incluindo a validacao do limite de 6 eBooks.
Decisoes: optei por lancar uma excecao (IllegalStateException) quando o
limite e atingido, para que a interface possa capturar e exibir uma
mensagem clara ao usuario.

## Semana 2
Contribuicao: implementei a persistencia em arquivo texto (leitura e
escrita de eBooks) e integrei essa funcionalidade ao menu principal,
com tratamento de excecao.
Decisoes: usei ponto e virgula como separador de campos no arquivo,
por ser simples de ler e de depurar manualmente durante os testes.
```

Observação: se a sprint durar mais de uma semana, acrescente um novo bloco `## Semana N` ao mesmo arquivo a cada atualização, sem apagar os blocos anteriores.

### Passo 12. Commitar e enviar as alterações

Recomenda-se um commit por funcionalidade concluída, não um único commit gigante ao final da sprint. Como na Sprint 2, use uma branch por integrante ou por funcionalidade, e junte o trabalho por Pull Request.

```bash
git checkout main
git pull origin main
git checkout -b feature/adicionar-ebook-estante

git add src/
git commit -m "feat: implementa adicao de eBook a estante do aluno"

git add src/
git commit -m "feat: implementa persistencia de eBooks em arquivo"

git add src/
git commit -m "feat: implementa interface de linha de comando"

git add docs/contribuicoes/nome-do-integrante-1/sprint3.md
git commit -m "docs: registra contribuicao semanal (sprint3)"

git push origin feature/adicionar-ebook-estante
```

### Passo 13. Revisar o repositório por completo

Confira, com o grupo, que o README está atualizado, os diagramas das três sprints estão corretos e o projeto compila e executa sem erros a partir de um clone limpo do repositório.

```bash
git clone https://github.com/<usuario-ou-organizacao>/lab01-ebooks-biblioteca.git pasta-teste
cd pasta-teste

# compilar todas as classes do projeto
mkdir -p bin
javac -d bin $(find src -name "*.java")

# executar a classe MenuPrincipal (pacote br.edu.pucminas.biblioteca, conforme os exemplos)
java -cp bin br.edu.pucminas.biblioteca.MenuPrincipal
```

Se o grupo estiver usando Maven ou Gradle, utilize os comandos equivalentes da ferramenta (`mvn compile exec:java` ou `./gradlew run`, por exemplo) em vez de `javac`/`java` diretamente.

### Passo 14. Comparar o protótipo com os modelos das sprints anteriores

Preparem, em grupo, uma breve comparação entre o que foi modelado nas Sprints 1 e 2 e o que foi de fato implementado, destacando eventuais mudanças e o motivo de cada uma. Essa comparação é parte da apresentação final exigida no enunciado.

### Passo 15. Revisão de qualidade de código

Antes da entrega, revisem juntos: nomes de classes, atributos e métodos estão claros, não há código morto (métodos ou variáveis não usados) nem comentários TODO esquecidos, e a indentação está consistente.

### Passo 16. Preparar a apresentação final

Confira o checklist da seção 6. Cada integrante deve estar pronto para demonstrar e justificar a funcionalidade sob sua responsabilidade, incluindo as decisões técnicas tomadas durante a implementação.

## 5. Erros Comuns e Dicas

- Funcionalidade que só funciona no caminho feliz. Teste também os casos de erro (limite atingido, entrada inválida).
- Persistência que não sobrevive ao reinício do programa. Um erro comum é guardar os dados apenas em memória e esquecer de gravar em arquivo.
- Interface travando em loop infinito. Sempre valide a entrada do usuário antes de processá-la.
- Deixar TODO esquecidos no código. Revise o projeto inteiro procurando por comentários TODO antes da apresentação final.
- Não testar a partir de um clone limpo. O que funciona na sua máquina pode falhar em outra, por exemplo por um caminho de arquivo escrito de forma fixa (hardcoded).
- Versionar dados de teste por engano. Sem um `.gitignore` para a pasta de dados persistidos, é fácil comitar arquivos gerados em tempo de execução que não deveriam estar no repositório.

## 6. Checklist Final (antes da apresentação)

Os itens abaixo usam a mesma redação dos critérios da Rubrica Individual Lab01S03.

| Critério da rubrica | Peso |
| --- | --- |
| Funcionalidade implementada pelo integrante | 4,0 |
| Correção das observações das sprints anteriores (parte do integrante) | 1,0 |
| Atualização semanal do documento de contribuição (`docs/contribuicoes/`) | 2,0 |
