package com.ohgiraffers.section03.entity;

import com.ohgiraffers.section02.crud.EntityManagerCRUD;
import com.ohgiraffers.section02.crud.EntityManagerGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityLifeCycleTests {

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private EntityLifeCycle lifeCycle;

    @BeforeEach
    void setUp() {
        entityManager = EntityManagerGenerator.getInstance();
        transaction = entityManager.getTransaction();
        transaction.begin();
        lifeCycle = new EntityLifeCycle(entityManager);
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
    @DisplayName("비영속 객체와 영속 객체 비교")
    void testTransientAndManaged() {
        // when
        Menu managedMenu = lifeCycle.findMenuByMenuCode(2);
        Menu transientMenu = new Menu(
                managedMenu.getMenuCode(),
                managedMenu.getMenuName(),
                managedMenu.getMenuPrice(),
                managedMenu.getCategoryCode(),
                managedMenu.getOrderableStatus()
        );
        // then
        assertTrue(lifeCycle.inManaged(managedMenu));
        assertFalse(lifeCycle.inManaged(transientMenu));

    }

    @Test
    @DisplayName("서로 다른 EntityManager가 조회한 엔티티 비교")
    void testManagedByDifferentEntityManagers() {
        //given
        EntityManager otherEntityManager = EntityManagerGenerator.getInstance();

        //when
        Menu menu1 = entityManager.find(Menu.class, 3);
        Menu menu2 = otherEntityManager.find(Menu.class, 3);

        assertNotSame(menu1, menu2);
    }

    @Test
    @DisplayName("같은 EntityManager에서 동일한 엔티티 조회")
    void testManagedBySameEntityManager() {
        //when
        Menu menu1 = entityManager.find(Menu.class, 3);
        Menu menu2 = entityManager.find(Menu.class, 3);

        //then
        assertSame(menu1, menu2);
    }

    @Test
    @DisplayName("detach(준영속) 후 변경 감지가 동작하지 않는지 확인")
    void testDetachEntity() {
        // given
        Menu foundMenu = entityManager.find(Menu.class, 10);
        int originalPrice = foundMenu.getMenuPrice();

        // when
        entityManager.detach(foundMenu);    // 준영속화
        foundMenu.setMenuPrice(originalPrice + 1000);
        entityManager.flush();

        //then
        Menu reloadedMenu = entityManager.find(Menu.class, 10);
        assertEquals(originalPrice, reloadedMenu.getMenuPrice());
        assertFalse(entityManager.contains(foundMenu));
    }

    @Test
    @DisplayName("merge가 반환한 새로운 영속 객체 확인")
    void testMergeEntity() {
        //given
        Menu detachedMenu = entityManager.find(Menu.class, 10);
        entityManager.detach(detachedMenu); // 준영속화
        detachedMenu.setMenuName("병합된 메뉴 이름");

        //when
        Menu mergedMenu = entityManager.merge(detachedMenu);
        // merge는 전달한 객체를 다시 관리하지 않고 관리되는 객체를 반환한다.
        // detachedMenu를 mergedMenu에 복사한다

        entityManager.flush();

        //then
        assertFalse(entityManager.contains(detachedMenu));
        assertTrue(entityManager.contains(mergedMenu));
        assertNotSame(detachedMenu, mergedMenu);
        assertEquals("병합된 메뉴 이름", mergedMenu.getMenuName());
    }

    @Test
    @DisplayName("clear 후 같은 엔티티를 다시 조회하면 새 객체가 반환되는지 확인")
    void testClearPersistenceContext() {
        //given
        Menu firstMenu = entityManager.find(Menu.class, 6);

        //when
        entityManager.clear();
        Menu secondMenu = entityManager.find(Menu.class, 6);

        //then
        assertFalse(entityManager.contains(firstMenu));
        assertNotSame(firstMenu, secondMenu);


    }

    @Test
    @DisplayName("remove로 엔티티를 삭제 상태로 전환")
    void testRemoveEntity() {
        //given
        Menu foundMenu = entityManager.find(Menu.class, 6);

        //when
        entityManager.remove(foundMenu);
        entityManager.flush();
        entityManager.clear();

        //then
        assertNull(entityManager.find(Menu.class, 6));
    }

}
