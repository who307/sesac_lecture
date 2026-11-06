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

class OrderLifecycleTests extends FixtureSupport {
    @Test
    void cascadePersistAndOrphanRemoval() {
        OrderService.OrderResponse created = orders.create(prefix + "학생");
        assertEquals(2, orders.find(created.id()).lines().size());
        Long removedId = created.lines().get(0).id();
        orders.removeLine(created.id(), removedId);
        assertEquals(1, orders.find(created.id()).lines().size());
        EntityManager em = factory.createEntityManager();
        try {
            assertNull(em.find(OrderLine.class, removedId));
        } finally { em.close(); }
    }

    @Test
    void deletingParentAlsoDeletesOwnedLines() {
        OrderService.OrderResponse created = orders.create(prefix + "학생");
        orders.delete(created.id());
        EntityManager em = factory.createEntityManager();
        try {
            for (OrderService.LineResponse line : created.lines()) {
                assertNull(em.find(OrderLine.class, line.id()));
            }
        } finally { em.close(); }
        assertEquals(0, ownOrderCount());
    }
}
