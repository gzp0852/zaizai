package com.shuaibi.demo.AopDemo;

import java.lang.annotation.*;

/**
 * @author WH1707069
 * @date 2019/1/22 19:30
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DemoAnnotation {
	String desc() default "我是注解我是注解...";
}
