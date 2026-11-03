package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Section03Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    // mappedBy에는 DB 컬럼명이 아니라 반대편 Menu의 연관관계 필드명 category를 적는다.
    @OneToMany(mappedBy = "category")
    private List<Menu> menuList = new ArrayList<>();

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    // 연관관계 편의 메서드
    public void addMenu(Menu menu) {
        menuList.add(menu); // 현재 카테고리의 메뉴 목록에 전달받은 메뉴 추가
        menu.setCategory(this); // 전달받은 메뉴에도 현재 카테고리를 설정
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }



}
