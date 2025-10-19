import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int score = 0; // to track rounds won
        boolean playAgain = true;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int lowerLimit = 1;
            int upperLimit = 100;
            int numberToGuess = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            int maxAttempts = 5;
            boolean guessedCorrectly = false;

            System.out.println("\nI'm thinking of a number between " + lowerLimit + " and " + upperLimit + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                System.out.print("Attempt " + attempt + ": Enter your guess ➤ ");
                int guess = sc.nextInt();

                if (guess == numberToGuess) {
                    System.out.println("✅ Correct! You guessed the number in " + attempt + " attempt(s).");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("📉 Too low! Try a higher number.");
                } else {
                    System.out.println("📈 Too high! Try a lower number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've used all attempts! The correct number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + score);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = sc.next().toLowerCase();

            playAgain = response.equals("yes");
        }

        System.out.println("\n🏁 Final Score: " + score);
        System.out.println("🎉 Thanks for playing! Goodbye!");

        sc.close();
    }
}
