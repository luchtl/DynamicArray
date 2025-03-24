
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author Admin
 */
public class DynamicArray<T> implements DynamicCollection<T> {

    private T[] capacity;
    private int size;

    public DynamicArray() {
        this.capacity = (T[]) new Object[8];
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toJavaArray() {
        Object[] obj = new Object[size];
        System.arraycopy(capacity, 0, obj, 0, size);
        return obj;
    }

    @Override
    public Object[] getUnderlyingArrayCopy() {
        Object[] fullCopy = new Object[capacity.length];
        System.arraycopy(capacity, 0, fullCopy, 0, capacity.length);
        return this.capacity;
    }

    public String toString() {
        return Arrays.toString(this.toJavaArray());
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
        return capacity[index];
    }

    public T getLast() {
        return capacity[size - 1];
    }

    public T getRandom() {
        if (isEmpty()) {
            throw new IllegalStateException("Can't get random elements from empty array.");
        }
        Random modnar = new Random();
        int modnarIndex = modnar.nextInt(size);
        return capacity[modnarIndex];
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        }
        capacity[index] = value;
    }

    @Override
    public void add(T value) {
        if (size == capacity.length) {
            int newCapacity = capacity.length * 2;
            capacity = Arrays.copyOf(capacity, newCapacity);
        }
        capacity[size] = value;
        size++; // increment size counter
    }

    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
        T removed = capacity[index];
        if (index < size - 1){
            for(int i = index; i < size - 1; i++){
                capacity[i] = capacity[i+1];
            }
        }
        capacity[size - 1] = null;
        size--;
        return removed;
    }
}
