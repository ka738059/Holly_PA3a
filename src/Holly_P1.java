import java.util.Scanner;
import java.security.SecureRandom;
import java.lang.Math;

public class Holly_P1 {

    public static void generateQuestion(int rand1, int rand2, int type) {
        switch (type) {
            case 1:
                System.out.printf("How much is %d plus %d?\n", rand1, rand2);
                break;
            case 2:
                System.out.printf("How much is %d times %d?\n", rand1, rand2);
                break;
            case 3:
                System.out.printf("How much is %d minus %d?\n", rand1, rand2);
                break;
            case 4:
                System.out.printf("How much is %d divided by %d? Please round to two decimal places.\n", rand1, rand2);
                break;
        }

    }
    public static void wrongAnswer(int rand) {
        switch (rand) {
            case 1:
                System.out.println("Incorrect, try again.");
                break;
            case 2:
                System.out.println("Don't give up! Keep trying.");
                break;
            case 3:
                System.out.println("Wrong, try once more!");
                break;
            case 4:
                System.out.println("Please try again.");
                break;
        }
    }

    public static void rightAnswer(int rand) {
        switch (rand) {
            case 1:
                System.out.println("Correct! Good job.");
                break;
            case 2:
                System.out.println("Nice work!");
                break;
            case 3:
                System.out.println("Excellent.");
                break;
            case 4:
                System.out.println("Keep up the good work!");
                break;
        }
    }

    public static int randGenerator(int numDigits, SecureRandom rand) {
        switch (numDigits) {
            case 1:
                return rand.nextInt(10);
            case 2:
                return rand.nextInt(100);
            case 3:
                return rand.nextInt(1000);
            case 4:
                return rand.nextInt(10000);
        }
        return -1; //returns -1 if error occurs
    }

    public static boolean checkAnswer(double userAnswer, int rand1, int rand2, int questionType) {
        switch (questionType) {
            case 1:
                if (Double.compare((rand1 + rand2), userAnswer) == 0)
                    return true;
                return false;
            case 2:
                if (Double.compare((rand1 * rand2), userAnswer) == 0)
                    return true;
                return false;
            case 3:
                if (Double.compare((rand1 - rand2), userAnswer) == 0)
                    return true;
                return false;
            case 4:
                if ((Math.abs(userAnswer - (float)rand1 / rand2)) <= 0.01)
                    return true;
                return false;
        }
        System.out.println("Error in checkAnswer()");
        return false;
    }
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        SecureRandom rand = new SecureRandom();
        int rand1, rand2, i, incorrect = 0;
        int difficultyLevel, questionType, mixtureType;
        double answer, grade;
        char nextUser = 'c';

        while (nextUser == 'c') {
            System.out.println("Welcome to Arithmetic Practice, please enter a difficulty level (1 - 4): ");
            difficultyLevel = scnr.nextInt();

            System.out.println("Please enter a problem type:");
            System.out.println("1 for addition");
            System.out.println("2 for multiplication");
            System.out.println("3 for subtraction");
            System.out.println("4 for division");
            System.out.println("5 for random mixture");

            questionType = scnr.nextInt();

            for (i = 0; i < 10; i++) {
                rand1 = randGenerator(difficultyLevel, rand);
                rand2 = randGenerator(difficultyLevel, rand);

                if (questionType == 4 && rand2 == 0) {
                    while (rand2 == 0) {
                        rand2 = randGenerator(difficultyLevel, rand);
                    }
                }

                else if (questionType == 5) {
                    mixtureType = rand.nextInt(4) + 1;
                    if (mixtureType == 4 && rand2 == 0) {
                        while (rand2 == 0) {
                            rand2 = randGenerator(difficultyLevel, rand);
                        }
                    }

                    generateQuestion(rand1, rand2, mixtureType);
                    answer = scnr.nextFloat();

                    if (checkAnswer(answer, rand1, rand2, mixtureType) == false) {
                        wrongAnswer(rand.nextInt(4) + 1);
                        incorrect++;
                        continue;
                    }
                }
                else {
                    generateQuestion(rand1, rand2, questionType);
                    answer = scnr.nextFloat();
                    if (checkAnswer(answer, rand1, rand2, questionType) == false) {
                        wrongAnswer(rand.nextInt(4) + 1);
                        incorrect++;
                        continue;
                    }
                }
                rightAnswer(rand.nextInt(4) + 1);
            }

            System.out.println("Number Correct: " + (10 - incorrect));
            System.out.println("Number Incorrect: " + incorrect);
            grade = ((float) (10 - incorrect) / 10) * 100;
            System.out.println("Grade: " + grade);

            if (grade < 75)
                System.out.println("Please ask your teacher for extra help.");
            else
                System.out.println("Congratulations, you are ready to go to the next level!");

            System.out.println("Enter 'c' to continue to the next user or 'q' to quit.");
            nextUser = scnr.next().charAt(0);
        }
    }
}
