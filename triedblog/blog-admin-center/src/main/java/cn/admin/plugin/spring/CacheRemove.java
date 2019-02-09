package cn.admin.plugin.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义缓存删除注解
 * Copyright (c) 2017 by 
 * @ClassName: CacheRemove.java
 * @Description: TODO
 * 
 * @author: 余勇
 * @version: V1.0  
 * @Date: 2018年9月1日 上午10:58:37
 */
@Documented
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheRemove {

    String value();

    String[] key();

}
