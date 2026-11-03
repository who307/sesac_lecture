package com.ohgiraffers.associationmapping.section03.bidirection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BiDirectionService {

    private final BiDirectionRepository biDirectionRepository;

    public BiDirectionService(BiDirectionRepository biDirectionRepository) {
        this.biDirectionRepository = biDirectionRepository;
    }

    @Transactional(readOnly = true)
    public Menu findMenu(int menuCode) {
        return biDirectionRepository.findMenu(menuCode);
    }

    @Transactional(readOnly = true)
    public Category findCategory(int categoryCode){
        return biDirectionRepository.findCategory(categoryCode);
    }

}
