package homework.students;

import homework.students.command.Commands;
import homework.students.command.UserCommands;
import homework.students.model.Lesson;
import homework.students.model.Role;
import homework.students.model.User;
import homework.students.storage.LessonStorage;
import homework.students.storage.StudentStorage;
import homework.students.model.Student;
import homework.students.storage.UserStorage;

import java.util.Date;
import java.util.Scanner;

import static homework.students.util.DateUtil.stringToDate;

public class StudentDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentStorage studentStorage = new StudentStorage();
    private static LessonStorage lessonStorage = new LessonStorage();
    private static UserStorage userStorage = new UserStorage();

    private static User currentUser = null;


    public static void main(String[] args) {


        Lesson java = new Lesson("Java", "Teacher 1 ", 7, 35, stringToDate("10/08/2022"));
        lessonStorage.add(java);
        Lesson javaScript = new Lesson("Java Script", "Teacher 2 ", 6, 30, stringToDate("15/09/2022"));
        lessonStorage.add(javaScript);
        Lesson english = new Lesson("English", "Teacher 3 ", 3, 20, stringToDate("20/08/2022"));
        lessonStorage.add(english);

        User admin = new User("admin", "admin", "admin@mail.ru", "admin", Role.ADMIN);
        userStorage.add(admin);
        studentStorage.add(new Student("Poghos", "Pogosyan", 25, "077077077", "Gyumri", java, admin, new Date()));
        studentStorage.add(new Student("Petros", "Petrosyan", 35, "094094094", "Gyumri", javaScript, admin, new Date()));
        studentStorage.add(new Student("Martiros", "Martirosyan", 18, "098098098", "Gyumri", english, admin, new Date()));


        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case 0:
                    run = false;
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
        }

    }

    private static void login() {
        System.out.println("Please input email, password");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        User user = userStorage.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("User does not exists!");
        } else {
            if (!user.getPassword().equals(emailPassword[1])) {
                System.out.println("Password is wrong!");
            } else {
                currentUser = user;
                if (user.getRole() == Role.ADMIN) {
                    adminLogin();
                } else if (user.getRole() == Role.USER) {
                    userLogin();
                }
            }
        }
    }


    private static void register() {
        System.out.println("Please input name, surname, email, password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct data for user");
        } else {
            if (userStorage.getUserByEmail(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("User registered!");
            } else {
                System.out.println("User with " + userData[2] + "already exists. ");
            }
        }
    }

    private static void adminLogin() {
        boolean run = true;
        while (run) {
            Commands.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_STUDENTS_COUNT:
                    System.out.println(studentStorage.getSize());
                case Commands.DELETE_STUDENT_BY_INDEX:
                    deleteStudent();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    studentsByLesson();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeStudentLesson();
                    break;
                case ADD_LESSON:
                    addLesson();
                    changeStudentLesson();
                    break;
                case PRINT_ALL_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
        }
    }

    private static void userLogin() {
        System.out.println("Welcome, " + currentUser.getName());
        boolean run = true;
        while (run) {
            UserCommands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_STUDENTS_COUNT:
                    System.out.println(studentStorage.getSize());
                case PRINT_STUDENTS_BY_LESSON:
                    studentsByLesson();
                    break;
                case PRINT_ALL_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
        }
    }


    private static void addLesson() {
        System.out.println("Please input lesson name ");
        String name = scanner.nextLine();

        System.out.println("Please input lesson price ");
        try {
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Please input lesson teacher name ");
            String teacherName = scanner.nextLine();

            System.out.println("Please input lesson duration by month ");
            int duration = Integer.parseInt(scanner.nextLine());

            System.out.println("Please input lesson start date (14/04/2022)");
            String dateStr = scanner.nextLine();
            Lesson lesson = new Lesson(name, teacherName, duration, price, stringToDate(dateStr));
            lessonStorage.add(lesson);
            System.out.println("Lesson created!!! ");

        } catch (NumberFormatException e) {
            System.out.println("Please input valid number! ");
        }
    }

    private static void addStudent() {
        if (lessonStorage.getSize() == 0) {
            System.out.println("Please add lesson");
            addLesson();
        } else {
            lessonStorage.print();
            System.out.println("Please choose lesson index");
            try {
                int lessonIndex = Integer.parseInt(scanner.nextLine());
                Lesson lesson = lessonStorage.getLessonByIndex(lessonIndex);
                if (lesson == null) {
                    System.out.println("Please input correct index ");
                    addStudent();
                } else {
                    System.out.println("Please input student name");
                    String name = scanner.nextLine();
                    System.out.println("Please input student surname");
                    String surname = scanner.nextLine();
                    System.out.println("Please input student age");
                    String ageStr = scanner.nextLine();
                    System.out.println("Please input student phone number");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Please input student city");
                    String city = scanner.nextLine();

                    int age = Integer.parseInt(ageStr);
                    Student student = new Student(name, surname, age, phoneNumber, city, lesson, currentUser, new Date());
                    studentStorage.add(student);
                    System.out.println("Thank you, Student  was added");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input valid number! ");
            }
        }
    }

    private static void deleteStudent() {
        studentStorage.print();
        System.out.println("Please choose student index");
        int index = Integer.parseInt(scanner.nextLine());
        studentStorage.delete(index);
    }

    private static void studentsByLesson() {
        System.out.println("Please input lesson name");
        String lessonName = scanner.nextLine();
        studentStorage.printStudentsByLesson(lessonName);
    }


    private static void changeStudentLesson() {
        studentStorage.print();
        System.out.println("Please choose student index");
        try {
            int studentIndex = Integer.parseInt(scanner.nextLine());
            Student student = studentStorage.getStudentByIndex(studentIndex);
            if (student != null) {
                lessonStorage.print();
                System.out.println("Please input lesson index");
                int lessonIndex = Integer.parseInt(scanner.nextLine());
                Lesson lesson = lessonStorage.getLessonByIndex(lessonIndex);
                if (lesson == null) {
                    System.out.println(" Please input correct index ");
                    changeStudentLesson();
                } else {
                    student.setLesson(lesson);
                    System.out.println("Lesson was changed! ");
                }

            }
        } catch (NumberFormatException e) {
            System.out.println(" Please input correct index! ");
        }
    }
}