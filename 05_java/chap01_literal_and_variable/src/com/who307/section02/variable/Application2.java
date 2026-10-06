package com.woong3e.section02.variable;

public class Application2 {
    public static void main(String[] args) {

        // 변수의 명명 규칙

        // 1. 컴파일 에러 발생하는
        // 동일한 범위 내에서 동일한 변수명을 가질 수 없다.
        int age = 20;
        // int age = 30;   // java: variable age is already defined in method main(java.lang.String[])

        // 예약어는 변수명으로 사용 불가하다.
        // int true = 3;   // java: not a statement

        // 대소문자를 구분한다.
        int kevin = 20;
        int Kevin = 20;
        int True = 30;  // 예약어 아니어서 사용은 가능하다.

        // 숫자로 시작할 수 없다.
        // int 1age = 20;  // java: not a statement
        int age1 = 20;

        // 특수기호는 '_' 와 '$' 만 사용가능하다.
        // int sh@rp = 10; // java: illegal start of type
        int _age = 10;
        int $age = 20;

        // 2. 에러를 발생시키지는 않지만 암묵적 규칙
        int askdnfaknsdflknalksdfnlkansdfnlasdf; // 길이 제한은 없지만 이렇게 하지 말자.

        // 합성어로 이루어진 경우 첫 단어는 소문자, 두 번째 시작 단어는 대문자로 시작한다. (카멜케이스)
        int maxAge = 20;
        int max_age = 20;   // 단어와 단어 사이의 연결을 언더바로 하지 않는다.

        int 나이; // 한글사용은 지양

        // 변수 안에 저장된 값이 어떤 의미를 가지는지 명확하게 표현하도록 한다.
        String s;
        String name;

        // 전형적 변수 이름이 있다면 가급적 사용한다.
        int sum = 0;
        int max = 20;
        int min = 10;
        int count = 1;

        // boolean 은 의문문으로 가급적 긍정 형태로 이름 짓는다.
        boolean isAlive = true;
    }
}
