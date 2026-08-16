LAB01, Laboratório 01, 15 pontos

Sistema de Gestão de eBooks da Biblioteca Universitária

Neste laboratório, vamos projetar um Sistema de Gestão de eBooks para a Biblioteca de uma Universidade. Leia atentamente a descrição fornecida pelo Product Owner e elabore o projeto detalhado do sistema.

Descrição do Sistema
Uma universidade pretende oferecer aos alunos um acervo de livros digitais (eBooks). A equipe da biblioteca cadastra os eBooks disponíveis a cada semestre e mantém as informações sobre os eBooks, os bibliotecários e os alunos.

Cada eBook possui um título, uma editora, um formato de arquivo (por exemplo, PDF ou EPUB) e pertence a uma categoria (por exemplo, literatura, técnico ou periódico).

Cada eBook tem uma licença de uso que define quantos alunos podem acessá-lo ao mesmo tempo. O número máximo de acessos simultâneos a um mesmo eBook é de 60. Quando esse número é atingido, novos acessos àquele eBook ficam bloqueados até que uma das licenças em uso seja liberada.

Os alunos podem adicionar à sua estante pessoal até 4 eBooks de leitura obrigatória, indicados pela disciplina, e mais 2 eBooks de leitura livre, de escolha do próprio aluno.

Há períodos de acesso ao longo do semestre, durante os quais um aluno pode entrar no sistema para adicionar eBooks à sua estante pessoal e, ou remover eBooks adicionados anteriormente.

Um eBook só permanece no catálogo licenciado no semestre seguinte se, ao final do período de acesso, tiver sido adicionado à estante de pelo menos 3 alunos. Caso esse número mínimo não seja atingido, a licença do eBook não é renovada e o título é removido do catálogo.

Sempre que um aluno adiciona um eBook à sua estante, o sistema de estatísticas de uso é notificado pelo sistema de gestão de eBooks, de modo que a biblioteca possa acompanhar quais títulos são mais utilizados pelos alunos.

Os bibliotecários podem acessar o sistema para saber quais alunos têm determinado eBook em sua estante.

Todos os usuários do sistema têm senhas que são utilizadas para validação do respectivo login.

Processo de Desenvolvimento
Para o passo a passo detalhado de cada sprint, incluindo os diagramas em PlantUML e as instruções de commit no repositório, consulte o roteiro hands-on correspondente (Roteiro Sprint 1, Roteiro Sprint 2 e Roteiro Sprint 3), disponibilizado no Canvas.

Lab01S01 (4 pontos), Roteiro Sprint 1

Modelo de Análise: Diagrama de Casos de Uso referente ao sistema, com descrição em Histórias de Usuário em Markdown (.md), no README do repositório, com URL enviado no Canvas.
Distribuição de tarefas por integrante: nesta sprint, cada integrante deverá ficar responsável pela criação de, no mínimo, 1 caso de uso e 1 história de usuário, com a distribuição registrada no repositório. Essa distribuição inicia o acompanhamento que se estende pelas três sprints, ao final das quais cada integrante deverá ter contribuído com, no mínimo, 1 caso de uso, 1 história de usuário, 1 agregação de classes e 1 implementação de funcionalidade.
Lab01S02 (4 pontos), Roteiro Sprint 2

Correção dos diagramas desenvolvidos, na parte sob responsabilidade de cada integrante.
Projeto Estrutural: Diagrama de Classes referente ao sistema, criação do projeto Java contendo classes, atributos e stub dos métodos modelados.
Distribuição de tarefas por integrante: nesta sprint, cada integrante deverá ficar responsável pela criação de, no mínimo, 1 agregação de classes, com a distribuição registrada no repositório.
Lab01S03 (7 pontos), Roteiro Sprint 3

Correção dos diagramas desenvolvidos, na parte sob responsabilidade de cada integrante.
Implementação do protótipo do sistema (principais funcionalidades usáveis, com interface e persistência). Observação: a interface pode ser em linha de comando e a persistência em arquivos.
Distribuição de tarefas por integrante: nesta sprint, cada integrante deverá ficar responsável pela implementação de, no mínimo, 1 funcionalidade, com a distribuição registrada no repositório. Ao final desta sprint, o grupo deverá confirmar que cada integrante contribuiu, ao longo de todo o projeto, com pelo menos 1 caso de uso, 1 história de usuário, 1 agregação de classes e 1 implementação de funcionalidade.
A distribuição de tarefas registrada em cada sprint será conferida durante a apresentação individual de cada integrante, conforme os critérios descritos a seguir.

Prazo final: consulte o cronograma da disciplina disponibilizado no Canvas.

Valor total: 15 pontos   |   Desconto de 1,0 ponto por dia de atraso

Ferramentas de Modelagem (PlantUML Obrigatório)
Todos os diagramas UML do projeto (Diagrama de Casos de Uso, Diagrama de Classes e demais diagramas eventualmente necessários) deverão ser obrigatoriamente construídos no formato PlantUML. Os arquivos-fonte (.puml) devem ser versionados na pasta docs/diagramas/ do repositório GitHub, junto com a imagem exportada de cada diagrama, conforme detalhado nos roteiros hands-on de cada sprint.

Ferramentas sugeridas para a criação dos diagramas em PlantUML:

PlantUML Online: https://plantuml.online/Links to an external site.
PlantUML, site oficial: https://plantuml.com/Links to an external site.
PlantText: https://www.planttext.com/Links to an external site.
Documentação oficial: https://plantuml.com/guideLinks to an external site.
Board do Projeto e Registro Semanal de Contribuições
O grupo deverá manter um board no GitHub Projects atualizado, refletindo o andamento das tarefas ao longo de todo o projeto.

Cada integrante deverá atualizar, semanalmente e antes da apresentação, um arquivo em Markdown na pasta docs/contribuicoes/ do repositório, dentro de uma subpasta com o nome do integrante (por exemplo, docs/contribuicoes/nome-do-integrante/). O nome do arquivo deverá ser o mesmo nome da sprint em andamento: sprint1.md, sprint2.md ou sprint3.md.

Nesse arquivo, o aluno deverá registrar qual foi sua contribuição da semana e quais foram suas principais decisões em relação ao processo. Quando a sprint se estender por mais de uma semana, o aluno deverá acrescentar um novo registro ao mesmo arquivo a cada atualização semanal, mantendo o histórico das semanas anteriores.

A atualização semanal do board e do arquivo de contribuição passa a compor diretamente a rubrica individual de cada sprint, descrita mais adiante, e não apenas as penalidades de apresentação.

Apresentações Semanais e Critérios Obrigatórios
Semanalmente, todos os grupos deverão apresentar o andamento das entregas durante a aula. A não participação do grupo implicará na perda automática de 50% dos pontos da sprint.

Além da apresentação em grupo, é critério obrigatório de avaliação, em todas as apresentações semanais de cada sprint (Lab01S01, Lab01S02 e Lab01S03), que cada integrante apresente, individualmente, a parte do sistema pela qual foi responsável naquela semana, respondendo às perguntas do professor sobre o que foi desenvolvido.

Penalidades aplicadas sobre a nota da sprint, de forma individual para cada integrante, a cada apresentação semanal em que a situação ocorrer:

Não apresentar sua parte individualmente: desconto de 50% da nota da sprint para o integrante.
Não conseguir realizar, durante a apresentação, as alterações solicitadas pelo professor: desconto de 30% da nota da sprint para o integrante.
Cada pergunta do professor que o integrante não conseguir responder: desconto de 10% da nota da sprint para o integrante, por pergunta.
As penalidades acima incidem sobre a nota individual do integrante, são cumulativas dentro da mesma sprint e se acumulam ao longo das semanas em que a sprint estiver em andamento. O desconto total não pode ultrapassar 100% da nota da sprint: quando a soma das penalidades ultrapassar esse valor, a nota individual do integrante naquela sprint é zerada, nunca ficando negativa.

Exemplo: se a nota da rubrica de uma sprint de 4,0 pontos for 3,5 e o integrante não conseguir responder 2 perguntas do professor (2 × 10% = 20% de desconto), sua nota individual na sprint será 3,5 × (1 − 20%) = 2,8. Se, na mesma sprint, o integrante também não apresentar em uma das semanas (mais 50%), o desconto acumulado passa a 70% e a nota individual cai para 3,5 × (1 − 70%) = 1,05.

Nota de Transparência sobre Uso de IA
Em conformidade com a política de uso responsável de Inteligência Artificial da disciplina, informa-se que este documento (enunciado do Laboratório 01) foi produzido com apoio da ferramenta Claude, da empresa Anthropic, utilizada na geração e revisão de texto. O conteúdo foi revisado pelo professor antes da disponibilização aos alunos.