package com.ohgiraffers.practicaljpa.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orders;
    public OrderService(OrderRepository orders) { this.orders = orders; }

    // record: 데이터를 전달할 클래스를 간단히 선언하는 문법
    // 각 값을 보관하는 private final 필드와, 순서대로 값을 받는 생성자, 각 값을 읽는 메소드를 제공
    public record LineResponse(Long id, String menuName, int quantity) {}

    public record OrderResponse(Long id, String customer, List<LineResponse> lines,
                                LocalDateTime createdAt, LocalDateTime updatedAt) {}

    // 엔티티 값을 읽어 위의 응답 객체들로 만드는 메서드
    private OrderResponse toResponse(PurchaseOrder order) {
        return new OrderResponse(order.getId(), order.getCustomer(),
                order.getLines().stream()
                        .map(line -> new LineResponse(line.getId(), line.getMenuName(), line.getQuantity()))
                        .toList(), order.getCreatedAt(), order.getUpdatedAt());
    }

    @Transactional
    public OrderResponse create(String customer) {
       PurchaseOrder order = new PurchaseOrder(customer);
       order.addLine(new OrderLine("아메리카노", 2));
       order.addLine(new OrderLine("카페라떼", 1));
       orders.saveAndFlush(order);

        return toResponse(order);
    }

    @Transactional
    public void failRuntime(String customer) {
        orders.saveAndFlush(new PurchaseOrder(customer));
        throw new IllegalStateException("저장 후 실패");
    }

    @Transactional
    public void failCheckedDefault(String customer) throws IOException {
        orders.saveAndFlush(new PurchaseOrder(customer));
        throw new IOException("체크 예외 기본 규칙 확인");
    }

    @Transactional(rollbackFor = IOException.class)
    public void failCheckedRollback(String customer) throws IOException {
        orders.saveAndFlush(new PurchaseOrder(customer));
        throw new IOException("체크 예외도 롤백");
    }

    @Transactional
    public void swallowFailure(String customer) {
        try{
            orders.saveAndFlush(new PurchaseOrder(customer));
            throw new IllegalStateException("업무 단계 실패");
        } catch (IllegalStateException e) {
            // 프록시 밖으로 전달되지 않는다 -> 결국 프록시는 정상 반환을 보므로 커밋한다.
        }

    }

    // 같은 객체 안에서 호출하므로 failRuntime()의 @Transactional을 거치지 않는다.
    public void selfInvocation(String customer) {
        failRuntime(customer);
    }

    @Transactional(readOnly = true)
    public OrderResponse find(Long id) {
        return toResponse(findEntity(id));
    }

    @Transactional
    public OrderResponse removeLine(Long id, Long lineId) {
        PurchaseOrder order = findEntity(id);
        order.removeLine(lineId);
        orders.flush();
        return toResponse(order);
    }

    @Transactional
    public OrderResponse rename(Long id, String customer) {
        PurchaseOrder order = findEntity(id);
        order.changeCustomer(customer);
        orders.flush();
        return toResponse(order);
    }

    @Transactional
    public void delete(Long id) {
        orders.delete(findEntity(id));
    }

    private PurchaseOrder findEntity(Long id) {
        return orders.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("주문 없음"));
    }
}
