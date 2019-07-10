package com.clinicmaster.clinic.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class ControllerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    ThreadLocal<Map<Integer, Integer>> count = new ThreadLocal<>();

    /**
     * 定义一个切入点. 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 定义在web包或者子包 ~ 第三个 * 任意方法 ~ .. 匹配任意数量的参数.
     */
    static final String pCutStr = "execution(* com.clinicmaster.clinic.controller.RegisterController.callPatient(Integer))";

    @Pointcut(value = pCutStr)
    public void cutPoint() {

    }


    @Around("cutPoint()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        if (count.get()==null) {
            log.info("new map...");
            count.set(new HashMap<>(16));
        }

        Object result = joinPoint.proceed();
        if (result == null) {
            return null;
        }

        Object[] args = joinPoint.getArgs();

        log.info("arg:{}",args[0]);

        if (count.get().containsKey(args[0])) {
            Integer num = count.get().get(args[0]);
            count.get().remove(args[0]);//先移除，在增加
            count.get().put((Integer) args[0], num + 1);
        } else {
            count.get().put((Integer) args[0], 1);
        }

        log.info("num:{}",count.get().get(args[0]));

        return result;
    }

    @AfterReturning("cutPoint()")
    public void afterMethod(JoinPoint joinPoint) {
        //获取参数
//        Object[] args = joinPoint.getArgs();
//        log.info("args:{}", args);

        for (Map.Entry<Integer, Integer> entry : count.get().entrySet()) {
            if (entry.getValue().equals(3)) {
                String key = "patient" + entry.getKey();
                String patientId = stringRedisTemplate.opsForList().index(key, 0);
                stringRedisTemplate.opsForList().leftPop(key);
                stringRedisTemplate.opsForList().rightPush(key,patientId);
            }
        }

    }
}
