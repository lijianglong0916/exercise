package com.exercise.config.annotation;

import java.lang.annotation.*;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-11 18:44
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterceptAnnotation  {

    public boolean flag();
}
