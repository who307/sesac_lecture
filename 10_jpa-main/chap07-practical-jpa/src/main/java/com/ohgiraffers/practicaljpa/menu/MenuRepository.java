package com.ohgiraffers.practicaljpa.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// TODO 04: 조건 검색을 실행할 저장소 기능을 추가한다.
public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {
}
