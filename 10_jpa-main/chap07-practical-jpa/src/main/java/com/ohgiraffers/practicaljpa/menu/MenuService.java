package com.ohgiraffers.practicaljpa.menu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class MenuService {
    private final MenuRepository menus;
    public MenuService(MenuRepository menus) { this.menus = menus; }

    public record MenuResponse(Integer menuCode, String menuName, int menuPrice,
                               int categoryCode, String categoryName) {
        public static MenuResponse from(Menu menu) {
            return new MenuResponse(menu.getMenuCode(), menu.getMenuName(), menu.getMenuPrice(),
                    menu.getCategory().getCategoryCode(),
                    menu.getCategory().getCategoryName());
        }
    }

    @Transactional(readOnly = true)
    public Page<MenuResponse> search(String keyword, Integer minPrice,
                                     Integer maxPrice, Integer categoryCode, Pageable pageable) {
        if ((minPrice != null && minPrice < 0) || (maxPrice != null && maxPrice < 0)
                || (minPrice != null && maxPrice != null && minPrice > maxPrice)) {
            throw new IllegalArgumentException("가격 범위를 확인하세요.");
        }
        return menus.findAll(MenuSpecifications.search(keyword, minPrice, maxPrice, categoryCode),
                pageable).map(MenuResponse::from);
    }


}
