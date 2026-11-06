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

class AuditingTests extends FixtureSupport {
    @Test
    void auditingKeepsCreationAndUpdatesModificationTime() throws InterruptedException {
        // DB 저장 정밀도에 맞춘 재조회 값을 기준으로 비교한다.
        Long id = orders.create(prefix + "수정 전").id();
        OrderService.OrderResponse created = orders.find(id);
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        Thread.sleep(30); // DATETIME(6)의 저장 정밀도보다 확실하게 구분한다.
        OrderService.OrderResponse updated = orders.rename(created.id(), prefix + "수정 후");
        assertEquals(created.createdAt(), updated.createdAt());
        assertTrue(updated.updatedAt().isAfter(created.updatedAt()));
    }
}
