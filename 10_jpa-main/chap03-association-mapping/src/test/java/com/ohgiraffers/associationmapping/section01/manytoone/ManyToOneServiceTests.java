package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class ManyToOneServiceTests {

    @Autowired
    private ManyToOneService manyToOneService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("N:1 연관관계로 메뉴에서 카테고리 조회")
    void manyToOneFindTest() {
        // given
        int menuCode = 10;

        // when
        Menu foundMenu = manyToOneService.findMenu(menuCode);
        Category category = foundMenu.getCategory();

        // then
        assertNotNull(foundMenu);
        assertNotNull(category.getCategoryName());
    }

    @Test
    @DisplayName("기존 카테고리와 연관관계를 맺어 새 메뉴 저장")
    void manyToOneInsertTest() {
        // given
        MenuDTO menuInfo = new MenuDTO(
                9001,
                "돈까스 스파게티",
                15000,
                4,
                "Y"
        );

        // when
        Menu savedMenu = manyToOneService.registMenu(menuInfo);
        entityManager.flush();

        // then
        assertEquals(9001, savedMenu.getMenuCode());
        assertEquals(4, savedMenu.getCategory().getCategoryCode());
    }
}
