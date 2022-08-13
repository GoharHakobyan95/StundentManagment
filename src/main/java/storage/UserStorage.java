package homework.students.storage;

import homework.students.model.User;

public class UserStorage {
    private User[] array = new User[10];
    private int size = 0;

    public void add(User student) {
        if (size == array.length) {
            extend();
        }
        array[size++] = student;
    }

    private void extend() {
        User[] temp = new User[array.length + 10];
        System.arraycopy(array, 0, temp, 0, size);
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
            System.out.println("User deleted");
        } else {
            System.out.println("Index out of bound");
        }
    }

    public User getUserByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }


}


























