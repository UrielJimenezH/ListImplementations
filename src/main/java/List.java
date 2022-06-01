public interface List<E> {
    E get(int index);
    void add(E element);
    void addAll(List<E> elements);
    boolean remove(E element);
    boolean removeAll(List<E> elements);
    boolean contains(E element);
    int size();
    boolean isEmpty();
}
