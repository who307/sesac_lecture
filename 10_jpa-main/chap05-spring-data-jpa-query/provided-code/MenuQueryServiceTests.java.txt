package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.MenuResponse;
import com.ohgiraffers.springdatajpa.menu.dto.PageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class MenuQueryServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    @DisplayName("쿼리 메소드로 최소 가격 이상의 메뉴 조회")
    void findMenusByMinimumPriceTest() {
        List<MenuResponse> menus = menuService.findMenusByMinimumPrice(20000);

        assertFalse(menus.isEmpty());
        assertTrue(menus.stream().allMatch(menu -> menu.getMenuPrice() >= 20000));
    }

    @Test
    @DisplayName("JPQL로 이름에 검색어가 포함된 메뉴 조회")
    void searchMenusByNameTest() {
        List<MenuResponse> menus = menuService.searchMenusByName("마늘");

        assertFalse(menus.isEmpty());
        assertTrue(menus.stream().allMatch(menu -> menu.getMenuName().contains("마늘")));
    }

    @Test
    @DisplayName("fetch join과 페이징으로 메뉴와 카테고리 조회")
    void findMenuPageTest() {
        PageRequest pageable = PageRequest.of(
                0,
                5,
                Sort.by("menuCode").ascending()
        );

        PageResponse<MenuResponse> page = menuService.findMenuPage(pageable);

        assertFalse(page.getContent().isEmpty());
        assertTrue(page.getContent().size() <= 5);
        assertTrue(page.getTotalElements() > 0);
        assertTrue(page.getContent().stream()
                .allMatch(menu -> menu.getCategoryName() != null));
    }
}
