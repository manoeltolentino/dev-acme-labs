package com.acme.application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class ContextService {

    private final ApplicationContext applicationContext;

    public ContextService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public int getBeansCount() {
        return applicationContext.getBeanDefinitionCount();
    }

    public List<String> getBeansLoaded() {

        ArrayList<String> beanList = new ArrayList<>();

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
            beanList.add(beanName);
        }

        return beanList;

    }

}
