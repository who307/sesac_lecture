package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<관리할 엔티티, 엔티티 식별자의 타입>
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
