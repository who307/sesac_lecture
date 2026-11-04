package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.MenuRequest;
import com.ohgiraffers.springdatajpa.menu.dto.MenuResponse;
import com.ohgiraffers.springdatajpa.menu.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Spring Data JPA로 메뉴 CRUD 수행")
    void menuCrudTest() {
        MenuResponse created = menuService.createMenu(
                new MenuRequest("돈까스 스파게티", 15000, 4, "Y")
        );
        assertTrue(created.getMenuCode() > 0);

        MenuResponse found = menuService.findMenu(created.getMenuCode());
        assertEquals("돈까스 스파게티", found.getMenuName());

        MenuResponse updated = menuService.updateMenu(
                created.getMenuCode(),
                new MenuRequest("치즈 돈까스 스파게티", 17000, 4, "Y")
        );
        entityManager.flush();
        assertEquals(17000, updated.getMenuPrice());

        menuService.deleteMenu(created.getMenuCode());
        entityManager.flush();
        entityManager.clear();

        assertThrows(
                ResourceNotFoundException.class,
                () -> menuService.findMenu(created.getMenuCode())
        );
    }
}
