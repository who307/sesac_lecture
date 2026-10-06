package com.woong3e.section01.operator;

public class Application2 {
    public static void main(String[] args) {

        // 1. 논리연결연산자
        // && 연산자 : 두 개의 논리식 모두 참 일 경우 참
        // || 연산자 : 두 개의 논리식 중 하나라도 참 일 경우 참

        // 2. 논리부정연산자
        // ! 연산자 : 논리식의 결과가 참이면 거짓을, 거짓이면 참을 반환한다.

        // 자바의 논리 연산에는 boolean 값만 사용할 수 있다.
        // JS처럼 숫자, 문자열 객체를 truthy 또는 falsy 값으로 판단하지 않는다.
        System.out.println(true && true);
        System.out.println(true && false);
        System.out.println(true || true);
        System.out.println(false || false);
        System.out.println(!false);

        int num1 = 55;
        System.out.println(num1 >= 1 && num1 <= 100);

        /*
        * 단축평가
        * && : 앞의 결과가 false이면 뒤를 실행 안함
        * || : 앞의 결과가 true이면 뒤를 실행 안함
        *
        * 자바의 && 와 || 는 boolean 끼리 연산하고 결과도 boolean 으로 반환한다.
        */

        int num2 = 10;
        boolean result1 = false && ++num2 > 0;
        System.out.println("result1 = " + result1);
        System.out.println(num2);  // ++num2 > 0 은 실행되지 않았다.


        /*
          삼항연산자
        * (조건식)? 참일 때 사용할 값1 : 거짓일 때 사용할 값2
        * */
        int num3 = 10;
        String result3 = (num3 > 0) ? "양수다" : "양수가 아니다";
        System.out.println("result3 = " + result3);
        

    }
}
