# 💡 Exemplo: Cálculo de Frete

Vamos desenvolver uma **API REST** que simula um **serviço de cotação de fretes**.

---

## 🧠 Objetivo

Calcular o **valor do frete** com base em três tipos disponíveis:

- **NORMAL**
- **EXPRESSO**
- **TRANSPORTADORA**

Cada tipo tem suas próprias regras de prazo de entrega e valor.

---

## 📦 Regras de Cálculo

### 1. Frete **NORMAL**
- 🕐 Prazo: até **12 dias úteis**
- 💰 Custo:
  - Valor fixo: **R$ 10,00**
  - Acréscimo: **10% do peso em kg**

### 2. Frete **EXPRESSO**
- 🕐 Prazo: até **3 dias úteis**
- 💰 Custo:
  - Valor fixo: **R$ 30,00**
  - Acréscimo: **50% do peso em kg**

### 3. Frete **TRANSPORTADORA**
- 🕐 Prazo: até **6 dias úteis**
- 💰 Custo:
  - Valor fixo: **R$ 20,00**
  - Acréscimo: **20% do peso em kg**

> 📌 **Obs.:** O peso será informado em **quilos** e o valor final será retornado em **reais (R$)**.

---

## 🗃️ Estrutura da Entidade

Você deverá criar uma entidade que armazene as seguintes informações:

- Tipo de frete (`NORMAL`, `EXPRESSO`, `TRANSPORTADORA`)
- Valor da encomenda
- Peso da encomenda (em kg)
- Valor do frete calculado

---

## 🔗 Endpoints da API

### `POST /fretes` — Criar uma nova cotação

#### Requisição (Exemplo):
```json
{
  "tipo": "NORMAL",
  "valorEncomenda": 100.00,
  "pesoEmKg": 2.0
}
```

#### Resposta (Exemplo):
```json
{
  "id": 1,
  "tipo": "NORMAL",
  "descricao": "Entrega em até 12 dias úteis",
  "valorFrete": 10.2
}
```

### Validações:

- `tipo`: não pode ser nulo, vazio ou conter apenas espaços em branco.
- `valorEncomenda`: obrigatório, deve ser **maior que R$ 10,00**.
- `pesoEmKg`: obrigatório, deve ser **maior que 1 kg**.

---

### `GET /fretes` — Listar todas as cotações de frete

#### Resposta (Exemplo):
```json
[
  {
    "id": 1,
    "tipo": "NORMAL",
    "valorFrete": 10.2
  },
  {
    "id": 2,
    "tipo": "EXPRESSO",
    "valorFrete": 31.0
  },
  {
    "id": 3,
    "tipo": "TRANSPORTADORA",
    "valorFrete": 20.4
  }
]
```

---

## 🔧 Dicas para melhorar seu projeto

- Use um `enum` para representar os tipos de frete (`NORMAL`, `EXPRESSO`, `TRANSPORTADORA`).
- Na entidade, use a anotação `@Enumerated(EnumType.STRING)` para salvar o enum no banco como texto.
- Caso o tipo de frete seja inválido, lance uma exceção e retorne **HTTP 422 - Unprocessable Entity**.