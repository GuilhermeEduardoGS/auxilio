public class BubbleSort{

    public static void bubbleSort(int[] vetor) {
        boolean trocou;
        for (int i = 0; i < vetor.length - 1; i++) {
            trocou = false;
            for (int j = 0; j < vetor.length - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                    trocou = true;
                }
            }
            if (!trocou) break; // otimização
        }
    }

}

