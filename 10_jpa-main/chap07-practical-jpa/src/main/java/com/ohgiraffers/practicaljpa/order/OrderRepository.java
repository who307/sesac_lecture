package com.ohgiraffers.practicaljpa.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> {
    // customer가 prefix로 시작하는 주문 수만 센다.
    long countByCustomerStartingWith(String prefix);
    java.util.List<PurchaseOrder> findByCustomerStartingWith(String prefix);
}
