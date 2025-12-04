# Sistema de Orçamentos

A empresa precisa de um módulo simples para registrar e consultar orçamentos. Cada orçamento deve ser criado com base na quantidade de itens e no preço unitário informado pelo cliente. A partir desses dados, o sistema deverá calcular o valor total do orçamento.

A regra de cálculo é:

**valorTotal = quantidade × precoUnitario**

O aluno deverá implementar um serviço capaz de realizar três operações: criar um orçamento, buscar um orçamento específico e listar todos os orçamentos já cadastrados. O cálculo do valor total deve ocorrer exclusivamente na camada de serviço.

O sistema deverá disponibilizar exatamente três métodos no controlador:

**criarOrcamento** no endpoint `POST /orcamentos`
**buscarPorId** no endpoint `GET /orcamentos/{id}`
**listarTodos** no endpoint `GET /orcamentos`

Ao criar um orçamento, o cliente deverá enviar um JSON com quantidade e preço unitário.
O retorno de sucesso deverá seguir o padrão definido pela classe de resposta.
Em casos de requisição inválida, o sistema deverá retornar **HTTP 400** sem corpo.
Se um orçamento pesquisado não existir, o sistema deverá retornar **HTTP 404** sem corpo.

---

## Estrutura de envio (OrcamentoRequest)

```json
{
  "quantidade": 3,
  "precoUnitario": 49.9
}
```

---

## Estrutura de retorno (OrcamentoResponse)

A resposta deve conter o código gerado para o orçamento (string) e o valor total calculado.

```json
{
  "codigo": "ORC-001",
  "valorTotal": 149.7
}
```

---

## Exemplo de listagem completa

```json
[
  {
    "codigo": "ORC-001",
    "valorTotal": 149.7
  },
  {
    "codigo": "ORC-002",
    "valorTotal": 50.0
  }
]
```

---

## Respostas de erro (sem corpo)

**HTTP 400** para requisição inválida
**HTTP 404** para orçamento não encontrado

---
