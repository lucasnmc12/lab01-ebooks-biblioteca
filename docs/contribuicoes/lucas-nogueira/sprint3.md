# Contribuições — Lucas Nogueira

## Semana 1 (Sprint 3, Lab01S03)

### Contribuição

Implementei o protótipo funcional inteiro. O Pedro não conseguiu participar desta etapa, então assumi tanto o bloco do acervo, que já era meu desde a Sprint 1, quanto o lado do aluno, que era dele.

O sistema roda de ponta a ponta: login com senha, menu de linha de comando separado por perfil, todas as funcionalidades dos doze casos de uso e persistência em arquivos de texto que sobrevive entre execuções. São vinte e nove classes, distribuídas em três camadas: `modelo` com o domínio e suas regras, `persistencia` com os repositórios de arquivo, e a interface de linha de comando.

Do lado do bibliotecário implementei cadastrar eBook já com a licença de uso (UC07 e UC08), consultar quais alunos estão com um título (UC09), abrir e encerrar o período de acesso (UC10), avaliar a renovação do catálogo (UC11) e remover os títulos não renovados (UC12). Do lado do aluno, adicionar e remover eBooks da estante (UC02 e UC03) e consultar a estante (UC04). O login (UC01) é exigido antes de qualquer uma delas.

Atualizei o diagrama de classes para refletir o que foi de fato implementado e acrescentei a ele as camadas de interface e persistência, que não existiam na Sprint 2.

### Decisões

Coloquei cada regra de negócio na classe que detém o dado, mantendo a linha da Sprint 2. O limite de acessos simultâneos vive na `Licenca`; os limites de 4 obrigatórios e 2 livres vivem na `Estante`; a regra dos 3 alunos vive no `Catalogo`. Nenhuma dessas regras aparece no menu — o menu só chama e mostra o resultado.

Criei a classe `Biblioteca` como camada de aplicação: ela carrega os arquivos, monta o grafo de objetos e grava tudo de volta. Ela conhece todas as partes, o que à primeira vista lembra a God Class que desfiz na Sprint 2, mas a diferença é justamente o que motivou aquele refactor: a `Biblioteca` não decide nada, não tem uma única regra de negócio. Montagem e coordenação são responsabilidades legítimas de uma camada de aplicação; regra de domínio dentro de uma classe que também guarda listas de tudo é que era o problema.

As regras de negócio sinalizam violação lançando `IllegalStateException`, e os menus capturam essa exceção num ponto só, em volta do `switch`. Assim o domínio não precisa saber que existe uma tela, e o usuário recebe uma mensagem em vez de um stack trace.

Senhas nunca são guardadas em texto puro, nem em memória nem no arquivo: o que fica armazenado é o resumo SHA-256. Para um sistema real isso ainda seria insuficiente — o correto seria um algoritmo próprio de senha, com sal e custo configurável, como bcrypt ou Argon2 — mas guardar senha legível num arquivo de texto seria um problema de segurança que eu não queria deixar no trabalho nem como exemplo.

Os acessos em uso de cada licença não são gravados no arquivo. Eles são recalculados ao carregar as estantes, porque cada eBook numa estante corresponde a uma licença ocupada. Gravar esse número seria duplicar uma informação que já está em outro lugar, com o risco de os dois valores discordarem.

A verificação do período de acesso ficou no menu, e não na `Estante`. A estante não tem como saber em que momento do calendário ela está sendo alterada; isso é condição de fluxo da aplicação, não regra da estante. Se o professor preferir ver essa validação dentro do domínio, é passar o período como parâmetro do método de adicionar.

O tipo de leitura não é escolhido pelo aluno. Quando ele adiciona um título, o sistema verifica se alguma disciplina em curso indica aquele eBook: se indica, entra como leitura obrigatória; se não, como leitura livre. O enunciado diz que os obrigatórios são "indicados pela disciplina", então deixar o aluno escolher o próprio tipo abriria brecha para burlar o limite de quatro.

Usei o `id` do eBook como critério de igualdade, com `equals` e `hashCode`. Sem isso, um eBook lido do arquivo seria um objeto diferente do que está na estante, e a consulta do UC09 não encontraria ninguém.

Na primeira execução o sistema cria dados fictícios e mostra as credenciais no terminal. É só para a demonstração não começar com telas vazias; os dados são inventados.

### O que testei

Testei manualmente cada fluxo pelo menu, incluindo os caminhos de erro: senha errada, letra digitada onde se espera número, opção fora do intervalo, tentativa de alterar a estante fora do período de acesso, título com a licença esgotada e estante cheia de leitura livre. Todos devolvem mensagem e mantêm o programa rodando.

Também verifiquei a persistência fechando e reabrindo o programa: os três alunos que adicionaram o mesmo título continuaram lá, a contagem de licenças em uso foi reconstruída corretamente, e a avaliação de renovação marcou para renovar apenas o título com três alunos, removendo os demais do catálogo.

### Pontos em aberto

Continuo sem aplicar o feedback das apresentações das Sprints 1 e 2, que vale 1,0 nesta sprint. Preciso conferir minhas anotações antes da entrega.

A distribuição de funcionalidades ficou desequilibrada, já que fiz também a parte que caberia ao Pedro. Registro isso aqui porque o enunciado cobra, ao final das três sprints, ao menos uma implementação por integrante.

### Nota de transparência sobre uso de IA

Usei o Claude, da Anthropic, como apoio nesta sprint. A ferramenta escreveu a maior parte do código Java a partir das decisões de projeto que discutimos, executou os testes manuais pelo terminal e revisou a redação deste documento. As decisões de onde colocar cada regra, como tratar as exceções, como guardar as senhas e o que persistir foram discutidas comigo e são minhas, e sei explicar cada uma delas.
