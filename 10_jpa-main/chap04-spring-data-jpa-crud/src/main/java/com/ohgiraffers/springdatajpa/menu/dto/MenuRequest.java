package com.ohgiraffers.springdatajpa.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class MenuRequest {

    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String menuName;

    @Positive(message = "메뉴 가격은 0보다 커야 합니다.")
    private int menuPrice;

    @Positive(message = "카테고리 코드는 0보다 커야 합니다.")
    private int categoryCode;

    @Pattern(regexp = "Y|N", message = "주문 가능 상태는 Y 또는 N이어야 합니다.")
    @NotBlank
    private String orderableStatus;

    public MenuRequest() {}

    public MenuRequest(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }
}
