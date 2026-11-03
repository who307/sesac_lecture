package com.ohgiraffers.springdatajpa.menu.dto;

public class MenuResponse {

    private final int menuCode;
    private final String menuName;
    private final int menuPrice;
    private final int categoryCode;
    private final String categoryName;
    private final String orderableStatus;

    public MenuResponse(
            int menuCode, String menuName, int menuPrice,
            int categoryCode, String categoryName, String orderableStatus
    ) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
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

    public String getCategoryName() {
        return categoryName;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }
}
