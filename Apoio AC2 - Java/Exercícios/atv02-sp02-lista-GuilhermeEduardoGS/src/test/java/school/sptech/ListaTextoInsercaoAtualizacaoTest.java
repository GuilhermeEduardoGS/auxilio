package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static school.sptech.TestReflectionUtils.*;

class ListaTextoInsercaoAtualizacaoTest {

    // Helper para montar uma ListaTexto a partir de strings
    private ListaTexto listaFrom(String... valores) {
        var lista = new ListaTexto();
        var arr = newArray(Math.max(4, valores.length)); // capacidade mínima 4
        for (int i = 0; i < valores.length; i++) {
            arr[i] = valores[i];
        }
        preload(lista, arr, valores.length);
        return lista;
    }

    // -------- add(String) --------

    @Test
    @DisplayName("add: insere no final quando há espaço -> numElements incrementa e valor no último índice")
    void add_insereNoFinal_quandoHaEspaco() {
        var lista = new ListaTexto();
        // Pré-carga: 2 elementos em capacidade 4
        var arr = newArray(4);
        arr[0] = "A";
        arr[1] = "B";
        preload(lista, arr, 2);

        assertTrue(lista.add("C"), "add deve retornar true");
        assertEquals(3, numElements(lista), "numElements deve ser 3");
        assertArraySlotEquals(elements(lista), 2, "C");
    }

    @Test
    @DisplayName("add: faz resize ao inserir 5º elemento (cap 4 -> 8) e preserva ordem")
    void add_fazResizeCapacidadeDobraEPreservaOrdem() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = "A"; arr[1] = "B"; arr[2] = "C"; arr[3] = "D";
        preload(lista, arr, 4);

        assertTrue(lista.add("E"));
        String[] novo = elements(lista);
        assertEquals(8, novo.length, "Capacidade deve dobrar para 8");
        assertEquals(5, numElements(lista), "numElements deve ser 5");
        assertEquals("A", novo[0]); assertEquals("B", novo[1]);
        assertEquals("C", novo[2]); assertEquals("D", novo[3]);
        assertEquals("E", novo[4]);
    }

    @Test
    @DisplayName("add: aceita null como elemento e o armazena corretamente")
    void add_aceitaNull() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = "X";
        preload(lista, arr, 1);

        assertTrue(lista.add(null));
        assertEquals(2, numElements(lista));
        assertNull(elements(lista)[1], "Deve armazenar null no índice 1");
    }

    // -------- add(int, String) --------

    @Test
    @DisplayName("add(index): insere no meio e desloca elementos à direita")
    void addIndex_insereNoMeio_deslocaParaDireita() {
        var lista = new ListaTexto();
        var arr = newArray(6);
        arr[0] = "A"; arr[1] = "B"; arr[2] = "D"; arr[3] = "E";
        preload(lista, arr, 4);

        assertTrue(lista.add(2, "C"));
        String[] a = elements(lista);
        assertEquals(5, numElements(lista));
        assertEquals("A", a[0]);
        assertEquals("B", a[1]);
        assertEquals("C", a[2]);
        assertEquals("D", a[3]);
        assertEquals("E", a[4]);
    }

    @Test
    @DisplayName("add(index): index == numElements (insere no fim) sem depender do add simples")
    void addIndex_insereNoFim_quandoIndexIgualTamanho() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = "A";
        preload(lista, arr, 1);

        assertTrue(lista.add(1, "B"));
        assertEquals(2, numElements(lista));
        assertArraySlotEquals(elements(lista), 1, "B");
    }

    @Test
    @DisplayName("add(index): index inválido (<0 ou >numElements) -> lança exceção")
    void addIndex_invalido_lancaExcecao() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        assertThrows(IndexOutOfBoundsException.class, () -> lista.add(-1, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.add(1, "X"));
    }

    // -------- addAll(ListaTexto) --------

    @Test
    @DisplayName("addAll: insere todos de outra ListaTexto e retorna true quando origem não-vazia")
    void addAll_insereTodos_quandoOrigemNaoVazia() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        var origem = listaFrom("A", "B");
        assertTrue(lista.addAll(origem));
        assertEquals(2, numElements(lista));
        assertEquals("A", elements(lista)[0]);
        assertEquals("B", elements(lista)[1]);
    }

    @Test
    @DisplayName("addAll: retorna false quando origem está vazia (nada inserido)")
    void addAll_origemVazia_retornaFalse() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        var origemVazia = listaFrom(); // sem elementos
        assertFalse(lista.addAll(origemVazia));
        assertEquals(0, numElements(lista));
    }

    @Test
    @DisplayName("addAll: realiza múltiplos resizes se necessário e mantém ordem ao copiar de outra ListaTexto")
    void addAll_variosResizes_mantemOrdem() {
        var lista = new ListaTexto();
        var inicial = newArray(4);
        inicial[0] = "X";
        preload(lista, inicial, 1);

        var origem = listaFrom("A","B","C","D","E","F","G");
        assertTrue(lista.addAll(origem));

        String[] arrFinal = elements(lista);
        assertTrue(arrFinal.length >= 8, "Capacidade deve ter aumentado pelo menos uma vez");
        assertEquals(8, numElements(lista));
        assertEquals("X", arrFinal[0]);
        assertEquals("A", arrFinal[1]);
        assertEquals("G", arrFinal[7]);
    }

    // -------- set(int, String) --------

    @Test
    @DisplayName("set: substitui valor e retorna o antigo")
    void set_substituiERetornaAntigo() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = "A"; arr[1] = "B";
        preload(lista, arr, 2);

        String old = lista.set(1, "BB");
        assertEquals("B", old);
        assertEquals("BB", elements(lista)[1]);
        assertEquals(2, numElements(lista), "set não altera numElements");
    }

    @Test
    @DisplayName("set: aceita null e mantém tamanho inalterado")
    void set_aceitaNull() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = "A";
        preload(lista, arr, 1);

        String old = lista.set(0, null);
        assertEquals("A", old);
        assertNull(elements(lista)[0]);
        assertEquals(1, numElements(lista));
    }

    @Test
    @DisplayName("set: index inválido (<0 ou >=numElements) -> lança exceção")
    void set_invalido_lancaExcecao() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        assertThrows(IndexOutOfBoundsException.class, () -> lista.set(0, "X"));
        preload(lista, newArray(4), 2);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.set(2, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.set(-1, "X"));
    }
}
