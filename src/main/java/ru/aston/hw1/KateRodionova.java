package ru.aston.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Класс KateRodionova содержит методы рыботы с объектом типа лист
 * @param <E> дженерик
 */
public class KateRodionova<E> implements IntensiveList<E> {

    private Object[] array;
    private int size = 0;
    private int VALUE_OF_EXTEND = 2;

    private int DEFAULT_CAPACITY = 10;

    public KateRodionova() {
        array = new Object[DEFAULT_CAPACITY];
    }


    /** Метод возвращает размер списка
     * @return array.length - количество элементов
     */
    @Override
    public int size() {
        return array.length;
    }

    /** Метод добавляет элемент типа E в объект
     * @param element добавляемый элемент
     */
    @Override
    public void add(E element) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * VALUE_OF_EXTEND);
        }
        array[size] = element;
        size++;
    }


    /** метод добавляет элемент типа E в указанную позицию index в объекте array
     * @param element добавляемый элемент
     * @param index индекс элемента
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }


    /**
     * Метод возвращает элемент по указанному индексу из списка, представленного объектом array
     * @param index индекс элемента
     * @return элемент
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        return (E) array[index];
    }


    /**
     * Метод заменяет элемент по заданному индексу в объекте array
     * на указанный элемент
     * @param index индекс элемента
     * @param element элемент
     * @return предыдущее значение элемента
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = (E) array[index];
        array[index] = element;
        return oldValue;
    }

    /**
     * Метод удаляет элемент из списка на позиции index
     * @param index индекс удаляемого элемента
     * @return возвращает удаленный элемент
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        System.out.printf("Удален элемент с индексом %s\n", index);
        return (E) array[index];
    }


    /**
     * Метод удаляет все элементы из объекта array, то есть чистит содержимое
     * capacity приводиn к дефолтному
     */
    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    /**
     * Метод быстрой сортировки списка
     * Метод quickSort() использует переданный объект Comparator для сравнения элементов списка и
     * упорядочивает их согласно заданным правилам сравнения.
     * @param comparator объект класса Comparator, который используется
     *                   для сравнения элементов списка, который нужно отсортировать.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSorting(0, size - 1, comparator);
    }


    /**
     * Метод quickSorting рекурсивно вызывает метод partition(), далее метод quickSorting()
     * вызывается рекурсивно для двух подсписков, левого и правого от опорного элемента.
     * разделяет список на подсписки вокруг опорного элемента
     * и сортирует каждый подсписок рекурсивно, пока весь список не будет отсортирован.
     * @param low элемент меньше опорного элемента pivot
     * @param high элемент больше опорного элемента pivot
     * @param comparator объект класса Comparator, который используется
     *                   для сравнения элементов списка, который нужно отсортировать.
     */
    private void quickSorting(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSorting(low, pivotIndex - 1, comparator);
            quickSorting(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Метод выбирает опорный элемент (pivot) и перемещает элементы в списке так,
     * чтобы все элементы меньше опорного элемента оказались слева от него, а все элементы
     * больше или равные опорному - справа от него.
     * @param low элемент меньше опорного элемента pivot
     * @param high элемент больше опорного элемента pivot
     * @param comparator объект класса Comparator, который используется
     *                   для сравнения элементов списка, который нужно отсортировать.
     * @return i + 1
     */
    private int partition(int low, int high, Comparator<E> comparator) {
        E pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) < 0) {
                i++;
                E temp = get(i);
                array[i] = array[j];
                array[j] = temp;
            }
        }
        E temp = get(i + 1);
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }


    /**
     * Метод проверяет, отсортирован ли список элементов в объекте.
     * Проходит по всем элементам списка и сравнивает каждый элемент с предыдущим с помощью метода compareTo().
     * @return false если встречается пара элементов, где предыдущий больше текущего (список не отсортирован)
     * и true если все элементы списка находятся в правильном порядке (список отсортированы по возрастанию).
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (((Comparable<E>) array[i]).compareTo((E) array[i - 1]) < 0) {
                return false;
            }
        }
        System.out.println("Список отсортирован");
        return true;
    }


    /**
     * Метод удаляет элементы из списка до тех пор, пока его размер
     * не станет равен или меньше указанного значения size.
     * @param size размер объекта, до заданного значения которого необходимо уменьшить список
     */
    @Override
    public void split(int size) {
        if (size > this.size) {
            throw new IllegalArgumentException("Размер уменьшения не может быть больше текущего размера списка!");
        }
        List newList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newList.add(array[i]);
        }
        array = new List[]{newList};
        System.out.printf("Список уменьшен до размера %s", size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(array));
        return sb.toString();
    }
}

