package com.ohgiraffers.mapping.section02.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// 별도 테이블 없이 소유 엔티티의 컬럼에 포함되는 값 객체를 뜻한다
// 이 클래스의 필드들을 엔티티 내부에 포함해 매핑할 수 있다.
@Embeddable
public class Price {

    @Column(name="regular_price", nullable = false)
    private int regularPrice;

    @Column(name="dicount_rate", nullable = false)
    private double discountRate;

    @Column(name="sell_price", nullable = false)
    private int sellPrice;

    protected Price() {}

    public Price(int regularPrice, double discountRate) {
        validatePrice(regularPrice);
        validateDiscountRate(discountRate);
        this.regularPrice = regularPrice;
        this.discountRate = discountRate;
        this.sellPrice = calculateSellPrice(regularPrice, discountRate);
    }

    private void validatePrice(int regularPrice) {
        if (regularPrice < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
    }

    private void validateDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            throw new IllegalArgumentException("할인율은 0 이상 1 이하여야 합니다.");
        }
    }

    private int calculateSellPrice(int regularPrice, double discountRate) {
        return (int) (regularPrice - (regularPrice * discountRate));
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
