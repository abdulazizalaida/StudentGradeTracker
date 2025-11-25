import java.util.Scanner;

// =======================================================================
// Project: Student Grade Tracker
// Contributors:
//
// 1) Mansour Trad Alotaibi – Team Leader
//    • Designed the main structure of the project.
//    • Implemented core OOP concepts and main logic.
//    • Reviewed and organized the overall architecture.
//
// 2) Abdulaziz Bader Al-Iday – Team Member
//    • Added menu input handling and validation logic.
//    • Helped with debugging and improving user interaction.
//    • Organized code formatting and pushed updates via GitHub.
// =======================================================================

public class Main {

    public static void main(String[] args) {
        GradeManager manager = new GradeManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Grade Tracker ===");
            System.out.println("1. Add student");
            System.out.println("2. Add course");
            System.out.println("3. Add grade for a student in a course");
            System.out.println("4. Show all students");
            System.out.println("5. Show all courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter initial grade (0 - 100): ");
                    double initialGrade;

                    try {
                        initialGrade = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        initialGrade = 0;
                    }

                    if (initialGrade < 0 || initialGrade > 100) {
                        initialGrade = 0;
                    }

                    Student student = new Student(name, id, initialGrade);
                    manager.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;

                case "2":
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();

                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();

                    Course course = new Course(courseName, courseCode);
                    manager.addCourse(course);
                    System.out.println("Course added successfully.");
                    break;

                case "3":
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();

                    System.out.print("Enter course code: ");
                    String courseCodeForGrade = scanner.nextLine();

                    System.out.print("Enter grade (0 - 100): ");
                    double grade;

                    try {
                        grade = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade input.");
                        break;
                    }

                    if (grade < 0 || grade > 100) {
                        System.out.println("Grade must be between 0 and 100.");
                        break;
                    }

                    Student foundStudent = manager.findStudentById(studentId);
                    Course foundCourse = manager.findCourseByCode(courseCodeForGrade);

                    if (foundStudent == null) {
                        System.out.println("Student not found.");
                    } else if (foundCourse == null) {
                        System.out.println("Course not found.");
                    } else {
                        manager.addGrade(studentId, courseCodeForGrade, grade);
                        System.out.println("Grade added successfully.");
                    }
                    break;

                case "4":
                    System.out.println("\nAll students:");
                    manager.printAllStudents();
                    break;

                case "5":
                    System.out.println("\nAll courses:");
                    manager.printAllCourses();
                    break;

                case "6":
                    System.out.println("Exiting program...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

