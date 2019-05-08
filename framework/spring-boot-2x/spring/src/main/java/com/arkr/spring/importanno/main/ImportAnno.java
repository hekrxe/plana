package com.arkr.spring.importanno.main;

import com.arkr.spring.importanno.ImportBeanDefinitionRegistrarAnno;
import com.arkr.spring.importanno.ImportRegular;

import java.lang.annotation.*;

/**
 * @author tanhuayou on 2019/03/22
 */
@ImportRegular
@ImportBeanDefinitionRegistrarAnno(value = ImportAnno.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportAnno {
}
