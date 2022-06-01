import java.util.Objects;

public class LinkedList<E> implements List<E> {
    private class Node {
        private final E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int tailIndex = -1;

    public LinkedList() { }

    public LinkedList(List<E> list) {
        Objects.requireNonNull(list);

        if (list.isEmpty())
            return;

        Node newNode = new Node(list.get(0));
        head = newNode;
        Node previousNode = newNode;
        tailIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            newNode = new Node(list.get(i));
            previousNode.next = newNode;
            previousNode = newNode;
            tailIndex++;
        }
        tail = newNode;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > tailIndex)
            throw new IndexOutOfBoundsException();

        Node pointer = head;
        for (int i = 0; i < index; i++)
            pointer = pointer.next;

        return pointer.data;
    }

    @Override
    public void add(E element) {
        Objects.requireNonNull(element);

        Node newNode = new Node(element);
        if (head == null)
            head = newNode;
        else
            tail.next = newNode;

        tail = newNode;
        tailIndex++;
    }

    @Override
    public void addAll(List<E> elements) {
        Objects.requireNonNull(elements);

        for (int i = 0; i < elements.size(); i++)
            add(elements.get(i));//Todo is safe self use ???
    }

    @Override
    public boolean remove(E element) {
        Objects.requireNonNull(element);

        if (element.equals(head.data)) {
            head = head.next;
            if (head == null)
                tail = null;

            tailIndex--;
            return true;
        }

        Node previous = head;
        Node pointer = head.next;
        int i = 1;
        while (pointer != null) {
            if (element.equals(pointer.data)) {
                if (i == tailIndex)//So I don't have to use equals to compare
                    tail = previous;

                previous.next = pointer.next;
                tailIndex--;
                return true;
            }
            previous = pointer;
            pointer = pointer.next;
            i++;
        }

        return false;
    }

    @Override
    public boolean removeAll(List<E> elements) {
        Objects.requireNonNull(elements);

        boolean changed = false;
        for (int i = 0; i < elements.size(); i++) {
            if (remove(elements.get(i)))//Todo is safe self use ???
                changed = true;
        }
        return changed;
    }

    @Override
    public boolean contains(E element) {
        Objects.requireNonNull(element);

        boolean found = false;
        Node pointer = head;
        while (pointer != null) {
            if (element.equals(pointer.data))
                return true;

            pointer = pointer.next;
        }
        return found;
    }

    @Override
    public int size() {
        return tailIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
