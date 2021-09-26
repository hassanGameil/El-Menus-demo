package com.eComm.demo;

import com.eComm.demo.order._Order;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Set;

@SpringBootTest
public class ValidateOrderClassesHierarchyTest {
    @Test
    public void validateOrderClassesBelongsToCorrectPackage() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(_Order.class));

        Set<BeanDefinition> components = provider.findCandidateComponents("com");
        for (BeanDefinition component : components)
        {
            Class cls = Class.forName(component.getBeanClassName());
            Assert.assertEquals("Class: "+cls.getName()+" Doesn't follow the correct hierarchy, Please rebase it at package 'com.eComm.demo.order' ","com.eComm.demo.order",cls.getPackage().getName());
        }
    }
}
