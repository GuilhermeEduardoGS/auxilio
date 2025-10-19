package school.sptech;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

class TestReflectionUtils {

    static Field field(Class<?> clazz, String name) {
        try {
            Field f = clazz.getDeclaredField(name);
            f.setAccessible(true);
            return f;
        } catch (Exception e) {
            throw new RuntimeException("Campo não encontrado: " + name, e);
        }
    }

    static Object get(Object target, String field) {
        try {
            Field f = field(target.getClass(), field);
            return f.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void set(Object target, String field, Object value) {
        try {
            Field f = field(target.getClass(), field);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static String[] newArray(int capacity) {
        return (String[]) Array.newInstance(String.class, capacity);
    }

    static void preload(ListaTexto lista, String[] dados, int usados) {
        // Seta o array interno e o numElements exatamente como queremos para o cenário
        set(lista, "elements", dados);
        set(lista, "numElements", usados);
    }

    static String[] elements(ListaTexto lista) {
        return (String[]) get(lista, "elements");
    }

    static int numElements(ListaTexto lista) {
        return (int) get(lista, "numElements");
    }

    static void assertArraySlotEquals(String[] arr, int index, String expected) {
        Assertions.assertEquals(expected, arr[index],
                "Esperava elements[" + index + "] == " + expected);
    }
}
