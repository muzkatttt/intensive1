package ru.aston.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс NameSurname содержит методы рыботы с объектом типа лист
 * @param <E> дженерик
 */
public class NameSurname<E> implements IntensiveList<E>{

    private final List<E> myArrayList;

    public NameSurname() {
        myArrayList = new ArrayList<>();
    }

    /** Метод возвращает размер списка
     * @return myArrayList.size() - количество элементов
     */
    @Override
    public int size() {
        System.out.println(myArrayList.size());
        return myArrayList.size();
    }

    /** Метод добавляет элемент типа E в объект myArrayList
     * @param element добавляемый элемент
     */
    @Override
    public void add(E element) {
        myArrayList.add(element);
    }

    /** метод добавляет элемент типа E в указанную позицию index в объекте myArrayList
     * @param element добавляемый элемент
     * @param index индекс элемента
     */
    @Override
    public void add(int index, E element) {
        myArrayList.add(index, element);
    }

    /**
     * Метод возвращает элемент по указанному индексу из списка, представленного объектом myArrayList
     * @param index индекс элемента
     * @return элемент
     */
    @Override
    public E get(int index) {
        return myArrayList.get(index);
    }

    /**
     * Метод заменяет элемент по заданному индексу в объекте myArrayList
     * на указанный элемент
     * @param index индекс элемента
     * @param element элемент
     * @return предыдущее значение элемента
     */
    @Override
    public E set(int index, E element) {
        return myArrayList.set(index, element);
    }

    /**
     * Метод удаляет элемент из списка на позиции index
     * @param index индекс удаляемого элемента
     * @return возвращает удаленный элемент
     */
    @Override
    public E remove(int index) {
        return myArrayList.remove(index);
    }


    /**
     * Метод удаляет все элементы из объекта myArrayList, то есть чистит содержимое
     */
    @Override
    public void clear() {
        myArrayList.clear();
    }

    /**
     * Метод быстрой сортировки
     * Внутри метода выполняется вызов статического метода sort() класса Collections.
     * Метод sort() использует переданный объект Comparator для сравнения элементов списка и
     * упорядочивает их согласно заданным правилам сравнения.
     * @param comparator объект класса Comparator, который используется
     *                   для сравнения элементов списка, который нужно отсортировать.
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        myArrayList.sort(comparator);
    }

    /**
     * Метод проверяет, отсортирован ли список элементов в объекте myArrayList.
     * Проходит по всем элементам списка и сравнивает каждый элемент с предыдущим с помощью метода compareTo().
     * @return false если встречается пара элементов, где предыдущий больше текущего (список не отсортирован)
     * и @true если все элементы списка находятся в правильном порядке (список отсортированы по возрастанию).
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < myArrayList.size(); i++) {
            if (((Comparable) myArrayList.get(i - 1)).compareTo(myArrayList.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Метод удаляет элементы из списка myArrayList до тех пор, пока его размер
     * не станет равен или меньше указанного значения size.
     * @param size размер объекта, до заданного значения которого необходимо уменьшить список myArrayList
     */
    @Override
    public void split(int size) {
        while (myArrayList.size() > size) {
            myArrayList.remove(myArrayList.size() - 1);
        }
    }

}