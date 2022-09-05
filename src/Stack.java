/**
 * Interface que define o comportamento de uma Pilha.
 */
public interface Stack<E> {
    /**
     * Informa se a pilha está vazia.
     * @return Verdadeiro se a pilha estiver vazia, falso caso contrário.
     */
    public boolean isEmpty();

    /**
     * Informa se a pilha está cheia.
     * @return Verdadeiro se a pilha estiver cheia, falso caso contrário.
     */
    public boolean isFull();

    /**
     * Informa a quantidade de elementos armazenados na pilha.
     * @return A quantidade de elementos armazenados na pilha.
     */
    public int numElements();

    /**
     * Adiciona um novo elemento à pilha.
     * @param element O elemento a ser adicionado
     */
    public void push(E element) throws OverflowException;

    /**
     * Retira um elemento da pilha. xxxx
     * @return O elemento retirado
     */
    public E pop() throws UnderflowException;

    /**
     * Informa qual o elemento no topo da pilha.
     * @return O elemento atualmente no topo da pilha
     */
    public E top() throws UnderflowException;

    /**
     * Retorna o elemento da posição indicada, sem removê-lo.
     * @param pos A posição do elemento
     * @return O elemento
     */
    public E get(int pos);
}

