package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.dto.MenuRequest;
import com.ohgiraffers.springdatajpa.menu.dto.MenuResponse;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuCode}")
    public ResponseEntity<MenuResponse> findMenu(@PathVariable int menuCode) {
        return ResponseEntity.ok(menuService.findMenu(menuCode));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> findMenus() {
        return ResponseEntity.ok(menuService.findMenus());
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@Valid @RequestBody MenuRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(menuService.createMenu(request));
    }

    @PutMapping("/{menuCode}")
    public ResponseEntity<MenuResponse> updateMenu(
            @PathVariable int menuCode,
            @Valid @RequestBody MenuRequest request
    ) {
        return ResponseEntity.ok(menuService.updateMenu(menuCode, request));
    }

    @DeleteMapping("/{menuCode}")
    public ResponseEntity<Void> deleteMenu(@PathVariable int menuCode) {
        menuService.deleteMenu(menuCode);
        return ResponseEntity.noContent().build();
    }
}
