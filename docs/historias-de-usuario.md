# Histórias de Usuário

Sistema de Gestão de eBooks da Biblioteca Universitária — Lab01S01.

Cada história corresponde a um caso de uso do [diagrama de casos de uso](diagramas/casos-de-uso.puml). O identificador entre parênteses aponta o caso de uso equivalente.

| Histórias | Casos de uso | Responsável |
| --- | --- | --- |
| HU01 a HU06 | UC01 a UC06 | Pedro Resende |
| HU07 a HU12 | UC07 a UC12 | Lucas Nogueira |

---

## Acesso ao sistema

### HU01, Realizar login (UC01)

_A escrever — Pedro Resende._

## Estante do aluno

### HU02, Adicionar eBook à estante (UC02)

_A escrever — Pedro Resende._

### HU03, Remover eBook da estante (UC03)

_A escrever — Pedro Resende._

### HU04, Consultar estante pessoal (UC04)

_A escrever — Pedro Resende._

### HU05, Verificar disponibilidade de licença (UC05)

_A escrever — Pedro Resende._

### HU06, Notificar estatísticas de uso (UC06)

_A escrever — Pedro Resende._

## Gestão do acervo

### HU07, Cadastrar eBook (UC07)

Como bibliotecário, eu quero cadastrar um eBook no acervo informando título, editora, formato de arquivo e categoria, para que o título fique disponível aos alunos no semestre corrente.

Critérios de aceitação:

- O cadastro só é concluído com título, editora, formato e categoria preenchidos.
- O formato é um dos aceitos pela biblioteca (PDF ou EPUB).
- A categoria é uma das previstas (literatura, técnico ou periódico).
- Ao final do cadastro o sistema exige a definição da licença de uso (HU08).
- Um eBook recém-cadastrado passa a aparecer no catálogo do semestre.

### HU08, Definir licença de uso (UC08)

Como bibliotecário, eu quero definir quantos alunos podem acessar um eBook ao mesmo tempo, para que a biblioteca respeite o contrato de licenciamento do título.

Critérios de aceitação:

- O limite de acessos simultâneos é um número inteiro maior que zero.
- O limite não pode ultrapassar 60 acessos simultâneos.
- Um valor fora dessa faixa é recusado, com mensagem indicando o limite permitido.
- O limite pode ser alterado depois do cadastro e passa a valer para os próximos acessos.

### HU09, Consultar alunos com um eBook (UC09)

Como bibliotecário, eu quero ver quais alunos têm um determinado eBook na estante, para acompanhar o uso do acervo e embasar a decisão de renovação da licença.

Critérios de aceitação:

- A consulta é feita a partir do título do eBook.
- O resultado lista os alunos que estão com aquele eBook na estante no período corrente.
- O total de alunos aparece junto da lista, já que esse número define a renovação (mínimo de 3).
- Um eBook sem nenhum aluno retorna lista vazia, não erro.

### HU10, Manter período de acesso (UC10)

Como bibliotecário, eu quero abrir e encerrar os períodos de acesso do semestre, para que os alunos só alterem a estante dentro da janela prevista.

Critérios de aceitação:

- Cada período tem data de início e data de fim, com o fim posterior ao início.
- Fora de um período aberto, o aluno não consegue adicionar nem remover eBooks da estante.
- Não existem dois períodos de acesso abertos ao mesmo tempo no mesmo semestre.
- O encerramento do período libera a avaliação de renovação do catálogo (HU11).

### HU11, Avaliar renovação do catálogo (UC11)

Como bibliotecário, eu quero verificar, ao final do período de acesso, quantos alunos adicionaram cada eBook, para decidir quais licenças serão renovadas no semestre seguinte.

Critérios de aceitação:

- A avaliação só é executada com o período de acesso encerrado.
- Cada eBook do catálogo é listado com a quantidade de alunos que o adicionaram no período.
- eBooks com 3 ou mais alunos são marcados para renovação.
- eBooks com menos de 3 alunos são marcados para remoção do catálogo (HU12).

### HU12, Remover eBook do catálogo (UC12)

Como bibliotecário, eu quero remover do catálogo os eBooks que não atingiram o mínimo de 3 alunos, para que o semestre seguinte comece apenas com os títulos cuja licença foi renovada.

Critérios de aceitação:

- Só pode ser removido um título já marcado como não renovado na avaliação.
- O eBook removido deixa de aparecer no catálogo do semestre seguinte.
- O registro de uso do título é mantido para consulta posterior.
- A remoção não altera as estantes do período já encerrado.
