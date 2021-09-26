package com.eComm.demo;

import com.eComm.demo.payment.Payment;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Set;

public class ValidatePaymentClassesHierarchyTest {
    @Test
    public void validatePaymentClassesBelongsToCorrectPackage() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(Payment.class));

        Set<BeanDefinition> components = provider.findCandidateComponents("com");
        for (BeanDefinition component : components)
        {
            Class cls = Class.forName(component.getBeanClassName());
            Assert.assertEquals("Class: "+cls.getName()+" Doesn't follow the correct hierarchy, Please rebase it at package 'com.eComm.demo.payment' ","com.eComm.demo.payment",cls.getPackage().getName());
        }
    }
}
