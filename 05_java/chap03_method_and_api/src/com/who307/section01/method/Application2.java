package com.who307.section01.method;

public class Application2 {
    public static void main(String[] args) {
    Application2 app2 = new Application2();
        app2.printAge(3);

        int myAge = 10;
        app2.printAge(myAge);

        app2.printUserInfo("박지성", 30, '남');

        // void 가 아닌 반환 타입을 작성했다면 그 타입에 맞는 값을 반드시 반환해야 한다.
        // 반환된 값은 변수에 저장하거나 다른 메소드의 전달인자로 즉시 사용할 수 있다.

        String message = app2.createMessage();
        System.out.println("message = " + message);


        String message2 = app2.createString("박지성", myAge);
        System.out.println("message2 = " + message2);

    }
    public void printAge(int age){

        /* 전달인자(argument)와 매개변수(parameter)
        * 메소드를 호출할 때 넘겨주는 값을 '전달인자' 라고 하며
        * 메소드에서 이 값을 받기 위해 선언된 변수를 '매개변수' 라고 한다.*/
        System.out.println("당신의 나이는 " + age + "세 입니다.");
    }

    public void printUserInfo(String name, int age, char gender) {
        System.out.println("이름: " + name + " 나이: " + age + " 성별: " + gender);

        /* void : 반환타입, 반환값이 없음을 뜻한다. 마지막 줄에 return 생략 가능
        * */
    }
    // String 타입을 반환
    public String createMessage(){
        return "Hello";
    }
    public String createString(String name, int age){
        String profile = name + "님의 나이는 " + age + "세 입니다.";
        return profile;
    }

}
