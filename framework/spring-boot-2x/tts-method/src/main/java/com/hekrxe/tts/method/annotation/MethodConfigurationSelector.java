package com.hekrxe.tts.method.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author tanhuayou on 2019/02/14
 */
public class MethodConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                ProxyMethodConfiguration.class.getName()
        };
    }
}
