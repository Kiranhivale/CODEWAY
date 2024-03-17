//NUMBERGAME

import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            int lowerBound = 1;
            int upperBound = 100;
            int targetNumber;
            int maxAttempts = 5; // Set the maximum number of attempts
            int numberOfTries = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");

            // Difficulty Levels
            System.out.println("Choose a difficulty level:");
            System.out.println("1. Easy (1-50)");
            System.out.println("2. Medium (1-100)");
            System.out.println("3. Hard (1-200)");
            int difficultyChoice = scanner.nextInt();

            switch (difficultyChoice) {
                case 1:
                    upperBound = 50;
                    break;
                case 2:
                    // No change to upperBound, already 1-100
                    break;
                case 3:
                    upperBound = 200;
                    break;
                default:
                    System.out.println("Invalid choice. Setting to medium difficulty.");
            }

            targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            System.out.println("I have selected a number between " + lowerBound + " and " + upperBound + ". Try to guess it.");

            while (!hasGuessedCorrectly && numberOfTries < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfTries++;

                if (userGuess == targetNumber) {
                    hasGuessedCorrectly = true;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again!");
                } else {
                    System.out.println("Too high. Try again!");
                }
            }

            // Hints
            System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
            if (targetNumber % 2 == 0) {
                System.out.println("Hint: The target number is even.");
            } else {
                System.out.println("Hint: The target number is odd.");
            }

            // Score System
            int score = calculateScore(numberOfTries, difficultyChoice);
            System.out.println("Your score: " + score);

            scanner.close();
        }
    }

    // Score calculation method (you can adjust the formula as needed)
    private static int calculateScore(int numberOfTries, int difficultyChoice) {
        int baseScore = 1000;
        int difficultyMultiplier;

        switch (difficultyChoice) {
            case 1:
                difficultyMultiplier = 3;
                break;
            case 2:
                difficultyMultiplier = 2;
                break;
            case 3:
                difficultyMultiplier = 1;
                break;
            default:
                difficultyMultiplier = 2; // Default to medium difficulty
        }

        // Penalty for exceeding max attempts
        int penalty = numberOfTries > 10 ? (numberOfTries - 10) * 50 : 0;

        return Math.max(0, baseScore / (numberOfTries * difficultyMultiplier) - penalty);
    }
}
