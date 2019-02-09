package cn.admin.plugin.spring.aspect;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.admin.plugin.spring.CacheRemove;

@Aspect
@Component
public class CacheRemoveAspect {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    //表示所有带注解类CacheRemove都执行，@annotation中的值是注解的全限定名。
    @Pointcut(value = "(execution(* *.*(..)) && @annotation(cn.admin.plugin.spring.CacheRemove))")
    private void pointcut() {
    }

    //缓存会在增删改的方法执行完后才要移除。所以使用@AfterReturning()
    @AfterReturning(value = "pointcut()")
    private void process(JoinPoint joinPoint) {
        //1.拦截方法上的注解
        //2.判断注解是不是CacheRemove
        //3.由于注解传入的 key 是个数组，循环处理每个key
        //4.在循环中编制每个 key 为 pattern， 并循环所有的缓存，移除匹配上的缓存
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheRemove cacheRemove = method.getAnnotation(CacheRemove.class);
        if (cacheRemove != null) {
            //redis key:blogWebCenter::selectByExample_1_8
            String value = cacheRemove.value();
            //需要移除的正则key
            String[] keys = cacheRemove.key();
            //获取value域下的所有cacheKeys，用来和key做正则判断
            Set<String> cacheKeys = redisTemplate.keys(value + "*");
            for (String key : keys) {
                Pattern pattern = Pattern.compile(key);
                for (String cacheKey : cacheKeys) {
                    if (pattern.matcher(cacheKey).find()) {
                        redisTemplate.delete(cacheKey);
                    }
                }
            }
        }
    }

}
