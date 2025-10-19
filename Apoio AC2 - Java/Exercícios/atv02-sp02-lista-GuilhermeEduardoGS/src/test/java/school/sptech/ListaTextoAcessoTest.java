package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static school.sptech.TestReflectionUtils.*;

class ListaTextoAcessoTest {

    // -------- get(int) --------

    @Test
    @DisplayName("get: retorna elemento existente pelo índice")
    void get_retornaElemento() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = "A"; arr[1] = "B";
        preload(lista, arr, 2);

        assertEquals("A", lista.get(0));
        assertEquals("B", lista.get(1));
    }

    @Test
    @DisplayName("get: index inválido (<0 ou >=numElements) -> lança exceção")
    void get_invalido_lancaExcecao() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);

        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
        preload(lista, newArray(4), 1);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(1));
    }

    @Test
    @DisplayName("get: permite recuperar null quando armazenado")
    void get_suportaNull() {
        var lista = new ListaTexto();
        var arr = newArray(4);
        arr[0] = null;
        preload(lista, arr, 1);

        assertNull(lista.get(0));
    }

    // -------- size() --------

    @Test
    @DisplayName("size: retorna 0 em lista vazia (sem depender de add)")
    void size_listaVazia() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);
        assertEquals(0, lista.size());
    }

    @Test
    @DisplayName("size: reflete numElements configurado por reflection")
    void size_refleteNumElements() {
        var lista = new ListaTexto();
        preload(lista, newArray(10), 7);
        assertEquals(7, lista.size());
    }

    @Test
    @DisplayName("size: não deve retornar a capacidade (elements.length)")
    void size_naoCapacidade() {
        var lista = new ListaTexto();
        preload(lista, newArray(10), 3);
        assertNotEquals(elements(lista).length, lista.size(),
                "size deve refletir quantidade usada, não a capacidade");
    }

    // -------- isEmpty() --------

    @Test
    @DisplayName("isEmpty: true quando numElements == 0")
    void isEmpty_trueQuandoZero() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);
        assertTrue(lista.isEmpty());
    }

    @Test
    @DisplayName("isEmpty: false quando numElements > 0")
    void isEmpty_falseQuandoTemElemento() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 1);
        assertFalse(lista.isEmpty());
    }

    @Test
    @DisplayName("isEmpty: independe da capacidade do array")
    void isEmpty_independeDaCapacidade() {
        var lista = new ListaTexto();
        preload(lista, newArray(100), 0);
        assertTrue(lista.isEmpty());
    }
}
