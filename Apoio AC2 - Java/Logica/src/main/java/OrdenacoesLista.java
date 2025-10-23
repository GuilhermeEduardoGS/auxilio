import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdenacoesLista {

    // ---------------- BUBBLE SORT ----------------
    public static void bubbleSort(List<Integer> lista) {
        boolean trocou;
        for (int i = 0; i < lista.size() - 1; i++) {
            trocou = false;
            for (int j = 0; j < lista.size() - 1 - i; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    int tmp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, tmp);
                    trocou = true;
                }
            }
            if (!trocou) break; // otimização
        }
    }

    // ---------------- SELECTION SORT ----------------
    public static void selectionSort(List<Integer> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j) < lista.get(minIndex)) {
                    minIndex = j;
                }
            }
            int tmp = lista.get(i);
            lista.set(i, lista.get(minIndex));
            lista.set(minIndex, tmp);
        }
    }

    // ---------------- INSERTION SORT ----------------
    public static void insertionSort(List<Integer> lista) {
        for (int i = 1; i < lista.size(); i++) {
            int chave = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j) > chave) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, chave);
        }
    }

    // ---------------- MERGE SORT ----------------
    public static void mergeSort(List<Integer> lista, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(lista, inicio, meio);
            mergeSort(lista, meio + 1, fim);
            merge(lista, inicio, meio, fim);
        }
    }

    private static void merge(List<Integer> lista, int inicio, int meio, int fim) {
        List<Integer> aux = new ArrayList<>(lista);

        int i = inicio, j = meio + 1, k = inicio;
        while (i <= meio && j <= fim) {
            if (aux.get(i) <= aux.get(j)) {
                lista.set(k++, aux.get(i++));
            } else {
                lista.set(k++, aux.get(j++));
            }
        }

        while (i <= meio) {
            lista.set(k++, aux.get(i++));
        }
    }

    // ---------------- QUICK SORT ----------------
    public static void quickSort(List<Integer> lista, int inicio, int fim) {
        if (inicio < fim) {
            int p = particiona(lista, inicio, fim);
            quickSort(lista, inicio, p - 1);
            quickSort(lista, p + 1, fim);
        }
    }

    private static int particiona(List<Integer> lista, int inicio, int fim) {
        int pivo = lista.get(fim);
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (lista.get(j) <= pivo) {
                i++;
                int tmp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, tmp);
            }
        }
        int tmp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fim));
        lista.set(fim, tmp);
        return i + 1;
    }

    // ---------------- TESTE ----------------
    public static void main(String[] args) {
        List<Integer> dados = new ArrayList<>(Arrays.asList(5, 3, 8, 4, 2, 7, 1, 6));

        System.out.println("Original: " + dados);

        // Teste qualquer um dos algoritmos:
//        bubbleSort(dados);
//        selectionSort(dados);
//        insertionSort(dados);
//        mergeSort(dados, 0, dados.size() - 1);
//        quickSort(dados, 0, dados.size() - 1);

        System.out.println("Ordenado: " + dados);
    }
}
