package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.dto.CategoryMenuCountView;
import com.ohgiraffers.springdatajpa.menu.dto.MenuPriceRankView;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuPriceGreaterThanEqualOrderByMenuPriceDesc(int minPrice);

    @Query("SELECT m FROM Menu m " +
            "WHERE m.menuName LIKE CONCAT('%', :keyword, '%') " +
            "ORDER BY m.menuCode")
    List<Menu> searchByMenuName(@Param("keyword") String keyword);

    @Query(
            value = "SELECT m FROM Menu m JOIN FETCH m.category",
            countQuery = "SELECT COUNT(m) FROM Menu m"
    )
    Page<Menu> findPageWithCategory(Pageable pageable);

    @Query(value = """
    SELECT c.category_code AS categoryCode,
        c.category_name AS categoryName,
        COUNT(m.menu_code) AS menuCount
    FROM tbl_category c
    LEFT JOIN tbl_menu m ON m.category_code = c.category_code
    GROUP BY c.category_code, c.category_name
    ORDER BY menuCount DESC, c.category_code
        
    """, nativeQuery = true)
    // 메소드의 반환 타입을 보고, 각 행을 CategoryMenuCountView로 읽을 수 있게 준비
    //
    List<CategoryMenuCountView> findCategoryMenuCounts();

    @Query(value = """
        SELECT m.menu_code AS menuCode,
               m.menu_name AS menuName,
               m.menu_price AS menuPrice,
               -- RANK의 ORDER BY: 각 메뉴에 몇 위를 붙일지 계산하는 기준
               RANK() OVER (ORDER BY m.menu_price DESC) AS priceRank
          FROM tbl_menu m
          -- 클라이언트에 반환할 순서
         ORDER BY priceRank, m.menu_code
        """, nativeQuery = true)
    List<MenuPriceRankView> findMenuPriceRanks();

}
