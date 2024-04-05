package com.example.apcsaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Expression {
    private int number1;
    private int number2;
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

        do {
            wrongAnswer1 = answer + rand.nextInt(20) - 10;
        } while (wrongAnswer1 == answer || wrongAnswer1 < 0);

        do {
            wrongAnswer2 = answer + rand.nextInt(20) - 10;
        } while (wrongAnswer2 == answer || wrongAnswer2 == wrongAnswer1 || wrongAnswer2 < 0);

        answers.add(wrongAnswer1);
        answers.add(wrongAnswer2);

        java.util.Collections.shuffle(answers);

        return answers;
    }
}
