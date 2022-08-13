package homework.students.command;

public interface UserCommands {
    int LOGOUT = 0;
    int ADD_STUDENT = 1;
    int PRINT_ALL_STUDENTS = 2;
    int PRINT_STUDENTS_COUNT = 3;
    int PRINT_STUDENTS_BY_LESSON = 4;
    int PRINT_ALL_LESSONS = 5;

    static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " for logut");
        System.out.println("Please input " + ADD_STUDENT + " for add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " for print all students");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " for print student count");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for print student by lesson");
        System.out.println("Please input " + PRINT_ALL_LESSONS + " for print all lesson");
    }
}


