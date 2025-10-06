public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int head;  // index of first element
    private int tail;  // index of last element 

    @SuppressWarnings("unchecked")  // 添加这行注解
    public ArrayDeque() {
        items = (T[]) new Object[8];
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
            throw new RuntimeException("Queue is empty");
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
            throw new RuntimeException("Queue is empty");
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
    public void resize(int capacity) {
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

    public void update() {
        if (size == items.length) {
            resize(Math.max(8, items.length * 2));
        } else if (size > 0 && size <= items.length / 4 && items.length >= 16) {
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