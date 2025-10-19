package school.sptech;

public class ListaTexto {

    private String[] elements;
    private int numElements;

    public ListaTexto() {
        this.elements = new String[4];
        this.numElements = 0;
    }

    // -------------------------------
    // INSERÇÃO
    // -------------------------------

    /**
     * Adiciona um elemento no final da lista.
     * Se o array estiver cheio, deve dobrar de tamanho (resize).
     * Retorna true se conseguiu inserir.
     */
    public boolean add(String element) {

        if  (numElements == elements.length){
            String[] elements2 = new String[elements.length * 2];

            for (int i = 0; i < elements.length; i++) {
                elements2[i] = elements[i];
            }
            elements = elements2;
        }
        elements[numElements] = element;
        numElements++;
        return true;
    }

    /**
     * Adiciona um elemento em uma posição específica da lista.
     * Deve "empurrar" os elementos seguintes uma posição à frente.
     * Se index for inválido, deve lançar exceção.
     */
    public boolean add(int index, String element) {

        if(index < 0 || index > numElements){
            throw new IndexOutOfBoundsException();
        }

        if (numElements == elements.length){
            String[] elements2 = new String[elements.length * 2];

            for (int i = 0; i < elements.length; i++) {
                elements2[i] = elements[i];
            }
            elements = elements2;
        }

        for (int i = numElements; i > index ; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        numElements++;
        return true;

    }

    /**
     * Adiciona todos os elementos de um array na lista atual.
     * Pode chamar add(String) para cada item.
     * Retorna true se pelo menos um elemento foi adicionado.
     */
    public boolean addAll(ListaTexto other) {

        if (other == null || other.numElements == 0) {
            return false;
        }

        for (int i = 0; i < other.numElements; i++) {
            add(other.elements[i]);
        }

        return true;
    }

    /**
     * Substitui o valor da posição indicada por outro.
     * Retorna o valor antigo que estava nessa posição.
     * Se index for inválido, deve lançar exceção.
     */
    public String set(int index, String element) {

        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException();
        }

        String old = elements[index];
        elements[index] = element;

        return old;
    }

    // -------------------------------
    // ACESSO
    // -------------------------------

    /**
     * Retorna o elemento na posição indicada.
     * Se index for inválido, deve lançar exceção.
     */
    public String get(int index) {

        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException();
        }

        return elements[index];

    }

    /**
     * Retorna a quantidade de elementos armazenados (não a capacidade do array).
     */
    public int size() {
        return numElements;
    }

    /**
     * Retorna true se a lista estiver vazia (size == 0).
     */
    public boolean isEmpty() {

        if (numElements == 0){
            return true;
        }

        return false;
    }

    // -------------------------------
    // REMOÇÃO
    // -------------------------------

    /**
     * Remove o elemento na posição indicada.
     * Deve "puxar" os elementos seguintes para trás.
     * Retorna o valor removido.
     */
    public String remove(int index) {

        if(index < 0 || index >= numElements){
            throw new IndexOutOfBoundsException();
        }

        String removido = elements[index];

        for (int i = index; i < numElements - 1; i++) {
            elements[i] = elements [i + 1];
        }

        elements[numElements - 1] = null;
        numElements--;

        return removido;

    }

    /**
     * Remove a primeira ocorrência do elemento informado.
     * Retorna true se encontrou e removeu.
     */
    public boolean remove(String element) {

        for (int i = 0; i < numElements; i++) {

            if(element == null){
                if(elements[i] == null){
                    remove(i);
                    return true;
                }
            }
            else {
                if (elements[i].equals(element)){
                    remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Remove todos os elementos da lista (zerar numElements).
     * Mantém a capacidade do array.
     */
    public void clear() {

        for (int i = 0; i < numElements; i++) {
            elements[i] = null;
        }

        numElements = 0;
    }

    // -------------------------------
    // CONSULTA
    // -------------------------------

    /**
     * Retorna true se a lista contiver o elemento informado.
     */
    public boolean contains(String element) {

        for (int i = 0; i < numElements; i++) {

            if (elements[i] == null){
                if (element == null){
                    return true;
                }
            }
            else {
                if(elements[i].equals(element)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Retorna a posição da primeira ocorrência do elemento.
     * Se não existir, retorna -1.
     */
    public int indexOf(String element) {

        for (int i = 0; i < numElements; i++) {

            if(elements[i] == null || element == null){
                return i;
            }

            if(elements[i] != null && elements[i].equals(element)){
                return i;
            }
        }

        return -1;
    }

    /**
     * Retorna a posição da última ocorrência do elemento.
     * Se não existir, retorna -1.
     */
    public int lastIndexOf(String element) {

        for (int i = numElements - 1; i >= 0; i--) {

            if(elements[i] == null || element == null){
                return i;
            }

            if(elements[i] != null && elements[i].equals(element)){
                return i;
            }
        }

        return -1;
    }
}