[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/jnIUu3Tn)
<h1 style="text-align:center;">Rubber Duck Debugging</h1>

<h3 style="text-align:center; font-style:italic;">
Capítulo 1: em caso de dúvida, pergunte ao pato. Ele sabe. 🦆
</h3>

<div style="text-align:center;">
  <a href="https://rubberduckdebugging.com/" target="_blank">
    <img src="rubber.png" alt="Patinho de borracha" width="200"/>
  </a>
</div>

---

# 📚 O que é um `ArrayList` em Java?

Um `ArrayList` é uma lista baseada em **array redimensionável**. Ele mantém a **ordem de inserção**, permite **elementos duplicados** e também valores **`null`**.  
Principais características:

* **Acesso por índice** é **O(1)** (tempo constante).
* **Inserção no fim** é **O(1) amortizado** (porque, quando enche, o array interno é realocado para uma capacidade maior).
* **Inserções/remoções no meio** exigem **deslocamentos (shifts)** e, portanto, custam **O(n)**.
* Existe diferença entre **capacidade** (tamanho do array interno) e **tamanho lógico** (quantidade de elementos armazenados).
* Quando o array interno está cheio, o `ArrayList` cresce multiplicando a capacidade (neste exercício, usaremos **dobrar** a capacidade), copiando os elementos para o novo array e preservando a ordem.

---

# Enunciado

# Visão geral da estrutura

* A lista armazena elementos em um **array interno** de `String` chamado `elements`.
* O número de posições ocupadas é controlado por **`numElements`**.
* Capacidade inicial: **4**.
* Quando precisar inserir e não houver espaço, **a capacidade deve dobrar** até comportar a nova quantidade.

---

## Inserção

### `boolean add(String element)`

**O que fazer:**

1. Garantir capacidade para `numElements + 1` (se não couber, **dobrar** até caber).
2. Inserir `element` na **última posição livre** (`elements[numElements]`).
3. Incrementar `numElements`.
4. Retornar `true`.

**Regras dos testes:**

* Verificam **crescimento de capacidade** (4 → 8 → 16...) quando necessário.
* Conferem **preservação da ordem** após *resize*.
* **`null` é válido** e deve ser armazenado.
* Não depende de `size()`/`get()` para validar.

---

### `boolean add(int index, String element)`

**O que fazer:**

1. Validar índice de **inserção**: `0 <= index <= numElements`.

    * `index == numElements` significa **inserir no fim**.
    * Fora disso, **lançar `IndexOutOfBoundsException`**.
2. Garantir capacidade para `numElements + 1`.
3. **Deslocar à direita** os elementos em `[index, numElements-1]` para `[index+1, numElements]`.
4. Gravar `element` em `elements[index]`.
5. Incrementar `numElements`.
6. Retornar `true`.

**Regras dos testes:**

* Cobram **deslocamento correto** (sem sobrescrever valores).
* Aceitam **inserção no fim** via `index == numElements`.
* Índices inválidos devem gerar **exceção**.
* Não presume implementação de `add(String)`.

---

### `boolean addAll(String[] elements)`

**O que fazer:**

1. Se `elements` for `null` **ou** `elements.length == 0`, **retornar `false`**.
2. Garantir capacidade para `numElements + elements.length` (pode crescer **de uma vez**).
3. Copiar na sequência os itens recebidos para o array interno.
4. Atualizar `numElements` somando `elements.length`.
5. Retornar `true`.

**Regras dos testes:**

* **`false`** quando o array recebido for vazio.
* **Ordem preservada** ao inserir muitos itens (podem ocorrer múltiplos *resizes*).
* Não depende de `add(String)` para validar.

---

### `String set(int index, String element)`

**O que fazer:**

1. Validar índice de **acesso**: `0 <= index < numElements`; senão, **exceção**.
2. Guardar o **valor antigo** de `elements[index]`.
3. Substituir por `element` (aceita `null`).
4. **Não** alterar `numElements`.
5. Retornar o **valor antigo**.

**Regras dos testes:**

* Exigem retorno do **valor anterior**.
* Aceitam **`null`** como novo valor.
* Índices inválidos → **exceção**.

---

## Acesso

### `String get(int index)`

**O que fazer:**

1. Validar índice de **acesso**: `0 <= index < numElements`; senão, **exceção**.
2. Retornar `elements[index]` (pode ser `null`).

**Regras dos testes:**

* Exceção para `index` fora do intervalo.
* Deve **retornar `null`** quando a posição armazenada é `null`.

---

### `int size()`

**O que fazer:**

* Retornar **exatamente** `numElements`.

**Regras dos testes:**

* **Não** retornar a capacidade do array interno.
* Refletir o valor manipulado nos cenários de teste por *reflection*.

---

### `boolean isEmpty()`

**O que fazer:**

* Retornar `numElements == 0`.

**Regras dos testes:**

* Independe da **capacidade**; considera só a **quantidade usada**.

---

## Remoção

### `String remove(int index)`

**O que fazer:**

1. Validar índice de **acesso**: `0 <= index < numElements`; senão, **exceção**.
2. Guardar o **valor removido** (`elements[index]`).
3. **Deslocar à esquerda** os elementos de `[index+1, numElements-1]` para `[index, numElements-2]`.
4. Colocar `null` na **última posição válida anterior** (agora ociosa).
5. **Decrementar `numElements`**.
6. Retornar o **valor removido**.

**Regras dos testes:**

* Conferem **deslocamento** e **contenção** (o que era `i+1` vira `i`).
* Testam remoção do **primeiro** e de **índices do meio**.
* Índices inválidos → **exceção**.

---

### `boolean remove(String element)`

**O que fazer:**

1. Encontrar o **primeiro índice** do `element` (comparando por `equals` quando não for `null`, e por `== null` quando for).
2. Se **não existir**, retornar `false`.
3. Se existir, **reutilizar a lógica de remoção por índice** (deslocar à esquerda) e retornar `true`.

**Regras dos testes:**

* Deve remover **apenas a primeira ocorrência**.
* Deve **aceitar `null`** como alvo de remoção.
* Não pode alterar a ordem dos demais.

---

### `void clear()`

**O que fazer:**

1. Opcional: atribuir `null` às posições `[0 .. numElements-1]` para não reter referências.
2. **Zerar `numElements`**.
3. **Manter a capacidade** do array (não substituir por outro menor).

**Regras dos testes:**

* Após `clear`, um novo `add` deve **reaproveitar** o mesmo array (mesma **capacidade**).
* `clear` deve ser **idempotente** (chamar em lista vazia mantém estado).

---

## Consulta

### `boolean contains(String element)`

**O que fazer:**

* Retornar `true` se **qualquer** posição `0..numElements-1` contiver o `element` (compara por `equals` quando não for `null`; `== null` quando for).

**Regras dos testes:**

* Deve lidar corretamente com **`null`**.
* Não considerar posições **além de `numElements-1`** (lixo da capacidade não conta).

---

### `int indexOf(String element)`

**O que fazer:**

* Retornar o **índice da primeira ocorrência** dentro de `0..numElements-1`.
* Se não existir, retornar **`-1`**.
* Comparar com `equals` quando `element != null`; comparar `== null` quando `element == null`.

**Regras dos testes:**

* Testa casos com **duplicatas** (precisa ser a **primeira**).
* Testa **ausência** (retornar `-1`).
* Testa busca por **`null`**.

---

### `int lastIndexOf(String element)`

**O que fazer:**

* Retornar o **índice da última ocorrência** dentro de `0..numElements-1`.
* Se não existir, retornar **`-1`**.
* Comparar com `equals` quando `element != null`; comparar `== null` quando `element == null`.

**Regras dos testes:**

* Testa casos com **duplicatas** (precisa ser a **última**).
* Testa **ausência** (retornar `-1`).
* Testa busca por **`null`**.

---

## Regras de ouro (como os testes te avaliam)

1. **Capacidade dobra** sempre que necessário (4 → 8 → 16 → …), preservando a ordem.
2. **Shifts corretos**:

    * Inserção por índice → **direita**.
    * Remoção por índice → **esquerda**.
3. **`null` é elemento válido** em todas as operações (inserir, set, buscar, remover).
4. **Exceções de índice**:

    * Acesso (`get`, `set`, `remove(int)`): `0 <= index < numElements`.
    * Inserção (`add(int, ...)`): `0 <= index <= numElements`.
5. **`size()`** reflete **somente** `numElements`.
6. **`clear()`** **não** reduz a capacidade; apenas zera o tamanho (e opcionalmente limpa referências até `numElements-1`).

> Siga exatamente este comportamento. Os testes não usam outros métodos como apoio (ex.: não checam `add` via `get`/`size`), eles inspecionam **diretamente** `elements` e `numElements` via *reflection*, checam **capacidade**, **ordem** e **conteúdo** após cada operação.
