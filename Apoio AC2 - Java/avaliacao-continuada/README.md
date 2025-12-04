# Contexto

Você está dando continuidade à API de avaliação continuada.  
A parte de **Autor** já está pronta e funcional.

Neste exercício, você deverá:

- Modelar o relacionamento entre **Autor** e **Quadrinho** (um autor possui muitos quadrinhos).
- Completar as classes de **DTO**, **Mapper**, **Repository**, **Service** e **Controller** para que **todos** os testes das classes:
    - `QuadrinhoControllerTest`
    - `QuadrinhoValidationTest`

Os pontos a serem implementados estão marcados com `// COMPLEMENTAR` ou `// COMPLETAR` nos arquivos:

- `Quadrinho.java`
- `QuadrinhoRequestDto.java`
- `QuadrinhoResponseDto.java`
- `QuadrinhoMapper.java`
- `QuadrinhoRepository.java`
- `QuadrinhoService.java`
- `QuadrinhoController.java`

---

## Modelo de domínio

### Autor (já pronto)

- A entidade `Autor` já existe e é persistida via `AutorRepository`.
- Para os testes, a criação de autores é feita diretamente no repositório (veja os métodos `criarAutor` nos testes).
- Você **não deve** alterar a lógica de `Autor`.

### Quadrinho (a completar)

A entidade `Quadrinho` representa um quadrinho em HQ/mangá etc.  
Ela possui, no mínimo, os campos:

- `id` (Integer, chave primária gerada)
- `titulo` (String)
- `isbn` (String)
- `nota` (Double)
- `dataLancamento` (LocalDate)

Você deve:

1. **Relacionar Quadrinho com Autor**

    - Cada **Quadrinho** deve estar relacionado a **um único Autor**.
    - Um **Autor** pode ter **vários Quadrinhos**.
    - Use o relacionamento JPA adequado para representar **muitos quadrinhos para um autor**.
    - Esse relacionamento deve permitir que:
        - Na criação de quadrinho, seja possível associá-lo a um autor existente.
        - Nos testes, o `QuadrinhoRepository` consiga salvar e recuperar quadrinhos já vinculados a um autor.

---

## DTOs

### `QuadrinhoRequestDto` (entrada da API)

Usado no corpo das requisições:

- `POST /quadrinhos`
- `PUT /quadrinhos/{id}`

Deve conter:

- `titulo` (String)
- `isbn` (String)
- `nota` (Double)
- `dataLancamento` (LocalDate)
- `autorId` (Integer) – id do autor dono do quadrinho

#### Regras de validação (testadas em `QuadrinhoValidationTest`)

Para **POST /quadrinhos**:

- `titulo`
    - Não pode ser nulo.
    - Se for nulo → **HTTP 400 (Bad Request)**.

- `isbn`
    - Não pode ser nulo.
    - Se for nulo → **HTTP 400**.

- `nota`
    - Não pode ser nula.
    - Não pode ser menor que **0**.
    - Não pode ser maior que **10**.
    - Se for nula, `< 0` ou `> 10` → **HTTP 400**.
    - Os testes garantem que:
        - `nota = 0.0` é aceita (**201 Created**).
        - `nota = 10.0` é aceita (**201 Created**).

- `dataLancamento`
    - Não pode ser nula.
    - Se for nula → **HTTP 400**.

- `autorId`
    - Não pode ser nulo.
    - Se for nulo → **HTTP 400**.

Para **PUT /quadrinhos/{id}**:

- Os testes de validação cobrem explicitamente:
    - `titulo` nulo → **HTTP 400**.
    - `nota < 0` → **HTTP 400**.
    - `nota > 10` → **HTTP 400**.

Você pode manter a mesma regra de validação do POST também para o PUT (tornar todos os campos obrigatórios no DTO), desde que os cenários acima, no mínimo, estejam atendidos.

> **Dica:** Use Bean Validation (ex: `@NotNull`, `@DecimalMin`, `@DecimalMax`) no `QuadrinhoRequestDto` para que o Spring retorne 400 automaticamente quando a validação falhar.

---

### `QuadrinhoResponseDto` (saída da API)

Usado nas respostas de:

- `GET /quadrinhos`
- `GET /quadrinhos/{id}`
- `GET /quadrinhos/top3`
- `GET /quadrinhos/por-periodo`
- `GET /quadrinhos/autor`
- `POST /quadrinhos`
- `PUT /quadrinhos/{id}`

Deve conter:

- `id` (Integer)
- `titulo` (String)
- `isbn` (String)
- `nota` (Double)
- `dataLancamento` (LocalDate)
- `autor` (objeto interno com informações do autor)

O objeto interno (por exemplo, `AutorInfoDto` dentro de `QuadrinhoResponseDto`) deve expor pelo menos:

- `id` do autor
- `nome` do autor

Os testes verificam coisas como:

- `$.autor.id`
- `$.autor.nome`
- `$.titulo`
- `$.isbn`
- `$.nota`

---

## Mapper

Classe: `QuadrinhoMapper`

Você deve implementar, no mínimo, os métodos que convertem:

- De `QuadrinhoRequestDto` → `Quadrinho` (entidade)
- De `Quadrinho` → `QuadrinhoResponseDto`

Requisitos gerais:

- O mapeamento **de entidade para resposta** deve preencher:
    - Campos básicos (`id`, `titulo`, `isbn`, `nota`, `dataLancamento`)
    - Objeto `autor` interno no DTO com `id` e `nome` do autor.

- O mapeamento **de DTO para entidade** deve:
    - Preencher `titulo`, `isbn`, `nota`, `dataLancamento`.
    - Se for um cadastro, permitir informar o `autor` posteriormente (normalmente resolvido no service a partir de `autorId`).
    - Se for uma atualização, considerar o `id` do quadrinho que está sendo atualizado.

> **Observação:** A resolução de `autorId` (buscar o autor no banco) normalmente deve ser feita na **service**, e não dentro do mapper.

---

## Repository

Classe: `QuadrinhoRepository`

- Deve estender a interface correta do Spring Data JPA para trabalhar com:
    - Entidade `Quadrinho`
    - Tipo de id `Integer`

Além disso, para suportar os endpoints exigidos pelos testes, você precisará de **métodos de consulta** que permitam:

1. Buscar quadrinhos **ordenados pela nota de forma decrescente** (para o endpoint `/quadrinhos/top3`).
2. Buscar quadrinhos por **intervalo de datas** (`dataLancamento` entre `inicio` e `fim`) para `/quadrinhos/por-periodo`.
3. Buscar quadrinhos pelo **nome do autor** (case insensitive) para `/quadrinhos/autor`.

> Você pode usar *query methods* do Spring Data JPA, por exemplo usando `ContainingIgnoreCase`, `Between` e `OrderBy`.

---

## Service

Classe: `QuadrinhoService`

Responsável pela **regra de negócio**. Alguns métodos já devem existir, outros você irá completar.

### Regras gerais de negócio

- **ISBN único**
    - Não deve existir mais de um quadrinho com o mesmo `isbn`.
    - Se, ao **cadastrar**, o `isbn` já existir em outro quadrinho → **HTTP 409 (Conflict)**.
    - Se, ao **atualizar**, o `isbn` informado já estiver em outro quadrinho diferente do que está sendo alterado → **HTTP 409 (Conflict)**.

- **Entidades não encontradas**
    - Se um quadrinho não for encontrado pelo `id` (GET/PUT/DELETE), deve resultar em **HTTP 404 (Not Found)**.
    - Se um autor não for encontrado pelo `autorId` durante a atualização (PUT) → **HTTP 404 (Not Found)**.

- **Associação com Autor**
    - No cadastro e na atualização, a service deve usar o `autorId` do DTO para buscar o autor no `AutorRepository`.
    - Se o autor existir, deve associá-lo ao quadrinho.
    - Os testes validam que, após o PUT, o autor do quadrinho foi alterado corretamente (id e nome).

### Comportamentos esperados (conectados aos testes)

A service deve fornecer métodos (ou lógica equivalente) para:

1. **Listar todos os quadrinhos**
    - Usado por `GET /quadrinhos`.
    - Quando a lista vier vazia → controller devolve **204 No Content**.
    - Quando houver quadrinhos → controller devolve **200 OK** e a lista de `QuadrinhoResponseDto`.

2. **Buscar quadrinho por id**
    - Usado por `GET /quadrinhos/{id}`.
    - Se existir → devolve o quadrinho.
    - Se não existir → gera **404 Not Found**.

3. **Cadastrar quadrinho (POST)**
    - Verifica validação (Bean Validation).
    - Verifica se já existe `isbn` igual:
        - Se **não existir** → salva e retorna o quadrinho criado (status **201 Created**).
        - Se **já existir** → status **409 Conflict**.

4. **Atualizar quadrinho (PUT)**
    - Se o quadrinho com aquele `id` **não existir** → **404 Not Found**.
    - Se o autor indicado por `autorId` **não existir** → **404 Not Found**.
    - Se o novo `isbn` já existir em **outro** quadrinho → **409 Conflict**.
    - Caso contrário:
        - Atualiza `titulo`, `isbn`, `nota`, `dataLancamento` e **autor**.
        - Retorna o quadrinho atualizado com **200 OK**.

5. **Remover quadrinho por id**
    - Usado por `DELETE /quadrinhos/{id}`.
    - Se existir → remove e a controller responde **204 No Content**.
    - Se não existir → **404 Not Found**.

6. **Top 3 melhores quadrinhos**
    - Usado por `GET /quadrinhos/top3`.
    - Deve retornar **até 3** quadrinhos com **maiores notas**, ordenados do maior para o menor.
    - Se não houver nenhum quadrinho:
        - Controller responde **204 No Content**.
    - Se houver:
        - Controller responde **200 OK** com lista de 1 a 3 `QuadrinhoResponseDto`.
    - Os testes verificam especificamente um cenário com 6 quadrinhos e esperam:
        - `nota` na ordem `[9.8, 9.5, 9.0]`.

7. **Buscar por período**
    - Usado por `GET /quadrinhos/por-periodo?inicio=YYYY-MM-DD&fim=YYYY-MM-DD`.
    - Deve retornar quadrinhos com `dataLancamento` **entre** `inicio` e `fim` (inclusive).
    - Se não houver quadrinhos no intervalo → **204 No Content**.
    - Se houver → **200 OK** com a lista:
        - A ordem esperada nos testes é a ordem natural de inserção dos exemplos criados (veja `QuadrinhoControllerTest`).

8. **Buscar por autor (case insensitive)**
    - Usado por `GET /quadrinhos/autor?nome=...`.
    - Deve buscar quadrinhos cujos autores tenham nome compatível com o parâmetro, **ignorando maiúsculas/minúsculas**.
    - Se não encontrar nenhum quadrinho para o autor informado:
        - **204 No Content**.
    - Se encontrar:
        - **200 OK** com lista de quadrinhos.
        - Nos testes, quando `nome=moore`, todos os itens retornados devem ter `autor.nome = "Alan Moore"`.

---

## Controller

Classe: `QuadrinhoController`  
Caminho base: `/quadrinhos`

Você deve expor os endpoints e status HTTP exatamente como os testes esperam.

### 1. `GET /quadrinhos`

- Sem parâmetros.
- Se não houver quadrinhos:
    - **204 No Content**.
- Se houver:
    - **200 OK**.
    - Corpo: lista de `QuadrinhoResponseDto`.
    - Exemplo checado nos testes:
        - Tamanho da lista (`$.length()`).
        - `titulo` do primeiro e segundo quadrinho.
        - `autor.nome` do primeiro.

### 2. `GET /quadrinhos/{id}`

- Se o quadrinho existir:
    - **200 OK**.
    - Corpo: `QuadrinhoResponseDto` com `id`, `titulo`, `autor.id`, `autor.nome`.
- Se não existir:
    - **404 Not Found**.

### 3. `POST /quadrinhos`

- Corpo: `QuadrinhoRequestDto` válido.
- Se passar na validação e `isbn` ainda **não existir**:
    - **201 Created**.
    - Corpo: `QuadrinhoResponseDto` com:
        - `id` preenchido.
        - `titulo`, `isbn`, `autor.id`, `autor.nome`.
- Se já existir quadrinho com o mesmo `isbn`:
    - **409 Conflict**.
- Se algum campo obrigatório violar as regras de validação (ex: `titulo` nulo, `nota < 0`, `nota > 10`, etc.):
    - **400 Bad Request**.

### 4. `GET /quadrinhos/top3`

- Sem parâmetros.
- Se não houver quadrinhos:
    - **204 No Content**.
- Se houver:
    - **200 OK**.
    - Corpo: lista com **exatamente 3** quadrinhos (quando houver 3 ou mais no banco) com as **maiores notas** em ordem decrescente.
    - Os testes verificam:
        - `$.length() == 3`
        - As notas dos itens nas posições 0, 1 e 2.

### 5. `GET /quadrinhos/por-periodo`

- Parâmetros obrigatórios:
    - `inicio` (String – data em `YYYY-MM-DD`)
    - `fim` (String – data em `YYYY-MM-DD`)
- Se não houver quadrinhos no intervalo:
    - **204 No Content**.
- Se houver:
    - **200 OK**.
    - Corpo: lista com apenas os quadrinhos dentro do intervalo.
    - Os testes comparam:
        - Tamanho da lista (2).
        - `id` dos retornados em ordem.

### 6. `GET /quadrinhos/autor`

- Parâmetro obrigatório:
    - `nome` (String) – parte do nome do autor (case insensitive).
- Se não existir quadrinho para esse autor:
    - **204 No Content**.
- Se existir:
    - **200 OK**.
    - Corpo: lista de quadrinhos cujo `autor.nome` bate com o filtro (ignorando maiúsculas/minúsculas).
    - Os testes verificam que, para `nome=moore`, são retornados 2 quadrinhos, ambos com `autor.nome = "Alan Moore"`.

### 7. `DELETE /quadrinhos/{id}`

- Se o quadrinho existir:
    - Remove o registro.
    - **204 No Content**.
- Se não existir:
    - **404 Not Found**.

### 8. `PUT /quadrinhos/{id}`

- Corpo: `QuadrinhoRequestDto`.
- Se o quadrinho não existir:
    - **404 Not Found**.
- Se o `autorId` informado não existir:
    - **404 Not Found**.
- Se o `isbn` informado já existir em outro quadrinho (diferente do que está sendo atualizado):
    - **409 Conflict**.
- Se alguma validação falhar (por exemplo, `titulo` nulo, `nota < 0`, `nota > 10`):
    - **400 Bad Request**.
- Caso tudo esteja correto:
    - Atualiza quadrinho e autor.
    - **200 OK**.
    - Corpo: `QuadrinhoResponseDto` com os novos dados:
        - `titulo`, `isbn`, `nota`, `dataLancamento`, `autor.id`, `autor.nome`.

Os testes verificam, após o PUT:

- Os campos no JSON de resposta.
- E também, diretamente no banco, se:
    - `titulo`, `isbn`, `nota` foram realmente alterados.
    - O autor associado ao quadrinho foi trocado corretamente.

---

## Dicas para passar nos testes

- Leia atentamente os nomes (`@DisplayName`) e o código dos testes:
    - `QuadrinhoControllerTest`
    - `QuadrinhoValidationTest`
- Use o comportamento esperado nos testes como “documentação viva” do que a API deve fazer.
- Garanta:
    - **Status HTTP corretos** em cada cenário (200, 201, 204, 400, 404, 409).
    - **Relacionamento correto** entre `Quadrinho` e `Autor`.
    - **Validação** de campos do `QuadrinhoRequestDto`.
    - **Regra de negócio de ISBN único** em POST e PUT.
    - **Filtros** por período, por autor e pela melhor nota (top3).