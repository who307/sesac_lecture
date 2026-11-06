package com.ohgiraffers.practicaljpa.menu;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public final class MenuSpecifications {
    private MenuSpecifications() {}

    public static Specification<Menu> search(String keyword, Integer minPrice,
                                              Integer maxPrice, Integer categoryCode) {
        // 이름 조건부터 한 단계씩 작성한다.
        /*
        * Root<Menu> : 조회할 Menu의 필드 지정
        * CriteriaQuery<?> : 쿼리 전체 설정
        * CriteriaBuilder : 비교 조건 생성
        * */
        return (root, query, cb) -> {
            ArrayList<Predicate> conditions = new ArrayList<>(); // 검색 조건을 담기 위한 목록
            if(keyword != null && !keyword.isBlank()){
                String pattern = "%" + escapeLike(keyword) + "%";
                conditions.add(cb.like(root.get("menuName"), pattern, '!'));
    //            return cb.conjuction(); // 항상 참인 조건을 만든다, 결국 조건 없이 메뉴를 조회하게 한다.
            }
            if(minPrice != null){
                conditions.add(cb.greaterThanOrEqualTo(root.get("menuPrice"), minPrice));
            }
            if(maxPrice != null){
                conditions.add(cb.lessThanOrEqualTo(root.get("menuPrice"), maxPrice));
            }
            if(categoryCode != null) {
                conditions.add(cb.equal(root.get("category").get("categoryCode"), categoryCode));
            }

            // toArray(new Predicate[0]) : Predicate 타입의 배열로 바꾸겠다는 표현
            return cb.and(conditions.toArray(new Predicate[0]));
        };

    }

    // 문자열 처리 자체는 이전 학습 내용이므로 제공한다.
    // LIKE에서 %, _를 검색 패턴이 아닌 입력 문자로 취급하기 위한 처리다.
    static String escapeLike(String keyword) {
        return keyword.replace("!", "!!").replace("%", "!%").replace("_", "!_");
    }
}
