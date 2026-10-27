package com.ohgiraffers.chap00springbootintroduction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* 이 클래스가 웹 요청을 받는 Controller 이며,
* 메소드의 반환값을 HTTP 응답 본문으로 전달한다는 표시이다 */
public class HelloController {

    // GET 방식의 /hello 요청이 들어오면 hello 메소드를 실행하라는 연결
    @GetMapping("/hello")
    public String hello(){
        return "Spring Boot Hello";
    }
}
