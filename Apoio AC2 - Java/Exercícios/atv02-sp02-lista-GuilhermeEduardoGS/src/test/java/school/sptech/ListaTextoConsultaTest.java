package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static school.sptech.TestReflectionUtils.*;

class ListaTextoConsultaTest {

    // -------- contains(String) --------

    @Test
    @DisplayName("contains: true quando elemento existe")
    void contains_true() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]="A"; arr[1]="B"; arr[2]="C";
        preload(lista, arr, 3);

        assertTrue(lista.contains("B"));
    }

    @Test
    @DisplayName("contains: false quando elemento não existe")
    void contains_false() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]="A"; arr[1]="B";
        preload(lista, arr, 2);

        assertFalse(lista.contains("Z"));
    }

    @Test
    @DisplayName("contains: suporta procurar por null")
    void contains_null() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]=null; arr[1]="X";
        preload(lista, arr, 2);

        assertTrue(lista.contains(null));
        assertFalse(lista.contains("Y"));
    }

    // -------- indexOf(String) --------

    @Test
    @DisplayName("indexOf: retorna índice da primeira ocorrência")
    void indexOf_primeiraOcorrencia() {
        var lista = new ListaTexto();
        var arr = newArray(6);
        arr[0]="A"; arr[1]="B"; arr[2]="B"; arr[3]="C";
        preload(lista, arr, 4);

        assertEquals(1, lista.indexOf("B"));
    }

    @Test
    @DisplayName("indexOf: retorna -1 quando não encontrado")
    void indexOf_naoEncontrado() {
        var lista = new ListaTexto();
        preload(lista, newArray(4), 0);
        assertEquals(-1, lista.indexOf("X"));
    }

    @Test
    @DisplayName("indexOf: lida com null como chave de busca")
    void indexOf_null() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]=null; arr[1]="A"; arr[2]=null;
        preload(lista, arr, 3);

        assertEquals(0, lista.indexOf(null));
    }

    // -------- lastIndexOf(String) --------

    @Test
    @DisplayName("lastIndexOf: retorna índice da última ocorrência")
    void lastIndexOf_ultimaOcorrencia() {
        var lista = new ListaTexto();
        var arr = newArray(6);
        arr[0]="A"; arr[1]="B"; arr[2]="B"; arr[3]="C"; arr[4]="B";
        preload(lista, arr, 5);

        assertEquals(4, lista.lastIndexOf("B"));
    }

    @Test
    @DisplayName("lastIndexOf: retorna -1 quando não encontrado")
    void lastIndexOf_naoEncontrado() {
        var lista = new ListaTexto();
        var arr = newArray(3);
        arr[0]="A";
        preload(lista, arr, 1);

        assertEquals(-1, lista.lastIndexOf("Z"));
    }

    @Test
    @DisplayName("lastIndexOf: lida com null corretamente")
    void lastIndexOf_null() {
        var lista = new ListaTexto();
        var arr = newArray(5);
        arr[0]=null; arr[1]="A"; arr[2]=null;
        preload(lista, arr, 3);

        assertEquals(2, lista.lastIndexOf(null));
    }
}
