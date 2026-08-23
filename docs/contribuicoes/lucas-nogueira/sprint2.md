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
