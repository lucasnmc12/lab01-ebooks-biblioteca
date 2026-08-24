# Contribuições — Lucas Nogueira

## Semana 1 (Sprint 2, Lab01S02)

### Contribuição

Restaurei o diagrama de casos de uso na versão que eu havia entregue no PR #9. Entre o fim da Sprint 1 e agora, o diagrama tinha perdido seis dos doze casos de uso, o ator `Usuário` e o ator secundário `Sistema de Estatísticas de Uso`. Voltei UC05, UC06, UC08, UC10, UC11 e UC12, as generalizações de ator e as dependências de `<<include>>` e `<<extend>>` associadas a eles.

Mantive tudo o que o Pedro produziu de novo no período: as histórias HU01 a HU04, que ele escreveu por cima dos placeholders que eu tinha deixado, e o `.gitignore` de Java que ele adicionou. O arquivo de contribuição dele não foi tocado.

Escrevi também HU05 e HU06, que estavam sem texto desde a Sprint 1. Não é a minha parte da distribuição, mas com os casos de uso de volta no diagrama eles não podiam ficar sem história correspondente. Sinalizei isso no cabeçalho do arquivo de histórias, para não parecer que estou assumindo a parte do outro integrante.

A imagem `casos-de-uso.png` foi recuperada do próprio histórico do Git, do commit em que ela foi gerada, então ela corresponde exatamente ao `.puml` restaurado — não há divergência entre fonte e imagem.

### Decisões

Recuperei a generalização de `Usuário` porque a descrição diz que *todos* os usuários do sistema têm senha. Sem o ator genérico, a mesma informação vira duas associações repetidas com "Realizar login" e o diagrama deixa de expressar que existe um conceito de usuário acima de aluno e bibliotecário. Esse conceito volta na Sprint 2 como superclasse no diagrama de classes, então prefiro que ele já apareça na análise.

Recuperei "Notificar estatísticas de uso" e o ator secundário porque a descrição é literal nesse ponto: o sistema de estatísticas de uso é notificado pelo sistema de gestão de eBooks a cada eBook adicionado. É um sistema externo que troca informação com o nosso, que é a definição de ator secundário. Tirar isso do diagrama apaga um requisito que está escrito no enunciado. Sei que o Pedro registrou ter perguntado ao professor sobre a necessidade desse ator; vou levar o ponto na apresentação para fechar a interpretação em vez de ficar indo e voltando no diagrama.

Recuperei "Definir licença de uso" separada de "Cadastrar eBook". O teto de 60 acessos simultâneos é uma regra de licenciamento que muda por conta própria, independente dos dados bibliográficos do título, e alguém precisa informar esse número. O único ator que administra o acervo é o bibliotecário. Na Sprint 2 isso vira uma classe de licença com o seu próprio ciclo de vida, e o `<<include>>` de UC07 para UC08 é o que já antecipa essa separação.

Recuperei "Manter período de acesso" e "Avaliar renovação do catálogo" pelo mesmo raciocínio: a descrição afirma que existem períodos de acesso e que a renovação é decidida ao final deles. O enunciado não diz quem executa, e essa é a lacuna que motivou a exclusão. Só que omitir o caso de uso não resolve a lacuna, apenas esconde. Assumo a interpretação de que a responsabilidade é da equipe da biblioteca e levo as duas para confirmar com o professor. Se a renovação for mesmo automática, é uma linha de mudança no diagrama; o custo de ter modelado é baixo, o de ter omitido é perder o requisito.

Mantive o `<<extend>>` de "Remover eBook do catálogo" para "Avaliar renovação do catálogo". A remoção é condicional, só acontece quando o título fica abaixo de 3 alunos ao final do período. Um `<<include>>` afirmaria que toda avaliação remove algum eBook, o que não é verdade.

"Verificar disponibilidade de licença" é o item em que reconheço o argumento contrário. Ele está mais próximo de uma regra de negócio do que de uma intenção do ator, e eu não teria como defender o contrário com convicção. Restaurei porque o bloqueio ao atingir o limite é um comportamento que o aluno percebe na hora de adicionar o título, mas é o primeiro ponto que pretendo confirmar na apresentação, e não me incomodo em remover se o professor entender que é detalhe de implementação.

Não desfiz nem reescrevi o histórico. A restauração está numa branch separada, `sprint2/restauracao-diagrama-lucas`, para entrar por Pull Request. Os commits de exclusão do Pedro continuam no histórico e o arquivo de contribuição dele continua descrevendo o raciocínio que ele seguiu. As duas leituras precisam ficar rastreáveis, porque a avaliação é individual e nenhum de nós deve perder o registro do que decidiu.

### Próximos passos

Alinhar a restauração com o Pedro antes de mesclar, levar os três pontos em aberto (ator de estatísticas, responsável pelos períodos de acesso e renovação, natureza do UC05) para a apresentação e começar o diagrama de classes da Sprint 2, que é onde as licenças, os períodos e a estante deixam de ser caixas do diagrama de casos de uso e viram estrutura de verdade.

### Nota de transparência sobre uso de IA

Usei o Claude, da Anthropic, como apoio nesta sprint. A ferramenta me ajudou a levantar e comparar os commits das duas versões do diagrama, a aplicar a restauração no repositório e a revisar a redação deste documento e do arquivo de histórias. A decisão de restaurar cada caso de uso, os argumentos registrados acima e os pontos que ficaram em aberto são meus, e sei defender cada um deles.

---

## Semana 2 (Sprint 2, Lab01S02)

### Contribuição

Assumi o bloco do acervo no diagrama de classes: `Bibliotecario`, `Catalogo`, `Ebook`, `Licenca`, `PeriodoAcesso`, `CalendarioAcademico`, `RegistroDeUso`, `AdicaoEbook` e os três enums. O Pedro tinha modelado o diagrama inteiro e registrou na contribuição dele que travou num ponto: não conseguiu cobrir os casos de uso 10, 11 e 12 sem criar a classe `SistemaGestaoEbooks`, que ele mesmo chamou de God Class. Peguei esse problema.

Também criei o projeto Java em `src/br/edu/pucminas/biblioteca/modelo/`, com as quinze classes do diagrama, cada uma com atributos privados, construtor e stub dos métodos. O projeto compila.

### Decisões

Desfiz a `SistemaGestaoEbooks` aplicando o princípio do especialista na informação: a responsabilidade vai para a classe que já detém os dados necessários para cumpri-la. O sintoma da God Class era ela ter composição com cinco outras classes e ainda executar a regra de renovação, ou seja, mudaria por três motivos diferentes.

`avaliarRenovacao()` e `removerNaoRenovados()` foram para o `Catalogo`, que é quem tem a lista de eBooks do semestre. A regra dos 3 alunos virou a constante `MINIMO_ALUNOS_RENOVACAO` dentro dele, em vez de ficar espalhada como número solto.

A contagem de alunos foi para o `RegistroDeUso`, que reúne as `AdicaoEbook` e portanto é a única classe capaz de responder quantos alunos distintos ficaram com um título num período. De quebra, esse mesmo registro responde o UC09, consultar quais alunos estão com um eBook, que é um caso de uso meu desde a Sprint 1 e que antes não tinha onde morar.

Criei o `CalendarioAcademico` para guardar os períodos de acesso. Sem ele, a lista de períodos ficava na God Class e não havia como garantir o critério da HU10, de que não existem dois períodos abertos ao mesmo tempo. Com a lista concentrada numa classe só, essa verificação tem casa.

As listas de alunos, bibliotecários e disciplinas que estavam na God Class eu simplesmente removi. Elas não são modelagem de domínio, são armazenamento, e o roteiro pede para deixar infraestrutura de fora nesta sprint. Na Sprint 3 elas voltam como persistência em arquivo.

Corrigi a dependência das estatísticas de uso. Estava saindo do `Aluno`, e a descrição do sistema diz que quem notifica o sistema de estatísticas é o sistema de gestão. Passou a sair do `RegistroDeUso`, que é quem efetivamente registra a adição, com o estereótipo `<<notifica>>`.

Troquei `Date` por `LocalDate`. `java.util.Date` é mutável e tem boa parte da API obsoleta; `LocalDate` representa data de calendário, que é exatamente o caso dos períodos de acesso.

Mantive os enums que o Pedro criou e estendi o uso: o método `cadastrarEbook` do `Bibliotecario` recebia `categoria: String` mesmo com o enum `Categoria` existindo. Um enum só protege de valor inválido se ele for usado na assinatura.

Sobre o projeto Java: criei também as classes do lado do aluno (`Usuario`, `Aluno`, `Estante`, `Disciplina`) porque sem elas o projeto não compila, e um `src/` que não compila não serve para a Sprint 3. Segui exatamente a modelagem do Pedro, sem alterar nada, e marquei cada uma dessas classes com um comentário indicando que a modelagem é dele. A implementação delas na Sprint 3 continua sendo responsabilidade dele.

### Pontos a alinhar com o Pedro

A associação `Estante o-- Ebook` está com multiplicidade `0..6`, o que garante o teto mas não expressa a divisão de 4 obrigatórios e 2 livres. Dá para separar em duas associações, `0..4` e `0..2`. É modelagem dele, então não mexi, mas acho que vale a mudança.

### Nota de transparência sobre uso de IA

Usei o Claude, da Anthropic, como apoio nesta semana. A ferramenta me ajudou a analisar o diagrama entregue pelo Pedro e apontar as inconsistências, a escrever o código das classes Java e a revisar a redação deste documento. A decisão de assumir o bloco do acervo, o caminho para desfazer a God Class e a distribuição de cada responsabilidade entre as classes foram discutidos e decididos por mim, e sei explicar cada um deles.
