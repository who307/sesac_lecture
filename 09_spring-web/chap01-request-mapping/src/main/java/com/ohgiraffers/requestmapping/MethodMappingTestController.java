package com.ohgiraffers.requestmapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/menus")
public class MethodMappingTestController {

    // HTTP 요청과 연결되어 실제로 실행되는 Controller 메소드를 핸들러 메소드라고 부른다
    @GetMapping
    public ResponseEntity<Map<String, String>> findMenus() {
        Map<String, String> response
                = Map.of("message", "메뉴 목록 조회용 핸들러 메소드 호출");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{menuCode}")
    public ResponseEntity<Map<String, Object>> findMenu(
            @PathVariable int menuCode) {
        Map<String, Object> response = Map.of(
                "message", menuCode + "번 메뉴 조회용 핸들러 메소드 호출");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registMenu() {
        Map<String, Object> response = Map.of(
                "message", "신규 메뉴 등록용 핸들러 메소드 호출");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{menuCode}")
    public ResponseEntity<Map<String, String>> modifyMenu(
            @PathVariable int menuCode) {
        Map<String, String> response = Map.of("message", menuCode + "번 메뉴 수정용 핸들러 메소드 호출");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{menuCode}")
    public ResponseEntity<Map<String, String>> deleteMenu(
            @PathVariable int menuCode) {
        Map<String, String> response = Map.of("message", menuCode + "번 메뉴 삭제용 핸들러 메소드 호출");
        return ResponseEntity.ok(response);
    }

}

