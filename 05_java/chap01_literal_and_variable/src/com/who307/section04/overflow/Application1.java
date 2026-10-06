package com.woong3e.section04.overflow;

public class Application1 {
    public static void main(String[] args) {

        byte num = 127;     // byte 의 저장범위 -128 ~ 127
        num++;              // 저장범위를 넘어가면
        System.out.println(num);    // -128, 오버플로우, 자료형이 저장할 수 있는 최대 범위를 넘어가는 현상

        byte num2 = -128;
        num2--;
        System.out.println(num2);   // 127, 언더플로우, 자료형이 저장할 수 있는 최소 범위를 넘어가는 현상

        int firstNum = 1000000;
        int secondNum = 700000;

        int multi = firstNum * secondNum;
        System.out.println("multi = " + multi);

        // 이미 오버플로우된 값이 담겨서 값이 같음.
        long longNum = firstNum * secondNum;
        System.out.println("longNum = " + longNum);

        // 피연산자 하나를 미리 long 으로 변환하면 전체 계산이 long 으로 처리된다.
        long result = (long) firstNum * secondNum;
        System.out.println("result = " + result);

    }
}
