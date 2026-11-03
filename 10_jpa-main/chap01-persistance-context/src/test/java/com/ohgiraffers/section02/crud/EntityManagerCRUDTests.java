package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerCRUDTests {

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private EntityManagerCRUD crud;

    @BeforeEach
    void setUp() {
        entityManager = EntityManagerGenerator.getInstance();
        transaction = entityManager.getTransaction();
        transaction.begin();
        crud = new EntityManagerCRUD(entityManager);
    }

    @AfterEach
    void tearDown() {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    @DisplayName("메뉴 코드로 메뉴 조회")
    void testFindMenuByMenuCode() {
        //when
        Menu foundMenu = crud.findMenuByMenuCode(1);

        //then
        assertNotNull(foundMenu);
        assertEquals(1, foundMenu.getMenuCode());
        System.out.println("foundMenu : " + foundMenu);
    }

    @Test
    @DisplayName("새로운 메뉴 저장")
    void testSaveMenu() {
        //given
        Menu newMenu = new Menu("신메뉴", 35000, 4, "Y");

        // when
        crud.saveMenu(newMenu);
        entityManager.flush();  // 영속성 컨텍스트의 변경 내용을 SQL로 DB에 동기화하라고 요청
        int newMenuCode = newMenu.getMenuCode();
        entityManager.clear();  // 영속성 컨텍스트 비우기

        // then
        Menu savedMenu = crud.findMenuByMenuCode(newMenuCode);
        assertNotNull(savedMenu);
        assertEquals("신메뉴", savedMenu.getMenuName());
    }

    @Test
    @DisplayName("영속 상태 메뉴의 이름 변경")
    void testModifyMenuName() {
        // when
        crud.modifyMenuName(1, "변경된 이름");
        entityManager.flush();
        entityManager.clear();

        // then
        Menu modifiedMenu = crud.findMenuByMenuCode(1);
        assertEquals("변경된 이름", modifiedMenu.getMenuName());
    }

    @Test
    @DisplayName("메뉴 삭제")
    void testRemoveMenu() {
        //when
        crud.removeMenu(1);
        entityManager.flush();
        entityManager.clear();

        //then
        assertNull(crud.findMenuByMenuCode(1));
    }


}
