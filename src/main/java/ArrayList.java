import java.util.Objects;

public /*final*/ class ArrayList<E> implements List<E>{
    /*private*/protected Object[] elements;
    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;//Index of next element added
    private static final float loadThreshold = 0.75f;
    private static final int loadIncreaseFactor = 2;


    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();

        elements = new Object[initialCapacity];
    }

    public ArrayList(List<E> elements) {
        Objects.requireNonNull(elements);

        int capacity;
        if (elements.size() > INITIAL_CAPACITY)
            capacity = elements.size() * loadIncreaseFactor;
        else if (INITIAL_CAPACITY * loadThreshold > elements.size())
            capacity = INITIAL_CAPACITY;
        else
            capacity = INITIAL_CAPACITY * loadIncreaseFactor;

        this.elements = new Object[capacity];

        for (int i = 0; i < elements.size(); i++)
            this.elements[i] = elements.get(i);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        return (E) elements[index];//Todo is this a safe cast ??? if don't, remove SuppressWarning annotation and fix cast
    }

    @Override
    public void add(E element) {
        Objects.requireNonNull(element);//Todo is this required ???

        elements[size++] = element;
        if (size > elements.length * loadThreshold)
            increaseElementsCapacity();
    }

    private void increaseElementsCapacity() {
        Object[] elements = new Object[this.elements.length * loadIncreaseFactor];
        for (int i = 0; i < size; i++) {
            elements[i] = this.elements[i];
        }
        this.elements = elements;
    }

    @Override
    public void addAll(List<E> elements) {
        Objects.requireNonNull(elements);//Todo is this required ???

        if (size + elements.size() > this.elements.length * loadThreshold)
            increaseElementsCapacity();

        for (int i = 0; i < elements.size(); i++)
            add(elements.get(i));//Todo safe self use ???
    }

    @Override
    public boolean remove(E element) {
        Objects.requireNonNull(element);//Todo is this required ???

        int foundAtIndex = -1;
        for (int i = 0; i < elements.length; i++) {
            if (element.equals(elements[i])) {
                foundAtIndex = i;
                break;
            }
        }

        if (foundAtIndex == -1)
            return false;

        //Todo is last element set to null ???
        for (int i = foundAtIndex; i < size; i++)
            elements[i] = elements[i + 1];

        size--;
        return true;
    }

    @Override
    public boolean removeAll(List<E> elements) {
        Objects.requireNonNull(elements);//Todo is this required ???

        boolean listChanged = false;

        for (int i = 0; i < elements.size(); i++) {
            if (remove(elements.get(i)))//Todo safe self use ???
                listChanged = true;
        }
        return listChanged;
    }

    @Override
    public boolean contains(E element) {
        Objects.requireNonNull(element);//Todo is this required ???

        boolean existsOnList = false;
        for (Object o : elements) {
            if (element.equals(o)) {
                existsOnList = true;
                break;
            }
        }
        return existsOnList;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
