package cn.web.plugin.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName:  CacheRemove   
 * @Description:自定义缓存删除注解,after
 * @author: yuyong 
 * @date:   2018年9月14日 下午9:35:28   
 *     
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Documented
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheRemove {

    String value();

    String[] key();

}
