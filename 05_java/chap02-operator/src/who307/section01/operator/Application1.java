package who307.section01.operator;

public class Applicaion1 {
    public static void main(String[] args) {
        /* 산술연산자(+, -, *, /, %)
        * 정수끼리 나눈 결과는 소수 부분을 버린다 */
        System.out.println("10 / 3 = " + (10 / 3));

        /* 산술 복합 대입 연산자(+=, -=, *=, /=, %=) */

        int num = 12;
        num +=3; // num = num + 3;
        System.out.println("num = " + num);
        /* 증감 연산자 (++, --) */

        num++; // 현재 값을 먼저 사용한 뒤 1 증가
        ++num; // 먼저 1 증가한 뒤 증가한 값 사용

        int firstNum = 10;

        int result = ++firstNum * 3;
        System.out.println("result = " + result);

        /* 비교연산자(==, !=, >, <, >=, <=) */

        int num1 = 10;
        int num2 = 20;

        System.out.println(num1 == num2);
        System.out.println(num1 != num2);

        /* 문자열 비교 */
        String str1 = "java";
        String str2 = "java";

        System.out.println(str1 == str2);

        // 문자열의 내용이 같은지 비교할때는 equals()를 사용
        System.out.println(str1.equals(str2));

        /* bollean 은 == 또는 !=로 값이 같은지 비교할 수 있다. */
        boolean bool1 = true;
        boolean bool2 = false;
        System.out.println(bool1 == bool2);

    }
}
