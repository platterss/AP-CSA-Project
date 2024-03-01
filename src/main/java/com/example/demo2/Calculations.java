package com.example.demo2;

import java.util.ArrayList;

public class Calculations {
    static ArrayList<Integer> numbers = new ArrayList<Integer>();
    public static void createNums() {
        int questionNum = (int) (Math.random() * 4 + 1);
        for (int i = 0; i < questionNum; i++) {
            numbers.add((int) (Math.random() * 100 + 1));
        }
    }

    /*public static void main(String[] args) {
        createNums();
        System.out.println(numbers);
    }*/
}

class division extends Calculations {

}

class addition extends Calculations {

}

class subtraction extends Calculations {

}
