package com.ohgiraffers.mapping.section02.embedded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookRegistServiceTests {

    @Autowired
    private BookRegistService bookRegistService;

    @Test
    @DisplayName("임베디드 가격을 포함한 도서 저장 확인")
    void testRegistBookWithEmbeddedPrice() {
        // given
        BookRegistDTO newBook = new BookRegistDTO(
                "자바 ORM 표준 JPA 프로그래밍",
                "김영한",
                "에이콘",
                LocalDate.now(),
                43000,
                0.1
        );

        // when
        Book savedBook = bookRegistService.registBook(newBook);

        // then
        assertTrue(savedBook.getBookNo() > 0);
        assertEquals("자바 ORM 표준 JPA 프로그래밍", savedBook.getBookTitle());
        assertEquals(38700, savedBook.getPrice().getSellPrice());
    }

    @Test
    @DisplayName("할인율은 0 이상 1 이하만 허용")
    void testInvalidDiscountRate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Price(43000, 1.1)
        );
    }
}
