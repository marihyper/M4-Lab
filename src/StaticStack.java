public class StaticStack<E> implements Stack<E> {
    private E[] elements;
    private int top;

    /**
     * Constrói uma lista com um tamanho máximo.
     * @param maxSize O tamanho máximo da lista
     */
    public StaticStack(int maxSize) {
        elements = (E[]) new Object[maxSize];
        top = -1;
    }

    /**
     * Informa a quantidade de elementos armazenados na lista.
     * @return A quantidade de elementos armazenados na lista.
     */
    public int numElements() {
        return top + 1;
    }

    /**
     * Informa se a pilha está vazia.
     * @return Verdadeiro se a pilha estiver vazia, falso caso contrário.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Informa se a pilha está cheia.
     * @return Verdadeiro se a pilha estiver cheia, falso caso contrário.
     */
    public boolean isFull() {
        return top == elements.length - 1;
    }

    /**
     * Adiciona um novo elemento à pilha.
     * @param element O elemento a ser adicionado
     */
    public void push(E element) throws OverflowException {
        if (isFull())
            throw new OverflowException();
        elements[++top] = element;
    }

    /**
     * Retira um elemento da pilha.
     * @return O elemento retirado
     */
    public E pop() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();
        E element = elements[top];
        elements[top--] = null; // p/ coleta de lixo
        return element;
    }

    /**
     * Informa qual o elemento no topo da pilha.
     * @return O elemento atualmente no topo da pilha
     */
    public E top() throws UnderflowException {
        if (isEmpty())
            throw new UnderflowException();
        return elements[top];
    }

    public E get(int pos) throws IndexOutOfBoundsException {
        // verifica se a posição é válida
        if (pos < 0 || pos >= numElements()) throw new IndexOutOfBoundsException();
        return elements[pos];
    }
    public String toString() {
        if (isEmpty())
            return "[Empty]";
        else {
            String s = "";
            for (int i = 0; i < numElements(); i++)
                s += elements[i];
            return s;
        }
    }

    /**
     * Método verifica se uma expressão matemática tem os parênteses agrupados de forma correta (1) se o número de
     * parênteses à esquerda e à direita é igual; e (2) se td parêntese aberto é seguido, posteriormente, por um
     * fechamento de parêntese.
     * @param stack stack containing a math expression
     * @return true if brackets are balanced, false otherwise
     */
    public boolean checkBrackets(StaticStack<Character> stack) {
        if (stack.isEmpty()) {
            System.out.println("Empty Expression!");
            return stack.isEmpty();
        }
        StaticStack <Character> stack2 = new StaticStack<>(stack.numElements());

        for (int i = 0; i < stack.numElements(); i++) {
            Character c = stack.get(i);
            if (c.equals('{') || c.equals('[') || c.equals('('))
                stack2.push(c);
            else if (c.equals('}') || c.equals(']') || c.equals(')')){
//                checking whether two characters are opening AND closing of same type 
//                OR if the stack2 is empty
                if(stack2.isEmpty() || !(stack2.top().equals('(')) && (stack.get(i)).equals(')'))
                    return false;
                else if(stack2.isEmpty() || !(stack2.top().equals('[')) && (stack.get(i)).equals(']'))
                    return false;
                else if(stack2.isEmpty() || !(stack2.top().equals('{')) && (stack.get(i)).equals('}'))
                    return false;
                else
                    stack2.pop();
            }
        }
        return stack2.isEmpty();
    }

    public static void main(String[] args) {
        String string1 = "((A+B)-(C+D))";
        String string2 = "((A+B)";
        String string3 = "";
        String string4 = "{A+[B+C*(D+F)+G]+H}";

//        Testing string1
        StaticStack<Character> stack1 = new StaticStack<>(string1.length());
        for( int i = 0; i < string1.length(); i++)
            stack1.push(string1.charAt(i));
        System.out.println(stack1);
        System.out.println("Check Brackets = " + stack1.checkBrackets(stack1));
        System.out.println("--------------------------");

//        Testing string2
        StaticStack<Character> stack2 = new StaticStack<>(string2.length());
        for( int i = 0; i < string2.length(); i++)
            stack2.push(string2.charAt(i));
        System.out.println(stack2);
        System.out.println("Check Brackets = " + stack2.checkBrackets(stack2));
        System.out.println("--------------------------");

//        Testing string3
        StaticStack<Character> stack3 = new StaticStack<>(string3.length());
        for( int i = 0; i < string3.length(); i++)
            stack3.push(string3.charAt(i));
        System.out.println(stack3);
        System.out.println("Check Brackets = " + stack3.checkBrackets(stack3));
        System.out.println("--------------------------");

//        Testing string4
        StaticStack<Character> stack4 = new StaticStack<>(string4.length());
        for( int i = 0; i < string4.length(); i++)
            stack4.push(string4.charAt(i));
        System.out.println(stack4);
        System.out.println("Check Brackets = " + stack4.checkBrackets(stack4));
        System.out.println("--------------------------");
    }
}