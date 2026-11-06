package com.ohgiraffers.practicaljpa.order;

import com.ohgiraffers.practicaljpa.common.BaseTimeEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "practice_order")
public class PurchaseOrder extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String customer;
    // 부모 저장 전파와 고아 객체 삭제를 설정한다.
    // orphanRemoval = true : 관리중인 부모에서 빠진 자식 삭제, 부모 삭제하면 자식들도 삭제
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST,
    orphanRemoval = true)
    private List<OrderLine> lines = new ArrayList<>();

    protected PurchaseOrder() {}

    public PurchaseOrder(String customer) { this.customer = customer; }

    public void addLine(OrderLine line) {
        lines.add(line);
        line.assignOrder(this);
    }
    public void removeLine(Long lineId) {
        OrderLine line = lines.stream().filter(item -> item.getId().equals(lineId))
                .findFirst().orElseThrow(() -> new java.util.NoSuchElementException("주문항목 없음"));
        lines.remove(line);
        line.assignOrder(null); // 메모리의 자식 참조도 끊는다
    }
    public void changeCustomer(String customer) { this.customer = customer; }
    public Long getId() { return id; }
    public String getCustomer() { return customer; }
    public List<OrderLine> getLines() { return lines; }
}
