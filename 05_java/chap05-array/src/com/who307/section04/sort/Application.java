package com.who307.section04.sort;

public class Application {
    public static void main(String[] args) {

        int num1 = 10;
        int num2 = 20;

        int temp;
        temp = num1;
        num1 = num2;
        num2 = temp;

        System.out.println("num1 : " + num1 + " num2 : " + num2);

        int[] arr = {1, 2, 3};

        int temp2 = arr[0];
        arr[0] = arr[1];
        arr[1] = temp2;
    }
}
