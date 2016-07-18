package com.handtours.service.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:18
 */
public class AbsoluteClassNameGenerator implements BeanNameGenerator {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return definition.getBeanClassName();
    }
}
