Sprint I
Vencimento Segunda-feira por 10:40 Pontos 4 Disponível até 17 Ago em 10:40
Roteiro Hands-on, Sprint 1 (Lab01S01)
Sistema de Gestão de eBooks da Biblioteca Universitária

Informação	Valor
Sprint	Lab01S01
Valor	4,0 pontos
Entregáveis	Diagrama de Casos de Uso (PlantUML) + Histórias de Usuário
Mínimo por integrante	1 caso de uso e 1 história de usuário
Este roteiro complementa o enunciado do Laboratório 01 (Sistema de Gestão de eBooks da Biblioteca Universitária) e a Rubrica Individual Lab01S01. Consulte esses dois documentos para os critérios completos de avaliação, penalidades e prazos.

Nota de transparência sobre uso de IA: em conformidade com a política de uso responsável de Inteligência Artificial da disciplina, informa-se que este roteiro foi produzido com apoio da ferramenta Claude, da empresa Anthropic, utilizada na geração e revisão de texto, dos diagramas em PlantUML e dos exemplos de código. O conteúdo foi revisado pelo professor antes da disponibilização aos alunos.

1. Objetivo da Sprint
Nesta sprint, o grupo deve analisar a descrição do sistema, identificar os atores e os casos de uso, modelar o Diagrama de Casos de Uso obrigatoriamente em PlantUML e escrever as Histórias de Usuário correspondentes. Cada integrante deve ficar responsável por, no mínimo, 1 caso de uso e 1 história de usuário, mantendo o board do GitHub Projects e o documento de contribuição semanal atualizados.

A rubrica individual desta sprint distribui os 4,0 conforme apresentado no final desta tarefa.

2. Pré-requisitos
Grupo formado e cadastrado no Canvas.
Conta no GitHub de cada integrante, com Git instalado e configurado na máquina.
Acesso a uma ferramenta de PlantUML (não é necessário instalar nada, basta um navegador ou um plugin do VSCode).
Leitura atenta da descrição do sistema no enunciado do Laboratório 01.
Ferramentas de PlantUML sugeridas
PlantUML Online: https://plantuml.online/Links to an external site.
PlantUML, site oficial: https://plantuml.com/Links to an external site.
PlantText: https://www.planttext.com/Links to an external site.
Documentação oficial: https://plantuml.com/guideLinks to an external site.
3. Conceitos-chave
3.1 Diagrama de Casos de Uso
Um Diagrama de Casos de Uso descreve, de forma visual, quem interage com o sistema (os atores) e o que cada ator consegue fazer (os casos de uso), sem entrar em detalhes de implementação.

Elemento	Representação	O que significa
Ator	Boneco (stick figure)	Usuário ou sistema externo que interage com o sistema
Caso de uso	Oval	Uma funcionalidade oferecida ao ator
Fronteira do sistema	Retângulo	Delimita o que está dentro do sistema modelado
Associação	Linha simples	Liga um ator a um caso de uso que ele executa
Inclusão (<<include>>)	Linha tracejada com seta	Um caso de uso sempre aciona outro (ex.: todos os casos de uso exigem login)
Extensão (<<extend>>)	Linha tracejada com seta	Um caso de uso pode, opcionalmente, estender outro em certas condições
Generalização	Seta triangular vazada	Um ator ou caso de uso é uma especialização de outro
Erro comum: confundir caso de uso com detalhe técnico. "Validar senha no banco de dados" não é um caso de uso, é um detalhe de implementação de "Realizar login". Um caso de uso descreve a intenção do ator, não a técnica usada para realizá-la.

3.2 História de Usuário
Uma história de usuário descreve uma necessidade sob o ponto de vista de quem usa o sistema, no formato:

Como <ator>, eu quero <ação>, para que <benefício>.
Uma boa história de usuário costuma seguir os critérios INVEST:

Independente: não depende de outra história para ser entendida.
Negociável: é um convite à conversa, não uma especificação fechada.
Valiosa: entrega valor perceptível para o ator.
Estimável: dá para estimar o esforço de implementação.
Small (pequena): cabe em uma sprint.
Testável: dá para verificar se foi atendida.
4. Passo a Passo
Passo 1. Criar o repositório no GitHub
Crie um repositório com um nome claro, por exemplo lab01-ebooks-biblioteca. Ao criar, marque a opção de gerar um README.md inicial e adicione um .gitignore para Java (o próprio GitHub oferece um modelo pronto ao criar o repositório).

Passo 2. Adicionar os colaboradores
Se o repositório for privado, em Settings > Collaborators, adicione todos os integrantes do grupo e o professor. Sem esse passo, o professor não consegue acessar o repositório mesmo com a URL enviada no Canvas. Se o repositório for público, esse passo é apenas recomendado, não obrigatório.

Passo 3. Clonar o repositório localmente
Cada integrante deve clonar o repositório na própria máquina.

git clone https://github.com/<usuario-ou-organizacao>/lab01-ebooks-biblioteca.git
cd lab01-ebooks-biblioteca
git config user.name "Seu Nome"
git config user.email "seu-email@exemplo.com"
Passo 4. Criar a estrutura de pastas
mkdir -p docs/diagramas
mkdir -p docs/contribuicoes/nome-do-integrante-1
mkdir -p docs/contribuicoes/nome-do-integrante-2
Estrutura esperada ao final desta sprint:

lab01-ebooks-biblioteca/
|-- README.md
|-- docs/
|   |-- diagramas/
|   |   |-- casos-de-uso.puml
|   |   |-- casos-de-uso.png
|   |-- historias-de-usuario.md
|   |-- contribuicoes/
|       |-- nome-do-integrante-1/
|       |   |-- sprint1.md
|       |-- nome-do-integrante-2/
|           |-- sprint1.md
Passo 5. Primeiro commit, estrutura inicial
git add .
git commit -m "chore: cria estrutura inicial de pastas do projeto"
git push origin main
Passo 6. Criar o board no GitHub Projects
Na aba Projects do repositório, crie um novo projeto usando o modelo Board. Sugestão de colunas: A Fazer, Em Andamento, Em Revisão, Concluído.

Cadastre uma tarefa (card) para cada item da distribuição de trabalho, por exemplo:

"Modelar caso de uso: Adicionar eBook a estante" (atribuído a um integrante)
"Escrever história de usuário: Consultar alunos com um eBook" (atribuído a outro integrante)
Atribua cada card ao integrante responsável, usando o campo Assignees.

Observação: o board deve ser mantido atualizado durante toda a sprint. Ele é um critério avaliado individualmente.

Passo 7. Analisar a descrição do sistema e identificar os atores
Releia a descrição do sistema no enunciado. Uma técnica simples é sublinhar todos os substantivos que representam pessoas ou sistemas externos que interagem com o sistema. Para o Sistema de Gestão de eBooks, os atores principais são:

Aluno: adiciona e remove eBooks da própria estante, consulta sua estante.
Bibliotecário: cadastra eBooks, consulta quais alunos têm um determinado eBook.
Passo 8. Brainstorm dos casos de uso em grupo
Em grupo, listem todas as ações que cada ator realiza no sistema, a partir da descrição. Não se preocupem ainda com o diagrama, apenas listem. Exemplos para este sistema:

Realizar login
Adicionar eBook à estante
Remover eBook da estante
Consultar estante pessoal
Consultar alunos com um eBook
Cadastrar eBook
Passo 9. Distribuir os casos de uso entre os integrantes
Cada integrante deve ficar responsável por, no mínimo, 1 caso de uso. Registrem essa distribuição no board (passo 6) e tomem nota, pois ela também será cobrada na Sprint 3, quando o mínimo de contribuições de todo o projeto for conferido.

Passo 10. Modelar o Diagrama de Casos de Uso em PlantUML
Abra uma das ferramentas de PlantUML sugeridas e crie o arquivo casos-de-uso.puml com o conteúdo abaixo, ajustando conforme a distribuição de tarefas do grupo.

@startuml casos_de_uso_ebooks
left to right direction
skinparam packageStyle rectangle

actor Aluno
actor Bibliotecario as "Bibliotecário"

rectangle "Sistema de Gestão de eBooks" {
  usecase "Realizar login" as UC01
  usecase "Adicionar eBook a estante" as UC02
  usecase "Remover eBook da estante" as UC03
  usecase "Consultar estante pessoal" as UC04
  usecase "Consultar alunos com um eBook" as UC05
  usecase "Cadastrar eBook" as UC06
}

Aluno --> UC01
Aluno --> UC02
Aluno --> UC03
Aluno --> UC04
Bibliotecario --> UC01
Bibliotecario --> UC05
Bibliotecario --> UC06

UC02 ..> UC01 : <<include>>
UC03 ..> UC01 : <<include>>
UC04 ..> UC01 : <<include>>
@enduml
Explicando o código linha a linha:

left to right direction: organiza o diagrama na horizontal, mais legível quando há muitos casos de uso.
actor Aluno e actor Bibliotecario as "Bibliotecário": declara os atores. O as permite usar um identificador sem acento (Bibliotecario) e exibir o rótulo acentuado ("Bibliotecário").
rectangle "Sistema de Gestão de eBooks" { ... }: define a fronteira do sistema, dentro da qual ficam os casos de uso.
usecase "..." as UC01: declara um caso de uso e cria um apelido curto (UC01) para referenciá-lo nas linhas seguintes.
Aluno --> UC01: associação entre o ator e o caso de uso.
UC02 ..> UC01 : <<include>>: indica que o caso de uso "Adicionar eBook à estante" sempre inclui o caso de uso "Realizar login".
Passo 11. Renderizar e exportar a imagem
Na ferramenta escolhida, gere a imagem do diagrama (PNG ou SVG) e salve como docs/diagramas/casos-de-uso.png, junto ao arquivo-fonte .puml.

Passo 12. Escrever as Histórias de Usuário
Para cada caso de uso, escreva uma história de usuário no arquivo docs/historias-de-usuario-identificador.md.

## Histórias de Usuário

### HU01, Realizar login
Como usuário do sistema, eu quero informar minha senha para acessar o
sistema, para que apenas pessoas autorizadas usem minha conta.

### HU02, Adicionar eBook a estante
Como aluno, eu quero adicionar um eBook a minha estante pessoal,
para que eu possa acessa-lo durante o semestre.

### HU03, Remover eBook da estante
Como aluno, eu quero remover um eBook da minha estante,
para liberar espaço para outro eBook de leitura obrigatoria ou livre.

### HU04, Consultar estante pessoal
Como aluno, eu quero consultar os eBooks da minha estante,
para saber quais titulos ja adicionei neste semestre.

### HU05, Consultar alunos com um eBook
Como bibliotecario, eu quero consultar quais alunos estao com
um eBook em sua estante, para acompanhar o uso do acervo.

### HU06, Cadastrar eBook
Como bibliotecario, eu quero cadastrar um novo eBook no sistema,
para disponibiliza-lo aos alunos.
Passo 13. Atualizar o README
No README.md, inclua: o nome do sistema, um resumo da descrição, o link para docs/diagramas/casos-de-uso.puml, o link para docs/historias-de-usuario.md e a URL do repositório (a mesma que deve ser enviada no Canvas).

Passo 14. Atualizar o board e o documento de contribuição da semana
Mova, no board, os cards concluídos para a coluna correspondente. Em seguida, cada integrante atualiza seu arquivo de contribuição semanal.

docs/contribuicoes/nome-do-integrante-1/sprint1.md

## Semana 1
Contribuicao: modelei o caso de uso "Adicionar eBook a estante" em
PlantUML e escrevi a historia de usuario correspondente (HU02).
Decisoes: optei por incluir o login como caso de uso incluido
(<<include>>) em vez de repetir a validacao em cada caso de uso,
para evitar duplicidade no diagrama.

## Semana 2
Contribuicao: revisei o caso de uso apos conversa com o grupo e
ajustei a historia de usuario HU02 para deixar o beneficio mais
claro.
Decisoes: mantive o caso de uso separado de "Remover eBook da
estante", em vez de unificar os dois, para facilitar a distribuicao
individual de tarefas entre os integrantes.
Observação: se a sprint durar mais de uma semana, acrescente um novo bloco ## Semana N ao mesmo arquivo a cada atualização, sem apagar os blocos anteriores.

Passo 15. Commitar e enviar as alterações
Prefira commits pequenos e frequentes, um para cada entrega concluída. Como vários integrantes modelam casos de uso e histórias em paralelo, use uma branch por integrante para evitar que um trabalho sobrescreva o outro.

git checkout main
git pull origin main
git checkout -b feature/caso-de-uso-adicionar-ebook

git add docs/diagramas/casos-de-uso.puml docs/diagramas/casos-de-uso.png
git commit -m "docs: adiciona diagrama de casos de uso em PlantUML"

git add docs/historias-de-usuario.md
git commit -m "docs: adiciona historias de usuario da Sprint 1"

git add docs/contribuicoes/nome-do-integrante-1/sprint1.md
git commit -m "docs: registra contribuicao semanal (sprint1)"

git add README.md
git commit -m "docs: atualiza README com descricao do sistema"

git push origin feature/caso-de-uso-adicionar-ebook
Depois de enviar a branch, abra um Pull Request no GitHub para revisar e mesclar as alterações na main. Como o diagrama de casos de uso e o arquivo de histórias são compartilhados por todo o grupo, combinem previamente quem mescla cada Pull Request, para não sobrescrever a contribuição de outro integrante.

Passo 16. Revisão final e preparação da apresentação
Antes da aula, confira o checklist da seção 6 e garanta que você consegue explicar, sozinho, o caso de uso e a história de usuário sob sua responsabilidade, incluindo o porquê das decisões de modelagem tomadas. O professor pode fazer perguntas sobre sua parte, e cada pergunta não respondida gera desconto individual na nota da sprint.

5. Erros Comuns e Dicas
Confundir caso de uso com passo técnico. "Consultar banco de dados" não é caso de uso; "Consultar estante pessoal" é.
Esquecer de versionar a imagem exportada. O .puml sozinho não é visualizável no GitHub sem uma extensão; sempre inclua também o .png.
Histórias de usuário vagas. Evite histórias sem benefício claro, como "Como aluno, eu quero usar o sistema". Prefira sempre explicitar o motivo.
Deixar o board desatualizado até a véspera da apresentação. Atualize o board conforme o trabalho avança, não tudo de uma vez.
Diagrama sem fronteira do sistema. Sem o retângulo delimitando o sistema, fica difícil visualizar o que está dentro e fora do escopo modelado.
Trabalhar direto na main em grupo. Como o diagrama e as histórias são arquivos compartilhados, editar todos direto na main gera sobrescrita de trabalho. Prefira uma branch por integrante, com Pull Request para juntar as contribuições.
Rubrica
Projeto1_Sprint1
Projeto1_Sprint1
Critérios	Avaliações	Pts
Este critério está vinculado ao objetivo de aprendizagemCaso de uso
Caso de uso sob responsabilidade do integrante (PlantUML)
1,5 pts
Completo
Caso de uso modelado corretamente em PlantUML, coerente com a descrição do sistema e com os demais casos de uso do grupo.
0,75 pts
Parcial
Caso de uso modelado em PlantUML, porém incompleto ou com inconsistências em relação à descrição do sistema.
0 pts
Insuficiente
Caso de uso ausente, incorreto ou não modelado em PlantUML.
1,5 pts
Este critério está vinculado ao objetivo de aprendizagemHistória de Usuário
História de usuário sob responsabilidade do integrante
1,5 pts
Completo
História completa, clara e alinhada ao caso de uso correspondente.
0,75 pts
Parcial
História presente, porém incompleta ou pouco clara.
0 pts
Insuficiente
História ausente ou sem um formato reconhecível.
1,5 pts
Este critério está vinculado ao objetivo de aprendizagemContribuição
Atualização semanal do documento de contribuição (docs/contribuicoes/)
1 pts
Completo
Arquivo atualizado em todas as semanas da sprint, com contribuição e decisões claramente descritas.
0,5 pts
Parcial
Arquivo atualizado em parte das semanas da sprint, ou com registros incompletos.
0 pts
Insuficiente
Arquivo de contribuição ausente ou não atualizado em nenhuma semana da sprint.
1 pts
Total de pontos: 4