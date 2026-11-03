package com.ohgiraffers.associationmapping.section01.manytoone;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManyToOneService {

    private final ManyToOneRepository manyToOneRepository;

    public ManyToOneService(ManyToOneRepository manyToOneRepository){
        this.manyToOneRepository = manyToOneRepository;
    }

    @Transactional(readOnly = true)
    public Menu findMenu(int menuCode) {
        return manyToOneRepository.find(menuCode);
    }

    @Transactional
    public Menu registMenu(MenuDTO menuInfo) {
        Category category = manyToOneRepository.findCategory(menuInfo.getCategoryCode());

        if (category == null) {
            throw new IllegalArgumentException("존재하지 않는 카테고리입니다");
        }

        Menu menu = new Menu(
                menuInfo.getMenuCode(),
                menuInfo.getMenuName(),
                menuInfo.getMenuPrice(),
                category,
                menuInfo.getOrderableStatus()
        );

        return manyToOneRepository.save(menu);
    }
}
