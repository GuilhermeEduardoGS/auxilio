public static void quickSort(int[] vetor, int inicio, int fim) {
    if (inicio < fim) {
        int p = particiona(vetor, inicio, fim);
        quickSort(vetor, inicio, p - 1);
        quickSort(vetor, p + 1, fim);
    }
}

private static int particiona(int[] vetor, int inicio, int fim) {
    int pivo = vetor[fim];
    int i = inicio - 1;
    for (int j = inicio; j < fim; j++) {
        if (vetor[j] <= pivo) {
            i++;
            int tmp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = tmp;
        }
    }
    int tmp = vetor[i + 1];
    vetor[i + 1] = vetor[fim];
    vetor[fim] = tmp;
    return i + 1;
}
