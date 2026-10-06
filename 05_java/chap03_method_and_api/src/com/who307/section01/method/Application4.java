package com.woong3e.section01.method;

public class Application4 {
    public static void main(String[] args) {

        int first = 100;
        int second = 50;

        // non-static 메소드의 경우
        // 클래스명 변수명 = new 클래스명();
        // 변수명.메소드명();

        Calculator cal = new Calculator();
        int min = cal.minNumberOf(first, second);
        System.out.println("min = " + min);

        // static 메서드의 경우
        // 다른 클래스에 작성한 경우 클래스명을 반드시 기술
        // 클래스명.메서드명();
        int max = Calculator.maxNumberOf(first, second);
        System.out.println("max = " + max);

        // 인스턴스 객체로도 접근할 수 있지만 권장되지는 않는다.
        int max2 = cal.maxNumberOf(1,20);
        System.out.println("max2 = " + max2);

    }
}
