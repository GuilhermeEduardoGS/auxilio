[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ZndjZMTS)
<div style="text-align:center;">
  <a href="https://rubberduckdebugging.com/" target="_blank">
    <img src="rubber-duck.png" alt="Patinho de borracha" width="200"/>
  </a>
</div>

# Reverter Apenas Letras

Dada uma string `s`, reverta a string de acordo com as seguintes regras:

- Todos os caracteres que **não** são letras do alfabeto inglês devem permanecer na mesma posição.
- Todas as letras do alfabeto inglês (maiúsculas ou minúsculas) devem ser invertidas.

Retorne `s` após a inversão.

---

## Exemplos

**Exemplo 1:**

Entrada:  
`s = "ab-cd"`

Saída:  
`"dc-ba"`

---

**Exemplo 2:**

Entrada:  
`s = "a-bC-dEf-ghIj"`

Saída:  
`"j-Ih-gfE-dCba"`

---

**Exemplo 3:**

Entrada:  
`s = "Test1ng-Leet=code-Q!"`

Saída:  
`"Qedo1ct-eeLg-ntse-T!"`

---

## Restrições

- `1 <= s.length <= 100`
- `s` consiste apenas de caracteres com valores ASCII no intervalo `[33, 122]`.
- `s` **não** contém aspas (`"`) nem barra invertida (`\`).

---

## 💡 Dica Importante

Em Java, **Strings são imutáveis**, ou seja, você não pode trocar diretamente os caracteres dentro dela.  
Por isso, uma técnica comum é **converter a string em um vetor de caracteres** usando:

```java
char[] chars = s.toCharArray();
```

Assim, você pode manipular cada posição como se fosse um array e, ao final, reconstruir a string com:

```java
return new String(chars);
```

