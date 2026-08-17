# Contribuições - Pedro Resende

## Contribuição

Fiquei responsável por revisar/fazer a análise final do diagrama de caso de uso gerado pelo Claude e posteriormente analisado pelo outro integrante do grupo. Também escrevi as histórias de usuário HU1, HU2, HU3, e HU4. Por fim, adicionei o arquivo `.gitgnore` no repositório, parte do passo 1 do roteiro que o outro integrante esqueceu de fazer. Utilizei [este template](https://github.com/github/gitignore/blob/main/Java.gitignore) para o arquivo.

## Decisões

Diagrama casos de uso:

1. Retirei a especialização do ator "Usuário" porque é acho redundante, só adiciona elementos a mais no diagrama sem melhorar a legibilidade/qualidade do diagrama.
2. Excluí o ator "Sistema de Estatísticas de Uso" e o caso de uso "Notificar estatísticas de uso", assim como as associações ligadas a eles, porque na aula perguntei ao professor sobre a necessidade de adicionar esse ator secundário no diagrama, e ele falou que não era preciso. Além disso acredito que a forma que essa seção foi modelada não estava adequada.
3. Excluí o caso de uso "Manter período de acesso" assim como as associações ligadas a ele pois na descrição do Product Owner, não é descrito como acontece a abertura e encerramento dos períodos de acesso, muito menos quem faz a abertura e ecerramento dos períodos. Prefiro manter o diagrama refletindo a descrição, sem fazer inferências/suposições que podem estar erradas.
4. Excluí o caso de uso "Verificar disponibilidade de licença" pois ele não representa uma interação com o sistema, mas sim uma regra de negócio, que não deve ser representada em diagramas de caso de uso.
5. Excluí o caso de uso "Definir licença de uso" porque a descrição do Product Owner não cita em nenhum parágrafo que, ao cadastrar um ebook, o bibliotecário também define a licença de uso.
6. Excluí o caso de uso "Avaliar renovação do catálogo" porque a descrição do Product Owner não cita que a renovação do catálogo é feita pela equipe da biblioteca (bibliotecários), podendo ela ser totalmente automatizada, sem envolver interação de um ator externo com o sistema.
7. Excluí o caso de uso "Remover eBook do catálogo" porque a descrição do Product Owner não cita que a remoção de um eBook do catálogo é feita por um ator, e sendo assim não é necessário ter esse caso de uso.
8. Na minha primeira versão do diagrama, eu não havia adicionado o caso de uso "Consultar estante pessoal" (UC04), pois não consegui identificar esse caso de uso na minha análise da descrição do Product Owner, e porque eu tentei criar casos de uso que refletem com muita fidelidade o que é descrito na descrição do PO, sem fazer inferências e suposições. Adicionei (mantive) ele no diagrama do projeto porque esse caso de uso está presente no exemplo de diagrama dado pelo professor no roteiro da sprint, além de que é possível inferir esse caso na leitura da descrição.

Histórias de Usuário:

1. Exclusão das histórias de usuário HU5, HU6, HU8, HU10, HU11, e HU12 referentes aos casos de uso excluídos do diagrama de casos de uso.
2. Preferi fazer as histórias de usuário da forma que está no roteiro da sprint, para seguir melhor o roteiro.
3. A princípio eu iria fazer as histórias de usuário como está no roteiro da sprint, apenas um parágrafo. Porém adicionei os critérios de aceitação para ficar da mesma forma que as HUs do outro integrante do grupo.
