package com.ohgiraffers.practicaljpa.menu;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @Column(name = "category_code")
    private int categoryCode;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    protected Category() {}
    public int getCategoryCode() { return categoryCode; }
    public String getCategoryName() { return categoryName; }
}
