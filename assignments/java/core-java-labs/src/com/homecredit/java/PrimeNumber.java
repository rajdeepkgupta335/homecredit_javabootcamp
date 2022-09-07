package com.homecredit.java;

public class PrimeNumber {
    public static void main(String[] args) {

        int a =15, b= 23;

        System.out.println(isPrime(a));
        System.out.println(isPrime(b));

        AllPrime(50);


    }
    public static boolean isPrime(int number){

        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }

        return true;
    }
    public static void AllPrime(int number){

        for(int i=1;i<=number;i++){
            if(isPrime(i)){
                System.out.print(i + " ");
            }
        }

    }
}
;