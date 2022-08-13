package homework.students.storage;

import homework.students.model.Lesson;

public class LessonStorage {
    private Lesson[] array = new Lesson[10];
    private int size = 0;

    public void add(Lesson lesson) {
        if (size == array.length) {
            extend();
        }
        array[size++] = lesson;
    }

    private void extend() {
        Lesson[] temp = new Lesson[array.length + 10];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + "." + array[i] + " ");
        }
    }

    public int getSize() {
        return size;
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[index] = array[index + 1];
            }
            size--;
            System.out.println("Lesson deleted");
        } else {
            System.out.println("Index out of bound");
        }
    }

    public Lesson getLessonByIndex(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }

}
