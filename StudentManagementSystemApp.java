import java.io.*;
import java.util.*;

// -------------------- Student Class --------------------
class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;

    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return String.format("Name: %s | Roll No: %s | Grade: %s | Age: %d", 
                              name, rollNumber, grade, age);
    }
}

// -------------------- Student Management System Class --------------------
class StudentManagementSystem {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add Student
    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("✅ Student added successfully.");
    }

    // Remove Student by Roll Number
    public void removeStudent(String rollNumber) {
        Student found = searchStudent(rollNumber);
        if (found != null) {
            students.remove(found);
            saveToFile();
            System.out.println("✅ Student removed successfully.");
        } else {
            System.out.println("⚠️ No student found with Roll No: " + rollNumber);
        }
    }

    // Search Student by Roll Number
    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    // Display All Students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No student records found.");
        } else {
            System.out.println("\n=== Student List ===");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Edit Student Info
    public void editStudent(String rollNumber, Scanner sc) {
        Student s = searchStudent(rollNumber);
        if (s != null) {
            System.out.print("Enter new name (or press Enter to skip): ");
            String newName = sc.nextLine().trim();
            if (!newName.isEmpty()) s.setName(newName);

            System.out.print("Enter new grade (or press Enter to skip): ");
            String newGrade = sc.nextLine().trim();
            if (!newGrade.isEmpty()) s.setGrade(newGrade);

            System.out.print("Enter new age (or press Enter to skip): ");
            String newAgeStr = sc.nextLine().trim();
            if (!newAgeStr.isEmpty()) {
                try {
                    int newAge = Integer.parseInt(newAgeStr);
                    s.setAge(newAge);
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Invalid age format. Age not updated.");
                }
            }

            saveToFile();
            System.out.println("✅ Student details updated.");
        } else {
            System.out.println("⚠️ Student not found.");
        }
    }

    // -------------------- File Handling --------------------
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠️ Error loading data: " + e.getMessage());
        }
    }
}

// -------------------- Main Application Class --------------------
public class StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    String roll = sc.nextLine().trim();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine().trim();
                    System.out.print("Enter age: ");
                    int age = 0;
                    try {
                        age = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Invalid age format. Setting age = 0");
                    }

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                        System.out.println("⚠️ All fields except age are required.");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, age));
                    }
                    break;

                case "2":
                    System.out.print("Enter roll number of student to edit: ");
                    String editRoll = sc.nextLine().trim();
                    sms.editStudent(editRoll, sc);
                    break;

                case "3":
                    System.out.print("Enter roll number of student to remove: ");
                    String removeRoll = sc.nextLine().trim();
                    sms.removeStudent(removeRoll);
                    break;

                case "4":
                    System.out.print("Enter roll number to search: ");
                    String searchRoll = sc.nextLine().trim();
                    Student found = sms.searchStudent(searchRoll);
                    if (found != null)
                        System.out.println("✅ Student Found:\n" + found);
                    else
                        System.out.println("⚠️ Student not found.");
                    break;

                case "5":
                    sms.displayAllStudents();
                    break;

                case "6":
                    System.out.println("👋 Exiting Student Management System. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Please try again.");
            }
        }
    }
}
