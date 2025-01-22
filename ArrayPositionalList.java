/**
 * Anmoldeep Singh
 * 3149800
 */
public class ArrayPositionalList<E> implements PositionalList<E> {

    private static class Location<E> implements Position<E> {

        private int index;
        private E element;

        public Location(E e, int i) {
            index = i;
            element = e;
        }

        public E getElement() throws IllegalStateException {
            if (index == -1) {
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public void setElement(E e) {
            element = e;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int i) {
            index = i;
        }
    }

    public static final int CAPACITY = 16;
    private Location<E>[] data;
    private int size = 0;

    public ArrayPositionalList() {
        this(CAPACITY);
    }

    public ArrayPositionalList(int capacity) {
        data = (Location<E>[]) new Location[capacity];
    }

    // Helper method to ensure the array has enough capacity
    private void ensureCapacity(int capacity) {
        if (capacity > data.length) {
            Location<E>[] newData = (Location<E>[]) new Location[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> first() {
        if (isEmpty()) {
            return null;
        }
        return data[0];
    }

    public Position<E> last() {
        if (isEmpty()) {
            return null;
        }
        return data[size - 1];
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Location<E> loc = (Location<E>) p;
        int index = loc.getIndex();
        if (index == 0) {
            return null;
        }
        return data[index - 1];
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Location<E> loc = (Location<E>) p;
        int index = loc.getIndex();
        if (index == size - 1) {
            return null;
        }
        return data[index + 1];
    }

    public Position<E> addFirst(E e) {
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= 0; i--) {
            data[i + 1] = data[i];
            data[i + 1].setIndex(i + 1);
        }
        Location<E> newLoc = new Location<>(e, 0);
        data[0] = newLoc;
        size++;
        return newLoc;
    }

    public Position<E> addLast(E e) {
        ensureCapacity(size + 1);
        Location<E> newLoc = new Location<>(e, size);
        data[size] = newLoc;
        size++;
        return newLoc;
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Location<E> loc = (Location<E>) p;
        int index = loc.getIndex();
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
            data[i + 1].setIndex(i + 1);
        }
        Location<E> newLoc = new Location<>(e, index);
        data[index] = newLoc;
        size++;
        return newLoc;
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Location<E> loc = (Location<E>) p;
        int index = loc.getIndex();
        ensureCapacity(size + 1);
        for (int i = size - 1; i > index; i--) {
            data[i + 1] = data[i];
            data[i + 1].setIndex(i + 1);
        }
        Location<E> newLoc = new Location<>(e, index + 1);
        data[index + 1] = newLoc;
        size++;
        return newLoc;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Location<E> loc = (Location<E>) p;
        E oldElement = loc.getElement();
        loc.setElement(e);
        return oldElement;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Location<E> loc = (Location<E>) p;
        int index = loc.getIndex();

        E removedElement = loc.getElement();
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            data[i].setIndex(i);
        }
        data[size - 1] = null;
        loc.setIndex(-1); // Invalidate the position
        loc.setElement(null);
        size--;
        return removedElement;
    }

}
