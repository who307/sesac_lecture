package com.ohgiraffers.associationmapping.section03.bidirection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class BiDirectionServiceTests {

    @Autowired
    private BiDirectionService biDirectionService;

    @Test
    @DisplayName("연관관계의 주인인 메뉴에서 카테고리 조회")
    void biDirectionFindTest1() {
        // given
        int menuCode = 10;

        // when
        Menu foundMenu = biDirectionService.findMenu(menuCode);

        // then
        assertEquals(menuCode, foundMenu.getMenuCode());
        assertNotNull(foundMenu.getCategory());
    }

    @Test
    @DisplayName("주인이 아닌 카테고리에서 메뉴 목록 조회")
    void biDirectionFindTest2() {
        // given
        int categoryCode = 4;

        // when
        Category foundCategory = biDirectionService.findCategory(categoryCode);
        var menuList = foundCategory.getMenuList();

        // then
        assertEquals(categoryCode, foundCategory.getCategoryCode());
        assertFalse(menuList.isEmpty());
        assertSame(foundCategory, menuList.get(0).getCategory());
    }

    @Test
    @DisplayName("연관관계 편의 메소드로 객체 양쪽을 함께 연결")
    void addMenuTest() {
        // given
        Category category = new Category(9002, "퓨전", null);
        Menu menu = new Menu(9002, "돈까스 스파게티", 15000, null, "Y");

        // when
        category.addMenu(menu);

        // then
        assertTrue(category.getMenuList().contains(menu));
        assertSame(category, menu.getCategory());
    }
}
