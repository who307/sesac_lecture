package com.woong3e.section02.package_and_import;

import com.woong3e.section01.method.Calculator;

public class Application1 {
    public static void main(String[] args) {
        // non-static 메서드
        Calculator cal = new Calculator();
        int min = cal.minNumberOf(10, 20);
        System.out.println("min = " + min);

        // static 메서드
        int max = Calculator.maxNumberOf(10, 20);
        System.out.println("max = " + max);
        // static 메서드도 import 해서 사용할 수 있지만, 선택적으로 사용하는게 좋다.
        // 짧고 간결하게 사용할 수 있지만 어디서 온 메서드인지 덜 명확해보일 수 있다.
    }
}
