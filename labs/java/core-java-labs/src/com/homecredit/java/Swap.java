package com.homecredit.java;

public class Swap {

    public static void main(String[] args) {

        int firstNumber = 2, secondNumber =5;

        System.out.println("Before Swapping ");

        System.out.println("First Number : " + firstNumber);
        System.out.println("Second Number : " + secondNumber);

        System.out.println();

        int temp = firstNumber;
        firstNumber =secondNumber;
        secondNumber = temp;

        System.out.println("After Swapping ");

        System.out.println("First Number : " + firstNumber);
        System.out.println("Second Number : " + secondNumber);


    }


}
