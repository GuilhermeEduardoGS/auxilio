package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static school.sptech.TestReflectionUtils.*;

class ListaTextoRemocaoTest {

    // -------- remove(int) --------

    @Test
    @DisplayName("remove(index): remove do meio, retorna valor e desloca à esquerda")
    void removeIndex_doMeio_desloca() {
        var lista = new ListaTexto();
        var arr = newArray(6);
        arr[0]="A"; arr[1]="B"; arr[2]="C"; arr[3]="D"; arr[4]="E";
        preload(lista, arr, 5);

        String removed = lista.remove(2);
        assertEquals("C", removed);
        assertEquals(4, numElements(lista));
        String[] a = elements(lista);
        assertEquals("A", a[0]); assertEquals("B", a[1]);
        assertEquals("D", a[2]); assertEquals("E", a[3]);
    }

    @Test
    @DisplayName("remove(index): index 0 remove o primeiro e puxa todos")
    void removeIndex_primeiro() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]="A"; arr[1]="B"; arr[2]="C";
        preload(lista, arr, 3);

        String removed = lista.remove(0);
        assertEquals("A", removed);
        assertEquals(2, numElements(lista));
        assertEquals("B", elements(lista)[0]);
        assertEquals("C", elements(lista)[1]);
    }

    @Test
    @DisplayName("remove(index): index inválido -> lança exceção")
    void removeIndex_invalido() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
        preload(lista, newArray(4), 2);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(2));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
    }

    // -------- remove(String) --------

    @Test
    @DisplayName("remove(element): remove primeira ocorrência e desloca à esquerda")
    void removeElemento_primeiraOcorrencia() {
        var lista = new ListaTexto();
        var arr = newArray(6);
        arr[0]="A"; arr[1]="B"; arr[2]="X"; arr[3]="X"; arr[4]="C";
        preload(lista, arr, 5);

        assertTrue(lista.remove("X"));
        assertEquals(4, numElements(lista));
        String[] a = elements(lista);
        assertEquals("A", a[0]); assertEquals("B", a[1]);
        assertEquals("X", a[2]); // segunda X virou a primeira
        assertEquals("C", a[3]);
    }

    @Test
    @DisplayName("remove(element): elemento inexistente -> retorna false e não altera estrutura")
    void removeElemento_inexistente_false() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0]="A"; arr[1]="B";
        preload(lista, arr, 2);

        assertFalse(lista.remove("Z"));
        assertEquals(2, numElements(lista));
        assertEquals("A", elements(lista)[0]);
        assertEquals("B", elements(lista)[1]);
    }

    @Test
    @DisplayName("remove(element): aceita null e remove quando presente")
    void removeElemento_null() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]=null; arr[1]="A"; arr[2]=null;
        preload(lista, arr, 3);

        assertTrue(lista.remove(null));
        assertEquals(2, numElements(lista));
        assertEquals("A", elements(lista)[0]);
        assertNull(elements(lista)[1]); // o null da posição 2 moveu pra 1
    }

    // -------- clear() --------

    @Test
    @DisplayName("clear: zera numElements mas mantém a capacidade")
    void clear_zeraTamanho_mantemCapacidade() {
        var lista = new ListaTexto();
        var arr = newArray(8);
        arr[0]="A"; arr[1]="B";
        preload(lista, arr, 2);

        int capAntes = elements(lista).length;
        lista.clear();
        assertEquals(0, numElements(lista));
        assertEquals(capAntes, elements(lista).length);
    }

    @Test
    @DisplayName("clear: após limpar, próximos adds devem reutilizar o array existente")
    void clear_permiteReusoArray() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0]="A"; arr[1]="B"; arr[2]="C";
        preload(lista, arr, 3);

        int capAntes = elements(lista).length;
        lista.clear();
        assertTrue(lista.add("X"));
        assertEquals(capAntes, elements(lista).length, "Capacidade não deve mudar após primeiro add pós-clear");
        assertEquals(1, numElements(lista));
        assertEquals("X", elements(lista)[0]);
    }

    @Test
    @DisplayName("clear: idempotente (limpar lista já vazia mantém estado)")
    void clear_idempotente() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        lista.clear();
        assertEquals(0, numElements(lista));
        assertEquals(4, elements(lista).length);
    }
}
