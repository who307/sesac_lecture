package com.who307.section02.enumtype;

public class Application {
    public static void main(String[] args) {

        Subjects subject1 = Subjects.JAVA;
        Subjects subject2 = Subjects.HTML;

        if (subject1 == subject2) {
            System.out.println("두 과목은 같은과목이다.");
        } else {
            System.out.println("두 과목은 다른과목이다.");
        }
//        printSubject(2);  // Subjects 타입이 아니므로 전달 불가
        printSubject(Subjects.HTML);

        // 선언된 상수 순회
        for (Subjects s : Subjects.values()) {
            // s.ordinal(): 현재 상수의 선언위치를 0부터 반환
            // s.name(): 같은 상수의 소스 이름을 문자열로 반환
            System.out.println((s.ordinal() + 1) + ". " +s.name());
        }


    }

    public static void printSubject(Subjects subjects) {
        System.out.println("전달받은 과목 : " + subjects.toString());
    }
}
