package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    // 쿼리메소드: 정해진 규칙에 맞는 메소드 이름을 해석해 쿼리 생성
    /* 20,000원 이상의 메뉴를 비싼 순서로 보여 주세요 */
    List<Menu> findByMenuPriceGreaterThanEqualOrderByMenuPriceDesc(int minPrice);

    // JPQL: 테이블과 컬럼이 아니라, 엔티티와 필드를 대상으로 조회하는 JPA 쿼리 언어
    // 검색어는 :keyword에 바인딩하고 앞뒤에 %를 붙여 검색한다
    @Query("SELECT m FROM Menu m WHERE m.menuName LIKE CONCAT('%', :keyword, '%') ORDER BY m.menuCode")
    List<Menu> searchByMenuName(@Param("keyword") String keyword);

    // JOIN FETCH m.category 메뉴와 연결된 카테고리를 조인, 연관객체를 함께 로디하겠다는 목적까지 포함.
    @Query(value="SELECT m FROM Menu m JOIN FETCH m.category",
        countQuery="SELECT COUNT(m) FROM Menu m")
    Page<Menu> findPageWithCategory(Pageable pageable);
}
