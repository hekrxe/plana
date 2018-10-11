package com.inject.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

/**
 * User: tanhuayou
 * Date: 2018/10/11
 */
public class MyAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements PriorityOrdered, BeanClassLoaderAware {

    private ClassLoader classLoader;

    private final Map<String, InjectionMetadata> injectionMetadataCache =
            new ConcurrentHashMap<>(256);

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = findMetadata(beanName, bean.getClass(), pvs);
        try {
            metadata.inject(bean, beanName, pvs);
        } catch (BeanCreationException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of @MyAnnotation dependencies failed", ex);
        }
        return pvs;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 2;
    }

    private InjectionMetadata findMetadata(String beanName, Class<?> clazz, PropertyValues pvs) {
        // Fall back to class name as cache key, for backwards compatibility with custom callers.
        String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
        // Quick check on the concurrent map first, with minimal locking.
        InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
            synchronized (this.injectionMetadataCache) {
                metadata = this.injectionMetadataCache.get(cacheKey);
                if (InjectionMetadata.needsRefresh(metadata, clazz)) {
                    if (metadata != null) {
                        metadata.clear(pvs);
                    }
                    try {
                        metadata = buildMetadata(clazz);
                        this.injectionMetadataCache.put(cacheKey, metadata);
                    } catch (NoClassDefFoundError err) {
                        throw new IllegalStateException("Failed to introspect bean class [" + clazz.getName() +
                                "] for autowiring metadata: could not find class that it depends on", err);
                    }
                }
            }
        }
        return metadata;
    }

    private InjectionMetadata buildMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>(findFieldReferenceMetadata(beanClass));
        return new InjectionMetadata(beanClass, elements);

    }

    private List<InjectionMetadata.InjectedElement> findFieldReferenceMetadata(final Class<?> beanClass) {
        final List<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
        ReflectionUtils.doWithFields(beanClass, field -> {
            MyAnnotation annotation = getAnnotation(field, MyAnnotation.class);
            if (annotation != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    return;
                }
                elements.add(new MyAnnotationFieldElement(field, annotation));
            }

        });
        return elements;
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private class MyAnnotationFieldElement extends InjectionMetadata.InjectedElement {

        private final Field field;

        private final MyAnnotation myAnnotation;

        MyAnnotationFieldElement(Field field, MyAnnotation myAnnotation) {
            super(field, null);
            this.field = field;
            this.myAnnotation = myAnnotation;
        }

        @Override
        protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
            Class<?> clazz = field.getType();
            ReflectionUtils.makeAccessible(field);
            field.set(bean, getBean(myAnnotation, clazz));
        }
    }

    private Object getBean(MyAnnotation myAnnotation, Class<?> clazz) {
        if (clazz.isInterface()) {
            return Proxy.newProxyInstance(classLoader, new Class[]{clazz}, (proxy, method, args) -> {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
                System.out.println("method=" + method.getName() + ",args=" + Arrays.toString(args));
                // TODO 适配一下返回值类型
                return null;
            });
        } else {
            // TODO
            return null;
        }
    }


}
