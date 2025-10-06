public class LinkedListDeque<T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        Node(Node prev, T item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size--;
        return last.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node x, int index) {
        if (index == 0) {
            return x.item;
        }
        return getRecursiveHelper(x.next, index - 1);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(current.item);
            if (i < size - 1) {
                System.out.print(" ");
            }
            current = current.next;
        }
        System.out.println();
    }
}