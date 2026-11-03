package com.ohgiraffers.springdatajpa.menu.repository;

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
}
