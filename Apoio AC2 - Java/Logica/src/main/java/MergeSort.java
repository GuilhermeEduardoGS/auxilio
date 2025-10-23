public class MergeSort {

    public static void mergeSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);
            merge(vetor, inicio, meio, fim);
        }
    }

    private static void merge(int[] vetor, int inicio, int meio, int fim) {
        int[] aux = new int[vetor.length];
        for (int i = inicio; i <= fim; i++) aux[i] = vetor[i];

        int i = inicio, j = meio + 1, k = inicio;
        while (i <= meio && j <= fim) {
            if (aux[i] <= aux[j]) vetor[k++] = aux[i++];
            else vetor[k++] = aux[j++];
        }
        while (i <= meio) vetor[k++] = aux[i++];
    }
}


