package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;

public class EntityLifeCycle {

    /*
    * 비영속 - 만들어졌지만 아직 JPA가 관리하지 않는 객체
    * 영속 - 현재 영속성 컨텍스트가 관리하는 객체
    * 준영속 - 한때 관리됐지만 현재는 분리된 객체
    * 삭제 - 영속성 컨텍스트에서 삭제 대상으로 지정된 객체
    * */

    private final EntityManager entityManager;

    public EntityLifeCycle(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Menu findMenuByMenuCode(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    public boolean inManaged(Menu menu) {
        // 엔티티매니저가 이 객체를 관리하는지 여부 반환
        return entityManager.contains(menu);
    }
}
