package com.example.apcsaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Expression {
    private final int number1;
    private final int number2;
    private char operation;
    private int answer;
    private static final Random rand = new Random();

    public Expression() {
        number1 = rand.nextInt(100) + 1;
        number2 = rand.nextInt(100) + 1;

        switch (rand.nextInt(3)) {
            case 0:
                operation = '+';
                answer = number1 + number2;
                break;
            case 1:
                operation = '-';
                answer = number1 - number2;
                break;
            case 2:
                operation = '*';
                answer = number1 * number2;
                break;
        }
    }

    public String getExpression() {
        return number1 + " " + operation + " " + number2;
    }

    public int getAnswer() {
        return answer;
    }

    public List<Integer> getAnswerCombo() {
        List<Integer> answers = new ArrayList<>();
        answers.add(answer);

        int wrongAnswer1, wrongAnswer2;

        wrongAnswer1 = generateWrongAnswer(answer);
        do {
            wrongAnswer2 = generateWrongAnswer(answer);
        } while (wrongAnswer2 == wrongAnswer1);

        answers.add(wrongAnswer1);
        answers.add(wrongAnswer2);

        java.util.Collections.shuffle(answers);

        return answers;
    }

    private int generateWrongAnswer(int correctAnswer) {
        int delta = rand.nextInt(15) + 1; // Ensure at least some difference
        boolean add = rand.nextBoolean();

        // Generate a wrong answer that's different from the correct answer
        int wrongAnswer = add ? correctAnswer + delta : correctAnswer - delta;

        // Ensure the wrong answer is non-negative
        if (wrongAnswer < 0) {
            wrongAnswer = correctAnswer + delta; // Adjust if subtraction made it negative
        }

        return wrongAnswer;
    }
}
