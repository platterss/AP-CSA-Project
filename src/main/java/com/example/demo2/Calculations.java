package com.example.demo2;

import java.util.ArrayList;

public class Calculations {
    protected ArrayList<Integer> numbers;

    public Calculations() {
        numbers = new ArrayList<Integer>();
        createNums();
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void createNums() {
        int questionNum = (int) (Math.random() * 4 + 2);
        for (int i = 0; i < questionNum; i++) {
            numbers.add((int) (Math.random() * 100 + 1));
        }
    }

    public String getExpression(String operation) {
        String expression = "";
        for (int i = 0; i < numbers.size(); i++) {
            expression = expression.concat(String.valueOf(numbers.get(i)));

            if (i < numbers.size() - 1) {
                expression = expression.concat(" " + operation + " ");
            }
        }
        return expression;
    }
}

class Addition extends Calculations {
    public Addition() {
        super();
    }

    public int getSum() {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        return sum;
    }

    public String getExpression() {
        return super.getExpression("+");
    }
}

class Subtraction extends Calculations {
    public Subtraction() {
        super();
    }

    public int getDifference() {
        int difference = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            difference -= numbers.get(i);
        }
        return difference;
    }

    public String getExpression() {
        return super.getExpression("-");
    }
}

class Multiplication extends Calculations {
    public Multiplication() {
        super();
    }

    public int getProduct() {
        int product = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            product *= numbers.get(i);
        }
        return product;
    }

    public String getExpression() {
        return super.getExpression("*");
    }
}