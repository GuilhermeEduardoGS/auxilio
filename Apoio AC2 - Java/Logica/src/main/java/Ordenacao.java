public class Ordenacao {

    public static void selectionSort(int[] v) {
        int troca = 0;
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[i]) {
                    int aux = v[i];
                    v[i] = v[j];
                    v[j] = aux;
                    troca++;
                }
            }
        }
        System.out.println("Total de trocas Selection Sort: " + troca);
    }

    public static void selectionSortOtimizado(int[] v) {
        int troca = 0;
        for (int i = 0; i < v.length - 1; i++) {
            int indMenor = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[indMenor]) {
                    indMenor = j;
                    troca++;
                }
            }
            int aux = v[i];
            v[i] = v[indMenor];
            v[indMenor] = aux;
        }
        System.out.println("Total de trocas Selection Sort Otimizado: " + troca);
    }

    public static void bubbleSort(int[] v) {
        int troca = 0;
        for (int i = 0; i < v.length - 1; i++) {

            for (int j = 1; j < v.length - i; j++) {
                if (v[j - 1] > v[j]) {
                    int aux = v[j];
                    v[j] = v[j - 1];
                    v[j - 1] = aux;
                    aux = v[j - 1];
                }
            }
        }
        System.out.println("Total de trocas Bubble Sort: " + troca);
    }

    public static int pesquisaBinaria(int[] v, int x) {
        int inicio = 0;
        int fim = v.length - 1;
        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            if (x == v[meio]) {
                return meio;
            }
            else if (x > v[meio]) {
                inicio = meio + 1;
            }
            else {
                fim = meio - 1;
            }
        }
        return -1;
    }



}
