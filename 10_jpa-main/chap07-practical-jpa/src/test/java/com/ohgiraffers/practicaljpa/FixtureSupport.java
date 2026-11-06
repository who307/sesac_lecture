package com.ohgiraffers.practicaljpa;

import com.ohgiraffers.practicaljpa.menu.*;
import com.ohgiraffers.practicaljpa.order.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
abstract class FixtureSupport {
    @Autowired protected OrderService orders;
    @Autowired protected OrderRepository orderRepository;
    @Autowired protected MenuService menus;
    @Autowired protected MenuRepository menuRepository;
    @Autowired protected EntityManagerFactory factory;

    // 클래스에 @Transactional을 붙이지 않는다.
    // 각 서비스 프록시가 실제로 커밋/롤백한 결과를 새 조회로 검증한다.
    @Autowired protected CategoryRepository categoryRepository;
    protected String prefix;
    protected Category category;
    protected java.util.List<Integer> fixtureCodes = new java.util.ArrayList<>();

    @BeforeEach
    // 테스트마다 UUID 표시가 붙은 메뉴 생성
    void prepareOwnFixtures() {
        // 기존 menu_name 길이에 맞춰 UUID 일부를 사용한다.
        prefix = "J7" + java.util.UUID.randomUUID().toString()
                .replace("-", "").substring(0, 16) + "-";
        category = categoryRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("menudb의 기존 카테고리가 필요합니다."));
        for (Menu menu : menuRepository.saveAll(List.of(
                new Menu(prefix + "마늘빵", 2500, category),
                new Menu(prefix + "마늘크림빵", 4500, category),
                new Menu(prefix + "아메리카노", 3000, category)))) {
            fixtureCodes.add(menu.getMenuCode());
        }
    }

    @AfterEach
    void cleanupOwnFixtures() {
        // 이번 테스트의 UUID 표시가 붙은 주문과 직접 만든 메뉴 번호만 정리한다.
        for (PurchaseOrder order : orderRepository.findByCustomerStartingWith(prefix)) {
            orders.delete(order.getId());
        }
        for (Integer code : fixtureCodes) {
            menuRepository.deleteById(code);
        }
        fixtureCodes.clear();
    }

    protected long ownOrderCount() {
        return orderRepository.countByCustomerStartingWith(prefix);
    }

}
