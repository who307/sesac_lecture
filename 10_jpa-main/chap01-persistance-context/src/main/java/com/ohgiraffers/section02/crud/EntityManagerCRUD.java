package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;

public class EntityManagerCRUD {

    private final EntityManager entityManager;

    public EntityManagerCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Menu findMenuByMenuCode(int menuCode) {
        // find(엔티티타입, pk)
        return entityManager.find(Menu.class, menuCode);
    }

    public void saveMenu(Menu newMenu) {
        entityManager.persist(newMenu);
    }

    public Menu modifyMenuName(int menuCode, String menuName) {

        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        foundMenu.setMenuName(menuName);
        return foundMenu;
    }

    public void removeMenu(int menuCode) {
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        if (foundMenu != null) {
            entityManager.remove(foundMenu);
        }
    }
}
