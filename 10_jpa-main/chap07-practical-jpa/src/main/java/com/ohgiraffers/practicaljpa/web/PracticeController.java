package com.ohgiraffers.practicaljpa.web;

import com.ohgiraffers.practicaljpa.common.PageResponse;
import com.ohgiraffers.practicaljpa.menu.MenuService;
import com.ohgiraffers.practicaljpa.order.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {
    private final OrderService orders;
    private final MenuService menus;
    public PracticeController(OrderService orders, MenuService menus) {
        this.orders = orders; this.menus = menus;
    }

    public record CustomerRequest(@NotBlank String customer) {}

    @PostMapping("/orders")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public OrderService.OrderResponse create(@Valid @RequestBody CustomerRequest request) {
        return orders.create(request.customer());
    }
    @GetMapping("/orders/{id}")
    public OrderService.OrderResponse find(@PathVariable Long id) { return orders.find(id); }

    @DeleteMapping("/orders/{id}/lines/{lineId}")
    public OrderService.OrderResponse remove(@PathVariable Long id, @PathVariable Long lineId) {
        return orders.removeLine(id, lineId);
    }
    @PatchMapping("/orders/{id}/customer")
    public OrderService.OrderResponse rename(@PathVariable Long id,
                                            @Valid @RequestBody CustomerRequest request) {
        return orders.rename(id, request.customer());
    }
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { orders.delete(id); }

    @GetMapping("/menus")
    public PageResponse<MenuService.MenuResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer categoryCode,
            @PageableDefault(size = 5, sort = "menuCode") Pageable pageable) {
        return PageResponse.from(menus.search(keyword, minPrice, maxPrice, categoryCode, pageable));
    }
}
