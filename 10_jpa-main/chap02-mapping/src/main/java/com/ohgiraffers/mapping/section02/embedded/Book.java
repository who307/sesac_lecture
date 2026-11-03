package com.ohgiraffers.mapping.section02.embedded;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Table(name="tbl_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_no", nullable = false, length = 100)
    private int bookNo;
    @Column(name="book_title")
    private String bookTitle;
    @Column(name="author")
    private String author;
    @Column(name="publisher")
    private String publisher;
    @Column(name="published_date")
    private LocalDate publishedDate;

    // Price의 값들을 Book 테이블의 컬럼으로 함께 저장
    @Embedded
    private Price price;

    protected Book() {}

    public Book(
            String bookTitle, String author, String publisher,
            LocalDate publishedDate, Price price
    ) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
    }

    public int getBookNo() {
        return bookNo;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Price getPrice() {
        return price;
    }
}
