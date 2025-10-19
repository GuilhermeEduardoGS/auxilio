[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ZbJjRFJK)
<div style="text-align:center;">
  <a href="https://rubberduckdebugging.com/" target="_blank">
    <img src="rubber-duck.png" alt="Patinho de borracha" width="200"/>
  </a>
</div>

# 🦆 Reverter String Completa

Implemente o método:

```java
public static String reverseLetters(String s)
```

Esse método deve receber uma string `s` e retornar a mesma string **invertida por completo**, ou seja:

* O **último caractere** da string passa a ser o primeiro.
* O **primeiro caractere** da string passa a ser o último.
* Todos os caracteres intermediários também devem ser invertidos de posição.

Importante: todos os caracteres devem ser considerados — **letras, números, espaços e símbolos**.

---

## ✅ Exemplos

**Exemplo 1:**
Entrada:
`s = "abc"`

Saída:
`"cba"`

---

**Exemplo 2:**
Entrada:
`s = "ab1cd"`

Saída:
`"dc1ba"`

---

**Exemplo 3:**
Entrada:
`s = "a-bC-dEf-ghIj"`

Saída:
`"jIhg-fEd-Cb-a"`

---

**Exemplo 4:**
Entrada:
`s = ""`

Saída:
`""`

---

**Exemplo 5:**
Entrada:
`s = "Test1ng-Leet=code-Q!"`

Saída:
`"!Q-edoc=teeL-gn1tseT"`

---

## 📏 Restrições

* `0 <= s.length <= 100`
* `s` consiste apenas de caracteres ASCII no intervalo `[33, 122]`.
* `s` **não** contém aspas (`"`) nem barra invertida (`\`).

---

## 🎯 Objetivo

Fazer todos os testes abaixo passarem:

```java
class SolutionTest {
    @Test
    @DisplayName("Deve inverter string simples")
    void testReverseSimple() {
        assertEquals("cba", Solution.reverseLetters("abc"));
    }

    @Test
    @DisplayName("Deve inverter string com números")
    void testReverseWithNumbers() {
        assertEquals("dc1ba", Solution.reverseLetters("ab1cd"));
    }

    @Test
    @DisplayName("Deve inverter string com símbolos")
    void testReverseWithSymbols() {
        assertEquals("jIhg-fEd-Cb-a", Solution.reverseLetters("a-bC-dEf-ghIj"));
    }

    @Test
    @DisplayName("Deve inverter string vazia (continua vazia)")
    void testReverseEmpty() {
        assertEquals("", Solution.reverseLetters(""));
    }

    @Test
    @DisplayName("Deve inverter string misturando letras, números e símbolos")
    void testReverseMixed() {
        assertEquals("!Q-edoc=teeL-gn1tseT", Solution.reverseLetters("Test1ng-Leet=code-Q!"));
    }
}
```