/**
 * Implementação de uma lista linear com armazenamento estático, baseado em array.
 */
public class StaticList<E> implements List<E> {
    private final E[] elements;
    private int numElements;

    /**
     * Constrói uma lista com um tamanho máximo.
     * @param maxSize O tamanho máximo da lista
     */
    public StaticList(int maxSize) {
        elements = (E[]) new Object[maxSize];
        numElements = 0;
    }

    /**
     * Informa a quantidade de elementos armazenados na lista.
     * @return A quantidade de elementos armazenados na lista.
     */
    public int numElements() {
        return numElements;
    }

    public boolean isEmpty() {
        return numElements == 0;
    }

    public boolean isFull() {
        return numElements == elements.length;
    }

    /**
     * Insere um novo elemento na posição indicada.
     * @param element O elemento a ser inserido
     * @param pos     A posição onde o elemento será inserido (iniciando em 0)
     */
    public void insert(E element, int pos) throws OverflowException, IndexOutOfBoundsException {
        // verifica se há espaço na lista
        if (isFull()) throw new OverflowException();

        // verifica se a posição é válida
        if (pos < 0 || pos > numElements) throw new IndexOutOfBoundsException();

        // desloca para a direita os elementos necessários,
        // abrindo espaço para o novo
        for (int i = numElements - 1; i >= pos; i--)
            elements[i + 1] = elements[i];

        // armazena o novo elemento e ajusta o total
        elements[pos] = element;
        numElements++;
    }

    public E remove(int pos) throws UnderflowException, IndexOutOfBoundsException {
        if (isEmpty()) throw new UnderflowException();

        // verifica se a posição é válida
        if (pos < 0 || pos >= numElements) throw new IndexOutOfBoundsException();

        // guarda uma referência temporária ao elemento removido
        E element = elements[pos];

        // desloca para a esquerda os elementos necessários,
        // sobrescrevendo a posição do que está sendo removido
        for (int i = pos; i < numElements - 1; i++)
            elements[i] = elements[i + 1];

        // define para null a posição antes ocupada pelo último,
        // para que a coleta de lixo possa atuar, e ajusta o total
        elements[numElements - 1] = null;
        numElements--;

        return element;
    }

    public E get(int pos) throws IndexOutOfBoundsException {
        // verifica se a posição é válida
        if (pos < 0 || pos >= numElements) throw new IndexOutOfBoundsException();
        return elements[pos];
    }

    public int search(E element) {
        for (int i = 0; i < numElements; i++)
            if (element.equals(elements[i]))
                return i;

        // se chegar até aqui, é porque não encontrou
        return -1;
    }

    /**
     * @return uma representação String da lista.
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < numElements; i++)
            s += elements[i] + " ";
        return s;
    }

    /**
     * Método público RECURSIVO que recebe um elemento por parâmetro e retorna a quantidade de vezes que este elemento aparece
     * na lista.
     * @param el o elemento a ser encontrado na lista
     * @return count
     * @throws IllegalArgumentException index out of bounds
     */
    public int contaElementos(E el) throws IllegalArgumentException {
        if (isEmpty()) throw new IllegalArgumentException();
        return contaElementosE(el, 0, 0);
    }

    /**
     * Método privado RECURSIVO que recebe um elemento por parâmetro e retorna a quantidade de vezes que este elemento aparece
     * na lista.
     * @param el o elemento a ser encontrado na lista
     * @param i  index do array
     * @return count
     */
    private int contaElementosE(E el, int i, int count) {
        if (i == numElements)
            return count;
        if (el == elements[i])
            count = contaElementosE(el, ++i, ++count);
        else
            count = contaElementosE(el, ++i, count);
        return count;
    }

    public static void main(String[] args) {
        StaticList<Object> words = new StaticList<>(6);
        words.insert("Hello", 0);
        words.insert(1, 1);
        words.insert("Beautiful", 2);
        words.insert('k', 3);
        words.insert(1, 4);
        words.insert("Hello", 5);

        System.out.println(words);
        System.out.println("--------------------------");

        Object achar = "Hello";
        int count1 = words.contaElementos(achar);
        System.out.println("Número ocorrências do elemento [" + achar + "]: " + count1);
        System.out.println("--------------------------");

        Object achar1 = 1;
        int count2 = words.contaElementos(achar1);
        System.out.println("Número ocorrências do elemento [" + achar1 + "]: " + count2);
        System.out.println("--------------------------");

        Object achar2 = 'k';
        int count3 = words.contaElementos(achar2);
        System.out.println("Número ocorrências do elemento [" + achar2 + "]: " + count3);
    }
}