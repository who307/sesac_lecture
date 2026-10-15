package com.who307.section02.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Application2 {
    public static void main(String[] args) {
        /*
        * [LinkedHashset]
        * - '중복 불가'의 특징은 그대로 유지
        * - 요소가 입력된 순서를 기억하여, 순서를 유지하는 특징이 있다.
        * */

        Set<String> hset = new HashSet<>();
        hset.add("java");
        hset.add("html");
        hset.add("css");
        hset.add("java");

        Set<String> lhset = new LinkedHashSet<>();
        lhset.add("java");
        lhset.add("html");
        lhset.add("css");
        lhset.add("java");  // 중복은 여전히 불가

        System.out.println("hashset : " +  hset);
        System.out.println("linkedHashset : " + lhset);
    }
}
