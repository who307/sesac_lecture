package com.ohgiraffers.associationmapping.section02.onetomany;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OneToManyService {

    private final OneToManyRepository oneToManyRepository;

    public OneToManyService(OneToManyRepository oneToManyRepository) {
        this.oneToManyRepository = oneToManyRepository;
    }

    @Transactional(readOnly = true)
    public Category findCategory(int categoryCode) {
        return oneToManyRepository.find(categoryCode);
    }

}
