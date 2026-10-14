package com.who307.section01.generic;

public class Application {
    public static void main(String[] args) {

        /*
        * [제네릭]
        * 클래스나 메소드에서 사용할 자료형을 미리 고정하지 않고
        * 사용하는 시점에 결정하는 문법
        *
        * 타입별 중복 코드를 줄이고
        * 잘못된 타입의 데이터가 들어오는 것을 방지한다.*/

        NormalBox normalBox = new NormalBox();

        normalBox.setContent("안녕하세요");
        String content = (String)normalBox.getContent();

//        normalBox.setContent(123);
        // Object에는 숫자도 저장 할 수 있어 자체 컴파일 오류가 발생하지 않는다.
        // 하지만 런타임 시점에 숫자를 String으로 바꾸려는 순간 ClassCaseException이 발생한다.
//        String wrongContent = (String)normalBox.getContent();

        GenericBox<String> stringBox = new GenericBox<String>();

        stringBox.setContent("안녕");
//        stringBox.setContent(123);
        String strContent = stringBox.getContent();

        GenericBox<Integer> integerBox = new GenericBox<>();

        integerBox.setContent(123); // 오토박싱
        int intContent = integerBox.getContent(); // 오토언박싱
        System.out.println(intContent + 1);
    }


}
