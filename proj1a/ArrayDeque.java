public class ArrayDeque<T> {
    private static final int MIN_CAPACITY = 16;
    private static final int DEFAULT_CAPACITY = 8;

    private T[] items;
    private int size;
    private int head;  // index of first element
    private int tail;  // index of last element

    @SuppressWarnings("unchecked")  // 添加这行注解
    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = 0;
        tail = -1;  // empty sentinel
    }

    public void addFirst(T x) {
        update();

        head = (head - 1 + items.length) % items.length;
        items[head] = x;
        size++;
        if (size == 1) {
            tail = head;
        }
    }

    public void addLast(T x) {
        update();

        tail = (tail + 1) % items.length;
        items[tail] = x;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removeIterm = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size--;

        // empty queue case
        if (size == 0) {
            head = 0;
            tail = -1;
        }

        // handle resize
        update();

        return removeIterm;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removeIterm = items[tail];
        items[tail] = null;
        tail = (tail - 1 + items.length) % items.length;
        size--;
        if (size == 0) {
            head = 0;
            tail = -1;
        }

        update();

        return removeIterm;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int getIndex = (head + index) % items.length;
        return items[getIndex];
    }

    @SuppressWarnings("unchecked")  // 添加这行注解
    private void resize(int capacity) {
        if (capacity < size) {
            throw new IllegalArgumentException("新容量必须至少为大小: " + size);
        }
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        head = 0;
        tail = size - 1;
    }

    private void update() {
        if (size == items.length) {
            resize(Math.max(DEFAULT_CAPACITY, items.length * 2));
        } else if (size > 0 && size <= items.length / 4 && items.length >= MIN_CAPACITY) {
            resize(items.length / 2);
        }
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            /* System.out.print(get(i) + " ");  */
            int index = (head + i) % items.length;
            System.out.print(items[index]);
            if (i < size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}