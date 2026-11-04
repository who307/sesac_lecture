package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.MenuRequest;
import com.ohgiraffers.springdatajpa.menu.dto.MenuResponse;
import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.exception.ResourceNotFoundException;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public MenuService(MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
    }

    private Menu findMenuEntity(int menuCode) {
        return menuRepository.findById(menuCode)
                .orElseThrow( () -> new ResourceNotFoundException("메뉴를 찾을 수 없습니다."));
    }

    private MenuResponse toResponse(Menu menu) {
        Category category = menu.getCategory();
        return new MenuResponse(
                menu.getMenuCode(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                category.getCategoryCode(),
                category.getCategoryName(),
                menu.getOrderableStatus()
        );
    }

    @Transactional(readOnly = true)
    public MenuResponse findMenu(int menuCode) {
        return toResponse(findMenuEntity(menuCode));
    }

    @Transactional(readOnly = true)
    public List<MenuResponse> findMenus() {
        return menuRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private Category findCategory(int categoryCode) {
        return categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다."));
    }

    @Transactional
    public MenuResponse createMenu(MenuRequest request) {
        Category category = findCategory(request.getCategoryCode());
        Menu menu = new Menu(
                request.getMenuName(),
                request.getMenuPrice(),
                category,
                request.getOrderableStatus()
        );

//        Menu response = menuRepository.save(menu);
//
//        MenuResponse result = toResponse(response);
//
//        return result;

        return toResponse(menuRepository.save(menu));
    }

    @Transactional
    public MenuResponse updateMenu(int menuCode, MenuRequest request) {
        Menu menu = findMenuEntity(menuCode);
        Category category = findCategory(request.getCategoryCode());

        menu.update(
                request.getMenuName(),
                request.getMenuPrice(),
                category,
                request.getOrderableStatus());

        return toResponse(menu);
    }

    @Transactional
    public void deleteMenu(int menuCode) {
        Menu menu = findMenuEntity(menuCode);
        menuRepository.delete(menu);
    }
}
