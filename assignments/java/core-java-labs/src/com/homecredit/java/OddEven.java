package com.homecredit.java;

public class OddEven {
    public static void main(String[] args) {


        int firstNummber = 12, secondNumber =11;

       oddEven(firstNummber);
       oddEven(secondNumber);

    }
    public static void oddEven(int number){

        if(number%2==0){
            System.out.println(number + " is Even");
        }else{
            System.out.println(number + " is Odd");
        }

    }
}
