package com.ohgiraffers.practicaljpa.order;

import jakarta.persistence.*;

@Entity
@Table(name = "practice_order_line")
public class OrderLine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private PurchaseOrder order;

    @Column(nullable = false)
    private String menuName;
    private int quantity;

    protected OrderLine() {}

    public OrderLine(String menuName, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");
        this.menuName = menuName;
        this.quantity = quantity;
    }

    void assignOrder(PurchaseOrder order) {
        this.order = order;
    }

    public Long getId() { return id; }
    public String getMenuName() { return menuName; }
    public int getQuantity() { return quantity; }
}
