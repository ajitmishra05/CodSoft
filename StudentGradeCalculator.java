import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎓 Welcome to the Student Grade Calculator!");

        // Step 1: Take input for number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();

        double totalMarks = 0;

        // Step 2: Input marks for each subject
        for (int i = 1; i <= numSubjects; i++) {
            double marks;
            while (true) {
                System.out.print("Enter marks for subject " + i + " (out of 100): ");
                marks = sc.nextDouble();
                if (marks >= 0 && marks <= 100) {
                    totalMarks += marks;
                    break;
                } else {
                    System.out.println("❌ Invalid input! Marks should be between 0 and 100.");
                }
            }
        }

        // Step 3: Calculate average percentage
        double average = totalMarks / numSubjects;

        // Step 4: Determine grade
        String grade;
        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B";
        } else if (average >= 60) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Step 5: Display results
        System.out.println("\n📊 ----- Student Report ----- 📊");
        System.out.printf("Total Marks: %.2f\n", totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        System.out.println("\n✅ Thank you for using the Grade Calculator!");
        sc.close();
    }
}
