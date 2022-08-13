package homework.students.command;

public interface Commands {

    int LOGOUT = 0;
    int ADD_STUDENT = 1;
    int PRINT_ALL_STUDENTS = 2;
    int PRINT_STUDENTS_COUNT = 3;
    int DELETE_STUDENT_BY_INDEX = 4;
    int PRINT_STUDENTS_BY_LESSON = 5;
    int CHANGE_STUDENT_LESSON = 6;
    int ADD_LESSON = 7;
    int PRINT_ALL_LESSONS = 8;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printAdminCommands() {
        System.out.println("Please input " + LOGOUT + " for logut");
        System.out.println("Please input " + ADD_STUDENT + " for add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " for print all students");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " for print student count");
        System.out.println("Please input " + DELETE_STUDENT_BY_INDEX + " for delete student by index");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for print student by lesson");
        System.out.println("Please input " + CHANGE_STUDENT_LESSON + " for change student's lesson");
        System.out.println("Please input " + ADD_LESSON + " for add lesson");
        System.out.println("Please input " + PRINT_ALL_LESSONS + " for print all lesson");
    }

    static void printLoginCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + LOGIN + " for login");
        System.out.println("Please input " + REGISTER + " for registered");
    }

}