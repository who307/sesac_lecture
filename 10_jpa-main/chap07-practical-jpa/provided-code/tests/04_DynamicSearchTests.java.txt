package com.ohgiraffers.practicaljpa;

import com.ohgiraffers.practicaljpa.menu.*;
import com.ohgiraffers.practicaljpa.order.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DynamicSearchTests extends FixtureSupport {
    @Test
    void dynamicConditionsAndPaging() {
        var page = menus.search(prefix + "마늘", 2000, 3000, category.getCategoryCode(),
                PageRequest.of(0, 1, Sort.by("menuCode")));
        assertEquals(1, page.getTotalElements());
        assertEquals(prefix + "마늘빵", page.getContent().get(0).menuName());
        assertEquals(3, menus.search(prefix, null, null, null,
                PageRequest.of(0, 2)).getTotalElements());
        assertEquals(2, menus.search(prefix, null, null, null,
                PageRequest.of(0, 2)).getContent().size());
        assertThrows(IllegalArgumentException.class, () -> menus.search(
                null, 5000, 1000, null, PageRequest.of(0, 5)));
    }
}
