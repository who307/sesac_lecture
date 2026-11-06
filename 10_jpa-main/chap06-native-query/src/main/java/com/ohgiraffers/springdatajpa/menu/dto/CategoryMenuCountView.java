package com.ohgiraffers.springdatajpa.menu.dto;

public interface CategoryMenuCountView {

    // Jackson이 응답 본문인 JSON으로 만들 때 사용할 getter
    // categoryCode,categoryName, menuCount
    int getCategoryCode();

    String getCategoryName();

    long getMenuCount();
}
