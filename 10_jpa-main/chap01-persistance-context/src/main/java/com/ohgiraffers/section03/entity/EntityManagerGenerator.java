package com.ohgiraffers.section03.entity;

import com.ohgiraffers.section01.entitymanager.EntityManagerFactoryGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerGenerator {

    private EntityManagerGenerator() {}

    public static EntityManager getInstance() {
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();
        return factory.createEntityManager();
    }
}
