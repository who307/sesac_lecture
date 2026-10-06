package com.woong3e.section01.literal;

public class Application1 {
    public static void main(String[] args) {
        // 숫자 형태의 값
        // 정수 형태의 값 출력
        System.out.println(123);

        // 실수 형태의 값 출력
        System.out.println(1.23);

        // 문자 형태의 값
        System.out.println('a');
        // System.out.println('abc'); 문자열일 때 쌍따옴표 안쓰면 java: unclosed character literal 에러
        // System.out.println(''); 공백일 때 에러 java: empty character literal

        // 문자열 형태의 값
        System.out.println("abc");
        System.out.println("");
        System.out.println("a");

        // 논리 형태의 값
        System.out.println(true);
        System.out.println(false);

    }
}
