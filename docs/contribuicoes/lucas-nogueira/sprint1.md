# Contribuições — Lucas Nogueira

## Semana 1 (Sprint 1, Lab01S01)

### Contribuição

Fiquei com o lado do bibliotecário no diagrama de casos de uso. Modelei UC07 (Cadastrar eBook), UC08 (Definir licença de uso), UC09 (Consultar alunos com um eBook), UC10 (Manter período de acesso), UC11 (Avaliar renovação do catálogo) e UC12 (Remover eBook do catálogo), e escrevi as histórias HU07 a HU12 correspondentes, cada uma com critérios de aceitação.

Também montei o esqueleto do `casos-de-uso.puml` (atores, fronteira do sistema e os agrupamentos internos) e a estrutura de pastas de `docs/`, para o Pedro preencher a parte do aluno em cima da mesma base.

### Decisões

Criei o ator `Usuário` como generalização de `Aluno` e `Bibliotecário`. A descrição diz que todos os usuários do sistema têm senha, então ligar o login aos dois atores separadamente repetiria a mesma informação. Com a generalização, a associação com "Realizar login" aparece uma vez só e continua valendo para os dois perfis.

Separei "Definir licença de uso" de "Cadastrar eBook" com `<<include>>`, em vez de tratar o limite de acessos como mais um campo do formulário de cadastro. O teto de 60 acessos simultâneos é uma regra de licenciamento que muda por conta própria, independente dos dados bibliográficos do título. Deixar isso visível no diagrama deve facilitar a modelagem da classe de licença na Sprint 2.

Entre "Remover eBook do catálogo" e "Avaliar renovação do catálogo" usei `<<extend>>`, não `<<include>>`. A remoção é condicional: só ocorre quando o título fica abaixo de 3 alunos ao final do período. Um `<<include>>` afirmaria que toda avaliação remove algum eBook, o que não é o caso.

Modelei "Manter período de acesso" como caso de uso do bibliotecário. A descrição fala em "períodos de acesso ao longo do semestre" sem dizer quem os define, mas como é a equipe da biblioteca que administra o catálogo, atribuí a responsabilidade a ela. Pretendo confirmar essa interpretação com o professor na apresentação.

Não modelei "validar senha" nem "gravar no banco" como casos de uso. São passos de implementação de "Realizar login" e não descrevem a intenção de nenhum ator.

Detalhe do arquivo `.puml`: as dependências de include para o login estão escritas na forma `UC01 <.. UCxx`. É só uma questão de layout, nessa ordem o PlantUML posiciona o login perto dos atores em vez de empurrá-lo para a extremidade oposta do diagrama. O sentido da dependência continua sendo de `UCxx` para `UC01`.

### Nota de transparência sobre uso de IA

Usei o Claude, da Anthropic, como apoio nesta sprint: para entender melhor cada etapa do roteiro e para revisar a formatação e a redação dos textos deste repositório. A análise da descrição do sistema, a modelagem dos casos de uso e as decisões registradas acima foram feitas por mim, e sei explicar cada uma delas.
