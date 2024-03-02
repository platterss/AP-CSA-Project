package com.example.demo2;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Calculation {
    protected ArrayList<Integer> numbers;

    public Calculation() {
        numbers = new ArrayList<>();
        generateNumbers();
    }

    public ArrayList<Integer> getAnswerCombo() {
        int correct = getResult();
        int wrong1 = correct;
        int wrong2 = correct;

        while (wrong1 == correct || wrong2 == correct || wrong1 == wrong2) {
            wrong1 = correct + ((int) (Math.random() + 1) * 75);
            wrong2 = correct - ((int) (Math.random() + 1) * 75);
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(correct);
        list.add(wrong1);
        list.add(wrong2);

        Collections.shuffle(list);
        return list;
    }

    private void generateNumbers() {
        int questionNum = (int) (Math.random() * 4 + 2);
        for (int i = 0; i < questionNum; i++) {
            numbers.add((int) (Math.random() * 100 + 1));
        }
    }

    abstract String getExpression();

    abstract int getResult();

}

class Addition extends Calculation {
    @Override
    public String getExpression() {
        String expression = "";
        for (int i = 0; i < numbers.size(); i++) {
            expression = expression.concat(String.valueOf(numbers.get(i)));

            if (i < numbers.size() - 1) {
                expression = expression.concat(" + ");
            }
        }
        return expression;
    }

    @Override
    public int getResult() {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        return sum;
    }
}

class Subtraction extends Calculation {
    @Override
    public String getExpression() {
        String expression = "";
        for (int i = 0; i < numbers.size(); i++) {
            expression = expression.concat(String.valueOf(numbers.get(i)));

            if (i < numbers.size() - 1) {
                expression = expression.concat(" - ");
            }
        }
        return expression;
    }

    @Override
    public int getResult() {
        int difference = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            difference -= numbers.get(i);
        }
        return difference;
    }
}

class Multiplication extends Calculation {
    public String getExpression() {
        String expression = "";
        for (int i = 0; i < numbers.size(); i++) {
            expression = expression.concat(String.valueOf(numbers.get(i)));

            if (i < numbers.size() - 1) {
                expression = expression.concat(" * ");
            }
        }
        return expression;
    }

    public int getResult() {
        int product = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            product *= numbers.get(i);
        }
        return product;
    }
}